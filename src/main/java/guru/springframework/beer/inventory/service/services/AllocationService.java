package guru.springframework.beer.inventory.service.services;

import guru.springframework.beer.inventory.service.statemachine.event.order.BeerOrderDto;

public interface AllocationService {
    Boolean allocateOrder(BeerOrderDto beerOrderDto);

    void deallocateOrder(BeerOrderDto beerOrderDto);

}
