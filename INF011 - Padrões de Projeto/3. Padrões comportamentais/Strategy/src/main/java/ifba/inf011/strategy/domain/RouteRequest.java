package ifba.inf011.strategy.domain;

// Domain Data
public class RouteRequest {

    private final String origin;
    private final String destination;

    public RouteRequest(String origin, String destination) {
        this.origin = origin;
        this.destination = destination;
    }

    public String getOrigin() {
        return origin;
    }

    public String getDestination() {
        return destination;
    }
}