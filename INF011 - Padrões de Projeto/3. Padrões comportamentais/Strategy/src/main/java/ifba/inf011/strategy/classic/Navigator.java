package ifba.inf011.strategy.classic;

import ifba.inf011.strategy.domain.Route;
import ifba.inf011.strategy.domain.RouteRequest;

// Context
public class Navigator {

    private RouteStrategy strategy;

    public Navigator(RouteStrategy strategy) {
        this.strategy = strategy;
    }

    public void setStrategy(RouteStrategy strategy) {
        this.strategy = strategy;
    }

    public Route buildRoute(RouteRequest request) {
        if (strategy == null) {
            throw new IllegalStateException("Nenhuma estratégia de rota foi definida.");
        }

        return strategy.buildRoute(request);
    }
}