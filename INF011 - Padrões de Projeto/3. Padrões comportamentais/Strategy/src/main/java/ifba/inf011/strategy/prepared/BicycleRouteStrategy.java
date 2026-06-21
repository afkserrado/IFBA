package ifba.inf011.strategy.prepared;

import java.util.Arrays;

import ifba.inf011.strategy.domain.Route;
import ifba.inf011.strategy.domain.RouteRequest;

// Concrete Strategy
public class BicycleRouteStrategy implements PreparedRouteStrategy {

    private boolean avoidSteepStreets;

    @Override
    public void collectRouteDetails(RouteRequest request) {
        this.avoidSteepStreets = true;
    }

    @Override
    public Route buildRoute(RouteRequest request) {
        String preference = avoidSteepStreets
                ? "evitando ladeiras"
                : "permitindo ladeiras";

        return new Route(
                "Rota de bicicleta " + preference,
                Arrays.asList(
                        request.getOrigin(),
                        "Ciclovia",
                        "Rua de baixo relevo",
                        request.getDestination()
                )
        );
    }
}