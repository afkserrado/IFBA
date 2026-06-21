package ifba.inf011.strategy.baseclass;

import java.util.Arrays;

import ifba.inf011.strategy.classic.RouteStrategy;
import ifba.inf011.strategy.domain.Route;
import ifba.inf011.strategy.domain.RouteRequest;

// Abstract Strategy
public abstract class AbstractRouteStrategy implements RouteStrategy {

    protected Route createRoute(String description, RouteRequest request, String... middlePoints) {
        String[] checkpoints = new String[middlePoints.length + 2];

        checkpoints[0] = normalize(request.getOrigin());

        for (int i = 0; i < middlePoints.length; i++) {
            checkpoints[i + 1] = normalize(middlePoints[i]);
        }

        checkpoints[checkpoints.length - 1] = normalize(request.getDestination());

        return new Route(description, Arrays.asList(checkpoints));
    }

    protected String normalize(String point) {
        return point == null ? "" : point.trim();
    }
}