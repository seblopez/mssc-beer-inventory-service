package guru.springframework.beer.inventory.service.services;

import guru.springframework.beer.inventory.service.event.order.BeerOrderDto;

public interface AllocationService {
    Boolean allocateOrder(BeerOrderDto beerOrderDto);
}
