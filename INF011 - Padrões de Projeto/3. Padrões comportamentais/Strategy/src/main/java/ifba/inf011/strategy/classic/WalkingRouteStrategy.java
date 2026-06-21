package ifba.inf011.strategy.classic;

import java.util.Arrays;

import ifba.inf011.strategy.domain.Route;
import ifba.inf011.strategy.domain.RouteRequest;

// Concrete Strategy
public class WalkingRouteStrategy implements RouteStrategy {

    @Override
    public Route buildRoute(RouteRequest request) {
        return new Route(
                "Rota a pé",
                Arrays.asList(
                        request.getOrigin(),
                        "Calçadão",
                        "Praça central",
                        request.getDestination()
                )
        );
    }
}