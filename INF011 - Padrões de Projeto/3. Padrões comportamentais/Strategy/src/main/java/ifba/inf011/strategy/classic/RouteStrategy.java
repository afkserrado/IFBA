package ifba.inf011.strategy.classic;

import ifba.inf011.strategy.domain.Route;
import ifba.inf011.strategy.domain.RouteRequest;

// Strategy
public interface RouteStrategy {
    Route buildRoute(RouteRequest request);
}