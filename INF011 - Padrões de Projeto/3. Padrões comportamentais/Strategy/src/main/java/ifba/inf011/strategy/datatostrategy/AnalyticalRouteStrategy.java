package ifba.inf011.strategy.datatostrategy;

import ifba.inf011.strategy.domain.Route;
import ifba.inf011.strategy.domain.RouteRequest;

// Strategy
public interface AnalyticalRouteStrategy {
    Route buildRoute(RouteRequest request, RoutingData data);
}