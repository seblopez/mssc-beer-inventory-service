package guru.springframework.beer.inventory.service.web.mappers;

import guru.springframework.beer.inventory.service.domain.BeerInventory;
import guru.springframework.beer.inventory.service.web.model.BeerDto;
import guru.springframework.beer.inventory.service.web.model.BeerInventoryDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 * Created by jt on 2019-05-31.
 */
@Mapper(uses = {DateMapper.class})
public interface BeerInventoryMapper {

    BeerInventory beerInventoryDtoToBeerInventory(BeerInventoryDto beerInventoryDTO);

    BeerInventoryDto beerInventoryToBeerInventoryDto(BeerInventory beerInventory);

    @Mappings({ @Mapping( target = "beerId", source = "id"), @Mapping(target = "id", ignore = true)})
    BeerInventory beerDtoToBeerInventory(BeerDto beerDto);
}
