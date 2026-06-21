package ifba.inf011.chain_of_responsibility;

import ifba.inf011.chain_of_responsibility.canonical.AdminResourceHandler;
import ifba.inf011.chain_of_responsibility.canonical.PublicResourceHandler;
import ifba.inf011.chain_of_responsibility.canonical.UserResourceHandler;
import ifba.inf011.chain_of_responsibility.domain.AccessRequest;
import ifba.inf011.chain_of_responsibility.domain.Role;
import ifba.inf011.chain_of_responsibility.hierarchy.ApplicationSecurityHelp;
import ifba.inf011.chain_of_responsibility.hierarchy.CreateOrderEndpointHelp;
import ifba.inf011.chain_of_responsibility.hierarchy.OrdersModuleSecurityHelp;
import ifba.inf011.chain_of_responsibility.hierarchy.SecurityHelpNode;
import ifba.inf011.chain_of_responsibility.middleware.AuthenticationMiddleware;
import ifba.inf011.chain_of_responsibility.middleware.AuthorizationMiddleware;
import ifba.inf011.chain_of_responsibility.middleware.BusinessLogicMiddleware;
import ifba.inf011.chain_of_responsibility.middleware.Middleware;
import ifba.inf011.chain_of_responsibility.middleware.PayloadValidationMiddleware;
import ifba.inf011.chain_of_responsibility.middleware.Server;
import ifba.inf011.chain_of_responsibility.middleware.ThrottlingMiddleware;
import ifba.inf011.chain_of_responsibility.requestobject.AdminReportCommandHandler;
import ifba.inf011.chain_of_responsibility.requestobject.CreateOrderCommandHandler;
import ifba.inf011.chain_of_responsibility.requestobject.LoginCommandHandler;
import ifba.inf011.chain_of_responsibility.requestobject.SecurityCommand;
import ifba.inf011.chain_of_responsibility.requestobject.SecurityCommandType;

// Client
public class Main {

    public static void main(String[] args) {

        System.out.println("=== Implementação 1: Pipeline de middlewares ===");

        Server server = new Server();
        server.register("admin@example.com", "123");

        Middleware middleware = Middleware.link(
                new ThrottlingMiddleware(100),
                new AuthenticationMiddleware(server),
                new AuthorizationMiddleware(),
                new PayloadValidationMiddleware(),
                new BusinessLogicMiddleware()
        );

        AccessRequest validRequest = new AccessRequest(
                "admin@example.com",
                "123",
                Role.ADMIN,
                Role.ADMIN,
                10,
                "{ \"order\": 1001 }",
                "/orders/create"
        );

        middleware.check(validRequest);

        System.out.println("\n--- Requisição inválida ---");

        AccessRequest invalidRequest = new AccessRequest(
                "admin@example.com",
                "senha_errada",
                Role.ADMIN,
                Role.ADMIN,
                10,
                "{ \"order\": 1002 }",
                "/orders/create"
        );

        middleware.check(invalidRequest);

        System.out.println("\n=== Implementação 2: Primeiro handler capaz ===");

        PublicResourceHandler publicHandler = new PublicResourceHandler();
        UserResourceHandler userHandler = new UserResourceHandler();
        AdminResourceHandler adminHandler = new AdminResourceHandler();

        publicHandler.setNext(userHandler);
        userHandler.setNext(adminHandler);

        AccessRequest adminAreaRequest = new AccessRequest(
                "user@example.com",
                "123",
                Role.USER,
                Role.ADMIN,
                1,
                "{}",
                "/admin/report"
        );

        publicHandler.handle(adminAreaRequest);

        System.out.println("\n=== Implementação 3: Links existentes na hierarquia ===");

        SecurityHelpNode appHelp = new ApplicationSecurityHelp();
        SecurityHelpNode ordersHelp = new OrdersModuleSecurityHelp(appHelp);

        SecurityHelpNode endpointWithoutOwnHelp =
                new CreateOrderEndpointHelp(ordersHelp, null);

        endpointWithoutOwnHelp.showSecurityHelp();

        SecurityHelpNode endpointWithOwnHelp =
                new CreateOrderEndpointHelp(
                        ordersHelp,
                        "Ajuda do endpoint: este recurso exige perfil USER ou ADMIN."
                );

        endpointWithOwnHelp.showSecurityHelp();

        System.out.println("\n=== Implementação 4: Objeto de requisição genérico ===");

        LoginCommandHandler loginHandler = new LoginCommandHandler();
        CreateOrderCommandHandler createOrderHandler = new CreateOrderCommandHandler();
        AdminReportCommandHandler reportHandler = new AdminReportCommandHandler();

        loginHandler.setNext(createOrderHandler);
        createOrderHandler.setNext(reportHandler);

        SecurityCommand loginCommand = new SecurityCommand(
                SecurityCommandType.LOGIN,
                "user@example.com",
                Role.GUEST
        );

        SecurityCommand createOrderCommand = new SecurityCommand(
                SecurityCommandType.CREATE_ORDER,
                "user@example.com",
                Role.USER
        );

        SecurityCommand reportCommand = new SecurityCommand(
                SecurityCommandType.ADMIN_REPORT,
                "user@example.com",
                Role.USER
        );

        loginHandler.handle(loginCommand);
        loginHandler.handle(createOrderCommand);
        loginHandler.handle(reportCommand);
    }
}
