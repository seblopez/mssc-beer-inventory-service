package guru.springframework.beer.inventory.service.statemachine.listener;

import guru.springframework.beer.inventory.service.config.JmsConfig;
import guru.springframework.beer.inventory.service.services.AllocationService;
import guru.springframework.beer.inventory.service.statemachine.event.allocation.DeallocateOrderRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class DeallocationListener {
    private final AllocationService allocationService;

    @JmsListener(destination = JmsConfig.DEALLOCATE_ORDER_REQUEST_QUEUE)
    public void listen(DeallocateOrderRequest deallocateOrderRequest) {
        allocationService.deallocateOrder(deallocateOrderRequest.getBeerOrderDto());
    }

}
