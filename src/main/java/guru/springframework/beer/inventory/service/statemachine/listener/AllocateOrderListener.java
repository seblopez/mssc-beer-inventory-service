package guru.springframework.beer.inventory.service.statemachine.listener;

import guru.springframework.beer.inventory.service.config.JmsConfig;
import guru.springframework.beer.inventory.service.services.AllocationService;
import guru.springframework.beer.inventory.service.statemachine.event.allocation.AllocateOrderRequest;
import guru.springframework.beer.inventory.service.statemachine.event.allocation.AllocateOrderResponse;
import guru.springframework.beer.inventory.service.statemachine.event.order.BeerOrderDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;

@Slf4j
@RequiredArgsConstructor
@Component
public class AllocateOrderListener {
    private final AllocationService allocationService;
    private final JmsTemplate jmsTemplate;

    @JmsListener(destination = JmsConfig.ALLOCATE_ORDER_REQUEST_QUEUE)
    public void listen(AllocateOrderRequest allocateOrderRequest) {
        final BeerOrderDto beerOrderDto = allocateOrderRequest.getBeerOrderDto();

        try {
            final Boolean orderAllocated = allocationService.allocateOrder(beerOrderDto);
            jmsTemplate.convertAndSend(JmsConfig.ALLOCATE_ORDER_RESPONSE_QUEUE,
                    AllocateOrderResponse.builder()
                            .beerOrderDto(beerOrderDto)
                            .pendingInventory(orderAllocated)
                            .allocationError(false)
                            .build());
        } catch (Exception exception) {
            log.error(MessageFormat.format("There was an error while processing request: {0}", exception.getMessage()));
            jmsTemplate.convertAndSend(JmsConfig.ALLOCATE_ORDER_RESPONSE_QUEUE,
                    AllocateOrderResponse.builder()
                            .beerOrderDto(beerOrderDto)
                            .pendingInventory(true)
                            .allocationError(true)
                            .build());
        }


    }
}
