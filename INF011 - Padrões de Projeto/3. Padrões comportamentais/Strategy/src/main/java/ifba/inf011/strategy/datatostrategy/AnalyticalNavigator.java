package ifba.inf011.strategy.datatostrategy;

import ifba.inf011.strategy.domain.Route;
import ifba.inf011.strategy.domain.RouteRequest;

// Context
public class AnalyticalNavigator {

    private AnalyticalRouteStrategy strategy;
    private final RoutingData routingData;

    public AnalyticalNavigator(AnalyticalRouteStrategy strategy, RoutingData routingData) {
        this.strategy = strategy;
        this.routingData = routingData;
    }

    public void setStrategy(AnalyticalRouteStrategy strategy) {
        this.strategy = strategy;
    }

    public Route buildRoute(RouteRequest request) {
        if (strategy == null) {
            throw new IllegalStateException("Nenhuma estratégia analítica foi definida.");
        }

        return strategy.buildRoute(request, routingData);
    }
}