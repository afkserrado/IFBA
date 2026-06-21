package ifba.inf011.strategy.datatostrategy;

import java.util.Arrays;

import ifba.inf011.strategy.domain.Route;
import ifba.inf011.strategy.domain.RouteRequest;

// Concrete Strategy
public class FixedStopsRouteStrategy implements AnalyticalRouteStrategy {

    @Override
    public Route buildRoute(RouteRequest request, RoutingData data) {
        return new Route(
                "Rota com paradas fixas",
                Arrays.asList(
                        request.getOrigin(),
                        "Parada obrigatória 1",
                        "Parada obrigatória 2",
                        request.getDestination()
                )
        );
    }
}