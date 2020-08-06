package guru.springframework.beer.inventory.service.listener;

import guru.springframework.beer.inventory.service.config.JmsConfig;
import guru.springframework.beer.inventory.service.domain.BeerInventory;
import guru.springframework.beer.inventory.service.event.NewInventoryEvent;
import guru.springframework.beer.inventory.service.repositories.BeerInventoryRepository;
import guru.springframework.beer.inventory.service.web.mappers.BeerInventoryMapper;
import guru.springframework.beer.inventory.service.web.model.BeerDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.text.MessageFormat;

@Slf4j
@RequiredArgsConstructor
@Component
public class NewInventoryListener {

    private final BeerInventoryRepository beerInventoryRepository;
    private final BeerInventoryMapper beerInventoryMapper;

    @Transactional
    @JmsListener(destination = JmsConfig.NEW_INVENTORY_QUEUE)
    public void listen(NewInventoryEvent inventoryEvent) {
        final BeerDto beerDto = inventoryEvent.getBeerDto();

        final BeerInventory beerInventory = beerInventoryMapper.beerDtoToBeerInventory(beerDto);

        log.debug(MessageFormat.format("Saving Beer UPC {0}, new QOH {1}", beerInventory.getUpc(), beerInventory.getQuantityOnHand()));

        beerInventoryRepository.save(beerInventory);

    }

}
