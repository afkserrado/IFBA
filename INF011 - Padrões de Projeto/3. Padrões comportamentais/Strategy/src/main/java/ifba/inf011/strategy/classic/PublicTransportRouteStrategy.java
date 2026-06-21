package ifba.inf011.strategy.classic;

import java.util.Arrays;

import ifba.inf011.strategy.domain.Route;
import ifba.inf011.strategy.domain.RouteRequest;

// Concrete Strategy
public class PublicTransportRouteStrategy implements RouteStrategy {

    @Override
    public Route buildRoute(RouteRequest request) {
        return new Route(
                "Rota de transporte público",
                Arrays.asList(
                        request.getOrigin(),
                        "Ponto de ônibus",
                        "Terminal urbano",
                        "Estação de metrô",
                        request.getDestination()
                )
        );
    }
}