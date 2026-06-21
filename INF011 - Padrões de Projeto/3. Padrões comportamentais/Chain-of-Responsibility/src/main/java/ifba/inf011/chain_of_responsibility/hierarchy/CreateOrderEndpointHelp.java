package ifba.inf011.chain_of_responsibility.hierarchy;

// Concrete Handler
public class CreateOrderEndpointHelp extends SecurityHelpNode {

    public CreateOrderEndpointHelp(SecurityHelpNode parent, String helpText) {
        super(parent, helpText);
    }
}
