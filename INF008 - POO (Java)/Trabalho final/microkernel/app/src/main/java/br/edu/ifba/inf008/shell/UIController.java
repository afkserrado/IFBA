package br.edu.ifba.inf008.shell;

import java.util.List;

import br.edu.ifba.inf008.interfaces.IScreen;
import br.edu.ifba.inf008.interfaces.IUIController;
import br.edu.ifba.inf008.screens.MainScreen;
import br.edu.ifba.inf008.screens.WelcomeScreen;
import javafx.application.Application;      
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;

/**
 * Controller responsável por inicializar e coordenar a interface gráfica JavaFX do sistema.
 *
 * <p>Esta classe atua como:</p>
 * <ul>
 *   <li>Ponto de entrada do JavaFX por estender {@link Application} (ciclo de vida: {@link #init()} e {@link #start(Stage)}).</li>
 *   <li>Fachada/ponte entre o núcleo ({@link Core}) e as funcionalidades de UI expostas por {@link IUIController}.</li>
 * </ul>
 *
 * <p>A troca de telas é feita atualizando o nó raiz ({@code root}) da {@link Scene} por meio de {@link Scene#setRoot(javafx.scene.Parent)}.</p>
 */
public class UIController extends Application implements IUIController {

    private Parent root; // Contêiner que armazena o conteúdo visual da tela criada 
    private MenuBar menuBar; // Barra de menu superior da janela da aplicação
    private TabPane tabPane; // Contêiner central que mantém múltiplas abas (área principal de conteúdo)
    private Scene scene; // Contêiner que contém o contêiner de elementos visuais
    private Stage primaryStage; // Guardar a scene (tela em execução)
    private static UIController uiController; // Referência estática usada para implementar acesso singleton ao UIController
    private IScreen welcomeScreen;
    private IScreen mainScreen;

    /**
     * Construtor padrão exigido pelo JavaFX.
     *
     * <p>O runtime do JavaFX cria a instância da classe que estende {@link Application} antes de chamar {@link #init()} e {@link #start(Stage)}.</p>
     */
    public UIController() {}

    /**
     * Inicializa o controller antes da exibição da interface.
     *
     * <p>Faz a vinculação da instância atual ao atributo estático usado como acesso tipo Singleton.</p>
     */
    @Override
    public void init() {
        uiController = this;
    }

    /**
     * Retorna a instância atual do {@link UIController} criada pelo JavaFX.
     *
     * <p>O método é package-private para incentivar que o acesso ocorra via {@link Core}, evitando dependência direta de plug-ins com a implementação concreta de UI.</p>
     *
     * @return instância em execução do {@link UIController}
     */
    static UIController getInstance() {
        return uiController;
    }

    /**
     * Monta e exibe a interface gráfica na janela principal.
     *
     * <p>No ciclo de vida do JavaFX, {@link #start(Stage)} é chamado após {@link #init()} e já com o runtime pronto para renderização da GUI.</p>
     *
     * @param primaryStage janela principal fornecida pelo JavaFX
     */
    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        
        // Define o título da janela fornecida pelo sistema operacional
        primaryStage.setTitle("LokiCar");

        // Cria a tela de boas-vindas
        createWelcomeScreen();

        // Cria uma Scene e passa para a janela do SO
        scene = new Scene(root, 1920, 1080);
        primaryStage.setScene(scene);

