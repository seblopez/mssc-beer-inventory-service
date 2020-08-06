package guru.springframework.beer.inventory.service.bootstrap;

import guru.springframework.beer.inventory.service.domain.BeerInventory;
import guru.springframework.beer.inventory.service.repositories.BeerInventoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;

import java.util.UUID;

/**
 * Created by jt on 2019-06-07.
 */
@Slf4j
@RequiredArgsConstructor
// @Component
public class BeerInventoryBootstrap implements CommandLineRunner {
    public static final String BEER_1_UPC = "0631234200036";
    public static final String BEER_2_UPC = "0631234300019";
    public static final String BEER_3_UPC = "0083783375213";
    public static final UUID BEER_1_UUID = UUID.fromString("6ca05c31-d2e8-4354-802f-12520c570d6c");
    public static final UUID BEER_2_UUID = UUID.fromString("a1c383ac-6e76-441e-870c-49868ab911bb");
    public static final UUID BEER_3_UUID = UUID.fromString("ebe8f408-e873-4905-aad7-9b9644d86c72");

    private final BeerInventoryRepository beerInventoryRepository;

    @Override
    public void run(String... args) {
        if(beerInventoryRepository.count() == 0){
            loadInitialInv();
        }
    }

    private void loadInitialInv() {
        beerInventoryRepository.save(BeerInventory
                .builder()
                .beerId(BEER_1_UUID)
                .upc(BEER_1_UPC)
                .quantityOnHand(50)
                .build());

        beerInventoryRepository.save(BeerInventory
                .builder()
                .beerId(BEER_2_UUID)
                .upc(BEER_2_UPC)
                .quantityOnHand(50)
                .build());

        beerInventoryRepository.saveAndFlush(BeerInventory
                .builder()
                .beerId(BEER_3_UUID)
                .upc(BEER_3_UPC)
                .quantityOnHand(50)
                .build());

        log.debug("Loaded Inventory. Record count: " + beerInventoryRepository.count());
    }
}
