package ifba.inf011.strategy;

import ifba.inf011.strategy.baseclass.EcoRouteStrategy;
import ifba.inf011.strategy.baseclass.SafeRouteStrategy;
import ifba.inf011.strategy.classic.Navigator;
import ifba.inf011.strategy.classic.PublicTransportRouteStrategy;
import ifba.inf011.strategy.classic.RoadRouteStrategy;
import ifba.inf011.strategy.classic.WalkingRouteStrategy;
import ifba.inf011.strategy.datatostrategy.AnalyticalNavigator;
import ifba.inf011.strategy.datatostrategy.FastestRouteStrategy;
import ifba.inf011.strategy.datatostrategy.FixedStopsRouteStrategy;
import ifba.inf011.strategy.datatostrategy.RoutingData;
import ifba.inf011.strategy.datatostrategy.ScenicRouteStrategy;
import ifba.inf011.strategy.domain.RouteRequest;
import ifba.inf011.strategy.optional.DefaultingNavigator;
import ifba.inf011.strategy.prepared.BicycleRouteStrategy;
import ifba.inf011.strategy.prepared.GuidedNavigator;
import ifba.inf011.strategy.prepared.TouristRouteStrategy;
import ifba.inf011.strategy.registry.ConfiguredNavigator;
import ifba.inf011.strategy.registry.RouteType;

// Client
public class Main {

    public static void main(String[] args) {

        RouteRequest request = new RouteRequest("Hotel", "Aeroporto");

        System.out.println("=== Implementação 1: Strategy clássico ===");

        Navigator navigator = new Navigator(new RoadRouteStrategy());
        System.out.println(navigator.buildRoute(request));

        navigator.setStrategy(new WalkingRouteStrategy());
        System.out.println(navigator.buildRoute(request));

        navigator.setStrategy(new PublicTransportRouteStrategy());
        System.out.println(navigator.buildRoute(request));

        System.out.println("\n=== Implementação 2: Strategy com preparação interna ===");

        GuidedNavigator guidedNavigator = new GuidedNavigator(new BicycleRouteStrategy());
        guidedNavigator.prepare(request);
        System.out.println(guidedNavigator.buildRoute(request));

        guidedNavigator.setStrategy(new TouristRouteStrategy());
        guidedNavigator.prepare(request);
        System.out.println(guidedNavigator.buildRoute(request));

        System.out.println("\n=== Implementação 3: Dados levados para a Strategy ===");

        RoutingData data = new RoutingData(
                new double[] { 4.0, 7.5, 2.0 },
                new double[] { 1.2, 1.8, 1.1 },
                new double[] { 8.0, 9.5, 7.0 }
        );

        AnalyticalNavigator analyticalNavigator =
                new AnalyticalNavigator(new FastestRouteStrategy(), data);

        System.out.println(analyticalNavigator.buildRoute(request));

        analyticalNavigator.setStrategy(new ScenicRouteStrategy());
        System.out.println(analyticalNavigator.buildRoute(request));

        analyticalNavigator.setStrategy(new FixedStopsRouteStrategy());
        System.out.println(analyticalNavigator.buildRoute(request));

        System.out.println("\n=== Implementação 4: Strategy opcional com padrão ===");

        DefaultingNavigator defaultingNavigator = new DefaultingNavigator();
        System.out.println(defaultingNavigator.buildRoute(request));

        defaultingNavigator.setStrategy(new WalkingRouteStrategy());
        System.out.println(defaultingNavigator.buildRoute(request));

        defaultingNavigator.clearStrategy();
        System.out.println(defaultingNavigator.buildRoute(request));

        System.out.println("\n=== Implementação 5: Strategy via enum ===");

        ConfiguredNavigator configuredNavigator = new ConfiguredNavigator();

        configuredNavigator.setStrategy(RouteType.ROAD);
        System.out.println(configuredNavigator.buildRoute(request));

        configuredNavigator.setStrategy(RouteType.PUBLIC_TRANSPORT);
        System.out.println(configuredNavigator.buildRoute(request));

        System.out.println("\n=== Implementação 6: Strategy com classe abstrata ===");

        Navigator safeNavigator = new Navigator(new SafeRouteStrategy());
        System.out.println(safeNavigator.buildRoute(request));

        safeNavigator.setStrategy(new EcoRouteStrategy());
        System.out.println(safeNavigator.buildRoute(request));
    }
}