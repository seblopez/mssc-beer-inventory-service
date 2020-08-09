package guru.springframework.beer.inventory.service.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import guru.springframework.beer.inventory.service.event.NewInventoryEvent;
import guru.springframework.beer.inventory.service.event.allocation.AllocateOrderRequest;
import guru.springframework.beer.inventory.service.event.allocation.AllocateOrderResponse;
import guru.springframework.beer.inventory.service.event.order.BeerOrderDto;
import guru.springframework.beer.inventory.service.event.order.BeerOrderLineDto;
import guru.springframework.beer.inventory.service.event.order.BeerOrderStatusDto;
import guru.springframework.beer.inventory.service.web.model.BeerDto;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class JmsConfig {

    public static final String NEW_INVENTORY_QUEUE = "new-inventory-request";
    public static final String ALLOCATE_ORDER_REQUEST_QUEUE = "allocate-order";
    public static final String ALLOCATE_ORDER_RESPONSE_QUEUE = "allocate-order-result";


    @Bean
    public MessageConverter messageConverter(ObjectMapper objectMapper) {
        Map<String, Class<?>> typeIdMappings = new HashMap<>();
        typeIdMappings.put("guru.springframework.msscbeerservice.web.model.BeerDto", BeerDto.class);
        typeIdMappings.put("guru.springframework.msscbeerservice.event.NewInventoryEvent", NewInventoryEvent.class);
        typeIdMappings.put("guru.springframework.beer.order.service.event.AllocateOrderRequest", AllocateOrderRequest.class);
        typeIdMappings.put("guru.springframework.beer.inventory.service.event.allocation.AllocateOrderResponse", AllocateOrderResponse.class);
        typeIdMappings.put("guru.springframework.beer.inventory.service.event.order.BeerOrderDto", BeerOrderDto.class);
        typeIdMappings.put("guru.springframework.beer.inventory.service.event.order.BeerOrderLineDto", BeerOrderLineDto.class);
        typeIdMappings.put("guru.springframework.beer.inventory.service.event.order.BeerOrderStatusDto", BeerOrderStatusDto.class);

        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setTargetType(MessageType.TEXT);
        converter.setTypeIdPropertyName("_type");
        converter.setObjectMapper(objectMapper);
        converter.setTypeIdMappings(typeIdMappings);

        return converter;

    }
}
