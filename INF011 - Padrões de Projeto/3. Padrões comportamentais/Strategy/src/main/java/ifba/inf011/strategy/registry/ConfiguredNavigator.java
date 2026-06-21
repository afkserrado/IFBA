package ifba.inf011.strategy.registry;

import ifba.inf011.strategy.classic.RouteStrategy;
import ifba.inf011.strategy.domain.Route;
import ifba.inf011.strategy.domain.RouteRequest;

// Context
public class ConfiguredNavigator {

    private RouteStrategy strategy;

    public void setStrategy(RouteType type) {
        this.strategy = type.strategy();
    }

    public Route buildRoute(RouteRequest request) {
        if (strategy == null) {
            throw new IllegalStateException("Nenhum tipo de rota foi definido.");
        }

        return strategy.buildRoute(request);
    }
}