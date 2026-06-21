package ifba.inf011.chain_of_responsibility.hierarchy;

// Concrete Handler
public class ApplicationSecurityHelp extends SecurityHelpNode {

    public ApplicationSecurityHelp() {
        super(null, "Ajuda da aplicação: todas as requisições passam por controle de acesso.");
    }
}
