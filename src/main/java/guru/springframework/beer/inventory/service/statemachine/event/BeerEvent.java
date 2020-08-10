package guru.springframework.beer.inventory.service.statemachine.event;

import guru.springframework.beer.inventory.service.web.model.BeerDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class BeerEvent implements Serializable {

    static final long serialVersionUID = -328541268806064236L;

    private BeerDto beerDto;

}
