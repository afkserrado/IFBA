package ifba.inf011.strategy.optional;

import ifba.inf011.strategy.classic.RoadRouteStrategy;
import ifba.inf011.strategy.classic.RouteStrategy;
import ifba.inf011.strategy.domain.Route;
import ifba.inf011.strategy.domain.RouteRequest;

// Context
public class DefaultingNavigator {

    private RouteStrategy strategy;
    private final RouteStrategy defaultStrategy;

    public DefaultingNavigator() {
        this.defaultStrategy = new RoadRouteStrategy();
    }

    public void setStrategy(RouteStrategy strategy) {
        this.strategy = strategy;
    }

    public void clearStrategy() {
        this.strategy = null;
    }

    public Route buildRoute(RouteRequest request) {
        RouteStrategy activeStrategy = strategy != null
                ? strategy
                : defaultStrategy;

        return activeStrategy.buildRoute(request);
    }
}