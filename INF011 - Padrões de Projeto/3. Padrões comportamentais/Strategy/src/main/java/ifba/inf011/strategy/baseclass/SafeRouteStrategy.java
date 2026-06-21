package ifba.inf011.strategy.baseclass;

import ifba.inf011.strategy.domain.Route;
import ifba.inf011.strategy.domain.RouteRequest;

// Concrete Strategy
public class SafeRouteStrategy extends AbstractRouteStrategy {

    @Override
    public Route buildRoute(RouteRequest request) {
        return createRoute(
                "Rota segura",
                request,
                "Avenida iluminada",
                "Área monitorada",
                "Rua movimentada"
        );
    }
}