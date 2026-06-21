package ifba.inf011.strategy.prepared;

import ifba.inf011.strategy.domain.Route;
import ifba.inf011.strategy.domain.RouteRequest;

// Context
public class GuidedNavigator {

    private PreparedRouteStrategy strategy;

    public GuidedNavigator(PreparedRouteStrategy strategy) {
        this.strategy = strategy;
    }

    public void setStrategy(PreparedRouteStrategy strategy) {
        this.strategy = strategy;
    }

    public void prepare(RouteRequest request) {
        if (strategy == null) {
            throw new IllegalStateException("Nenhuma estratégia de rota foi definida.");
        }

        strategy.collectRouteDetails(request);
    }

    public Route buildRoute(RouteRequest request) {
        if (strategy == null) {
            throw new IllegalStateException("Nenhuma estratégia de rota foi definida.");
        }

        return strategy.buildRoute(request);
    }
}