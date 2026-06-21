package ifba.inf011.strategy.datatostrategy;

import java.util.Arrays;

import ifba.inf011.strategy.domain.Route;
import ifba.inf011.strategy.domain.RouteRequest;

// Concrete Strategy
public class FastestRouteStrategy implements AnalyticalRouteStrategy {

    @Override
    public Route buildRoute(RouteRequest request, RoutingData data) {
        double totalCost = 0.0;

        for (int i = 0; i < data.getDistances().length; i++) {
            totalCost += data.getDistances()[i] * data.getTrafficIndexes()[i];
        }

        return new Route(
                "Rota mais rápida com custo estimado " + totalCost,
                Arrays.asList(
                        request.getOrigin(),
                        "Trecho com menor tempo",
                        request.getDestination()
                )
        );
    }
}