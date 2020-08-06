package guru.springframework.beer.inventory.service.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import guru.springframework.beer.inventory.service.event.NewInventoryEvent;
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

    @Bean
    public MessageConverter messageConverter(ObjectMapper objectMapper) {
        Map<String, Class<?>> typeIdMappings = new HashMap<>();
        typeIdMappings.put("guru.springframework.msscbeerservice.web.model.BeerDto", BeerDto.class);
        typeIdMappings.put("guru.springframework.msscbeerservice.event.NewInventoryEvent", NewInventoryEvent.class);

        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setTargetType(MessageType.TEXT);
        converter.setTypeIdPropertyName("_type");
        converter.setObjectMapper(objectMapper);
        converter.setTypeIdMappings(typeIdMappings);

        return converter;

    }
}
