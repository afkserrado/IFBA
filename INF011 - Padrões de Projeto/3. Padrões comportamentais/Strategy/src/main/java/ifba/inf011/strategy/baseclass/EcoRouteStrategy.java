package ifba.inf011.strategy.baseclass;

import ifba.inf011.strategy.domain.Route;
import ifba.inf011.strategy.domain.RouteRequest;

// Concrete Strategy
public class EcoRouteStrategy extends AbstractRouteStrategy {

    @Override
    public Route buildRoute(RouteRequest request) {
        return createRoute(
                "Rota ecológica",
                request,
                "Ciclovia arborizada",
                "Parque urbano",
                "Via de baixa emissão"
        );
    }
}