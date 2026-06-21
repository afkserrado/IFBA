package ifba.inf011.strategy.datatostrategy;

// Domain Data
public class RoutingData {

    private final double[] distances;
    private final double[] trafficIndexes;
    private final double[] scenicScores;

    public RoutingData(double[] distances, double[] trafficIndexes, double[] scenicScores) {
        this.distances = distances;
        this.trafficIndexes = trafficIndexes;
        this.scenicScores = scenicScores;
    }

    public double[] getDistances() {
        return distances;
    }

    public double[] getTrafficIndexes() {
        return trafficIndexes;
    }

    public double[] getScenicScores() {
        return scenicScores;
    }
}