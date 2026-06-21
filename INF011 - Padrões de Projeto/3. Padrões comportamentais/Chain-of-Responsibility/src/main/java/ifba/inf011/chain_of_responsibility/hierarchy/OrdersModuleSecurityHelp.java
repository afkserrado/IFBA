package ifba.inf011.chain_of_responsibility.hierarchy;

// Concrete Handler
public class OrdersModuleSecurityHelp extends SecurityHelpNode {

    public OrdersModuleSecurityHelp(SecurityHelpNode parent) {
        super(parent, "Ajuda do módulo de pedidos: criar pedidos exige usuário autenticado.");
    }
}
