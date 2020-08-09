package guru.springframework.beer.inventory.service.event.allocation;

import guru.springframework.beer.inventory.service.event.order.BeerOrderDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class AllocateOrderRequest implements Serializable {
    private static final long serialVersionUID = -2766651269198146830L;
    private BeerOrderDto beerOrderDto;
}
