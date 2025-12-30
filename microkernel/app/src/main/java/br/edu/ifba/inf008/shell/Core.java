package br.edu.ifba.inf008.shell;

// Importando bibliotecas internas
import br.edu.ifba.inf008.interfaces.IAuthenticationController;
import br.edu.ifba.inf008.interfaces.ICore;
import br.edu.ifba.inf008.interfaces.IDatabaseController;
import br.edu.ifba.inf008.interfaces.IIOController;
import br.edu.ifba.inf008.interfaces.IPluginController;
import br.edu.ifba.inf008.interfaces.IUIController;

// Classe que representa o núcleo (core) da aplicação
// Centraliza o acesso aos principais controladores do sistema
public class Core extends ICore {

    // Padrão Singleton garante que 'Core' só possa ser instanciado internamente
    private Core() {} // Construtor privado para impedir criação direta de instâncias

    // Método responsável por inicializar o núcleo da aplicação e a interface gráfica
    public static boolean init() {

        // Verifica se o Core já foi inicializado
        // Evita múltiplas instâncias do núcleo
        if (instance != null) {
            System.out.println("Fatal error: core is already initialized!");
            System.exit(-1);
        }

        // Cria a única instância de 'Core'
        // 'instance' já foi declarada na classe abstrata ICore
        instance = new Core();

        // Dispara o carregamento do UIController, responsável por gerenciar a interface gráfica
        // Inicia o framework JavaFX chamando o ponto de entrada da interface gráfica
        // Quando o método launch, da classe Application, é chamado, o JavaFX internamente:
            // Cria uma instância do UIController
            // Chama o método 'init'
            // Chama o método 'start'
        UIController.launch(UIController.class);

        return true;
    }

    // Inicializa os controladores
    private final IAuthenticationController authenticationController = new AuthenticationController();
    private final IIOController ioController = new IOController();
    private final IPluginController pluginController = new PluginController();
    private final IDatabaseController databaseController = new DatabaseController();

    // Getters
    //
    // Retorna o controlador responsável pela interface gráfica
    @Override
    public IUIController getUIController() {
        return UIController.getInstance();
    }

    // Retorna o controlador responsável pela autenticação (login, logout, cadastro)
    @Override
    public IAuthenticationController getAuthenticationController() {
        return authenticationController;
    }

    // Retorna o controlador responsável pelas operações de entrada e saída de dados
    @Override
    public IIOController getIOController() {
        return ioController;
    }

    // Retorna o controlador responsável por carregar e gerenciar plugins
    @Override
    public IPluginController getPluginController() {
        return pluginController;
    }

    // Retorna o controlador responsável por estabelecer a conexão com a base de dados
    @Override 
    public IDatabaseController getDatabaseController() {
        return databaseController;
    }
}
