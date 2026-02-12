package br.edu.ifba.inf008.shell;

import br.edu.ifba.inf008.interfaces.IAuthenticationController;
import br.edu.ifba.inf008.interfaces.ICore;
import br.edu.ifba.inf008.interfaces.IDatabaseController;
import br.edu.ifba.inf008.interfaces.IIOController;
import br.edu.ifba.inf008.interfaces.IPluginController;
import br.edu.ifba.inf008.interfaces.IUIController;

/**
 * Implementação concreta do núcleo (core) da aplicação.
 *
 * <p>Centraliza o acesso aos principais controladores do sistema (UI, autenticação,
 * entrada/saída, plug-ins e banco de dados), seguindo o contrato definido por {@link ICore}.</p>
 *
 * <p>Esta classe participa de um padrão de instância única (Singleton) por meio do campo {@code instance} definido em {@link ICore} e do construtor privado, evitando que código cliente crie múltiplas instâncias diretamente.</p>
 */
public class Core extends ICore {

    /**
     * Construtor privado para impedir criação direta de instâncias do núcleo.
     *
     * <p>O acesso ao núcleo deve ocorrer via {@link ICore#getInstance()}, que retorna a instância armazenada em {@link ICore}.</p>
     */
    private Core() {}

    /**
     * Inicializa o núcleo da aplicação e dispara o bootstrap da interface gráfica JavaFX.
     *
     * <p>Este método cria a instância única do core (armazenada em {@link ICore}) e chama {@code Application.launch(...)} via {@link UIController#launch(Class, String...)}, o que inicializa o runtime do JavaFX e só deve ser executado uma vez durante o ciclo de vida da aplicação.</p>
     *
     * @return {@code true} se a inicialização foi disparada com sucesso
     */
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

        // Observação: pela especificação do JavaFX, o método launch não deve ser chamado mais de uma vez durante a execução da aplicação
        UIController.launch(UIController.class);

        return true;
    }

    // Inicializa os controladores
    //
    /** Controlador responsável por autenticação (login/logout/cadastro). */
    private final IAuthenticationController authenticationController = new AuthenticationController();
    
    /** Controlador responsável por operações de entrada e saída (I/O) do sistema. */
    private final IIOController ioController = new IOController();

    /** Controlador responsável por registro, carregamento e acesso a plug-ins. */
    private final IPluginController pluginController = new PluginController();
    
    /** Controlador responsável por acesso ao banco de dados (conexões e queries). */
    private final IDatabaseController databaseController = new DatabaseController();

    // Getters
    //
    /**
     * Retorna o controlador responsável pela interface gráfica.
     *
     * @return instância do {@link IUIController} provida por {@link UIController#getInstance()}
     */
    @Override
    public IUIController getUIController() {
        return UIController.getInstance();
    }

    /**
     * Retorna o controlador responsável por autenticação (login, logout e cadastro).
     *
     * @return controlador de autenticação do sistema
     */
    @Override
    public IAuthenticationController getAuthenticationController() {
        return authenticationController;
    }

    /**
     * Retorna o controlador responsável pelas operações de entrada e saída (I/O).
     *
     * @return controlador de I/O do sistema
     */
    @Override
    public IIOController getIOController() {
        return ioController;
    }

    /**
     * Retorna o controlador responsável por carregar, registrar e gerenciar plug-ins.
     *
     * @return controlador de plug-ins do sistema
     */
    @Override
    public IPluginController getPluginController() {
        return pluginController;
    }

    /**
     * Retorna o controlador responsável por estabelecer conexões e executar operações de banco de dados.
     *
     * @return controlador de banco de dados do sistema
     */
    @Override 
    public IDatabaseController getDatabaseController() {
        return databaseController;
    }
}