        primaryStage.show(); // Exibe a janela fornecida pelo SO
    }

    /**
     * Cria a tela de boas-vindas e a define como tela corrente.
     *
     * <p>A {@link WelcomeScreen} recebe uma ação a ser executada no clique do botão "LOGAR", que direciona o fluxo para a criação da tela principal.</p>
     */
    private void createWelcomeScreen() {
        // Cria a WelcomeScreen passando a ação: "quando o botão 'LOGAR' for clicado, mostre a MainScreen"
        welcomeScreen = new WelcomeScreen(() -> createMainScreen());
        setScreen(welcomeScreen);
    }

    /**
     * Cria a tela principal (menu + abas), define como tela corrente e inicializa os plug-ins.
     *
     * <p>A inicialização do {@link br.edu.ifba.inf008.interfaces.IPluginController} é realizada após a GUI principal ser montada, permitindo que plug-ins registrem menus/abas na aplicação.</p>
     */
    private void createMainScreen() {
        
        // Inicializa a barra de menus e o contêiner de abas
        menuBar = new MenuBar();
        tabPane = new TabPane();
        
        mainScreen = new MainScreen(menuBar, tabPane);
        setScreen(mainScreen);

        // Inicializa os plugins após a GUI ter sido criada e exibida
            // Obtém a instância do Core em execução
            // Obtém a instância do PluginController associada ao Core em execução
            // Inicializa o PluginController
        Core.getInstance().getPluginController().init();
    }

    /**
     * Troca a tela atual atualizando o nó raiz da {@link Scene}.
     *
     * <p>Se a {@link Scene} já tiver sido criada, usa {@link Scene#setRoot(javafx.scene.Parent)} para substituir o conteúdo exibido sem trocar a instância de {@link Scene}.</p>
     *
     * @param screen tela a ser exibida
     */
    private void setScreen(IScreen screen) {
        root = screen.createScreen();
        
        // Se Scene já foi criada, atualiza o root
        if (scene != null) {
            scene.setRoot(root);
        }
    }

    /**
     * Cria (ou reutiliza) um {@link Menu} e adiciona um novo {@link MenuItem} a ele.
     *
     * <p>Se não existir um menu com o texto {@code menuText} na {@link MenuBar}, um novo menu é criado e adicionado; em seguida, o item {@code menuItemText} é criado e anexado ao menu.</p>
     *
     * @param menuText texto do menu (ex.: "Arquivo")
     * @param menuItemText texto do item (ex.: "Abrir")
     * @return {@link MenuItem} criado (para permitir que o chamador configure eventos, por exemplo)
     */
    @Override
    public MenuItem createMenuItem(String menuText, String menuItemText) {
        
        // Tenta localizar um menu existente com o texto informado
        Menu newMenu = null;
        for (Menu menu : menuBar.getMenus()) {
            if (menu.getText().equals(menuText)) {
                newMenu = menu;
                break;
            }
        }

        // Caso o menu não exista, cria um novo e o adiciona à barra de menus
        if (newMenu == null) {
            newMenu = new Menu(menuText);
            menuBar.getMenus().add(newMenu);
        }

        // Cria um novo item de menu e o adiciona ao menu selecionado
        MenuItem menuItem = new MenuItem(menuItemText);
        newMenu.getItems().add(menuItem);

        // Retorna o item de menu para permitir configurações adicionais (ex.: eventos)
        return menuItem;
    }

    /**
     * Cria uma nova aba no {@link TabPane} e define seu conteúdo.
     *
     * @param tabText título da aba
     * @param contents conteúdo visual (nó JavaFX) a ser exibido na aba
     * @return {@code true} se a aba foi criada e adicionada ao {@link TabPane}
     */
    @Override
    public boolean createTab(String tabText, Node contents) {
        
        // Cria uma nova instância de Tab
        Tab tab = new Tab();

        // Define o título da aba
        tab.setText(tabText);

        // Define o conteúdo visual da aba
        tab.setContent(contents);

        // Adiciona a aba ao TabPane
        tabPane.getTabs().add(tab);

        // Indica que a aba foi criada com sucesso
        return true;
    }

    /**
     * Adiciona nós visuais ao conteúdo central da tela principal.
     *
     * @param nodes lista de nós JavaFX a serem adicionados na {@link MainScreen}
     * @return {@code true} se os nós foram encaminhados para a tela principal com sucesso
     */
    @Override
    public boolean addMainNodes(List<Node> nodes) {
        MainScreen ms = (MainScreen) mainScreen;
        ms.addContent(nodes);
        return true;
    }
}
