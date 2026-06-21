package ifba.inf011.strategy.prepared;

import java.util.Arrays;

import ifba.inf011.strategy.domain.Route;
import ifba.inf011.strategy.domain.RouteRequest;

// Concrete Strategy
public class TouristRouteStrategy implements PreparedRouteStrategy {

    private String interestProfile;

    @Override
    public void collectRouteDetails(RouteRequest request) {
        this.interestProfile = "museus, praças e mirantes";
    }

    @Override
    public Route buildRoute(RouteRequest request) {
        return new Route(
                "Rota turística com foco em " + interestProfile,
                Arrays.asList(
                        request.getOrigin(),
                        "Museu da cidade",
                        "Praça histórica",
                        "Mirante",
                        request.getDestination()
                )
        );
    }
}