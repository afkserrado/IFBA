package ifba.inf011.strategy.datatostrategy;

import java.util.Arrays;

import ifba.inf011.strategy.domain.Route;
import ifba.inf011.strategy.domain.RouteRequest;

// Concrete Strategy
public class ScenicRouteStrategy implements AnalyticalRouteStrategy {

    @Override
    public Route buildRoute(RouteRequest request, RoutingData data) {
        double scenicTotal = 0.0;

        for (double score : data.getScenicScores()) {
            scenicTotal += score;
        }

        return new Route(
                "Rota panorâmica com pontuação visual " + scenicTotal,
                Arrays.asList(
                        request.getOrigin(),
                        "Orla",
                        "Mirante",
                        request.getDestination()
                )
        );
    }
}