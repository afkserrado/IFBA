package ifba.inf011.chain_of_responsibility.hierarchy;

// Handler
public abstract class SecurityHelpNode {

    private final SecurityHelpNode parent;
    private final String helpText;

    protected SecurityHelpNode(SecurityHelpNode parent, String helpText) {
        this.parent = parent;
        this.helpText = helpText;
    }

    public void showSecurityHelp() {
        if (this.helpText != null && !this.helpText.isBlank()) {
            System.out.println(this.helpText);
            return;
        }

        if (this.parent != null) {
            this.parent.showSecurityHelp();
            return;
        }

        System.out.println("Ajuda genérica: consulte a política geral de acesso.");
    }
}
