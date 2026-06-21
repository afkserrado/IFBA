package ifba.inf011.strategy.classic;

import java.util.Arrays;

import ifba.inf011.strategy.domain.Route;
import ifba.inf011.strategy.domain.RouteRequest;

// Concrete Strategy
public class RoadRouteStrategy implements RouteStrategy {

    @Override
    public Route buildRoute(RouteRequest request) {
        return new Route(
                "Rota de carro",
                Arrays.asList(
                        request.getOrigin(),
                        "Avenida principal",
                        "Via expressa",
                        request.getDestination()
                )
        );
    }
}