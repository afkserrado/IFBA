package ifba.inf011.strategy.prepared;

import ifba.inf011.strategy.domain.Route;
import ifba.inf011.strategy.domain.RouteRequest;

// Strategy
public interface PreparedRouteStrategy {
    void collectRouteDetails(RouteRequest request);
    Route buildRoute(RouteRequest request);
}