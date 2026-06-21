package ifba.inf011.strategy.registry;

import ifba.inf011.strategy.classic.PublicTransportRouteStrategy;
import ifba.inf011.strategy.classic.RoadRouteStrategy;
import ifba.inf011.strategy.classic.RouteStrategy;
import ifba.inf011.strategy.classic.WalkingRouteStrategy;

// Strategy Registry
public enum RouteType {

    ROAD(new RoadRouteStrategy()),
    WALKING(new WalkingRouteStrategy()),
    PUBLIC_TRANSPORT(new PublicTransportRouteStrategy());

    private final RouteStrategy strategy;

    RouteType(RouteStrategy strategy) {
        this.strategy = strategy;
    }

    public RouteStrategy strategy() {
        return strategy;
    }
}