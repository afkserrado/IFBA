package br.edu.ifba.inf008.shell;

// Importando bibliotecas internas
import br.edu.ifba.inf008.interfaces.ICore;
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
 * Controlador principal da interface gráfica do sistema LokiCar.
 * Gerencia a criação de telas, menus e abas, atuando como ponte
 * entre o núcleo da aplicação e o JavaFX.
 */

// Atua como ponto de entrada para o JavaFX e como uma fachada entre o núcleo da aplicação e a interface gráfica gerenciada pelo JavaFX
// UIController não é a interface gráfica em si: ele a constrói e controla
public class UIController extends Application implements IUIController {

    private ICore core;                         // Referência para a camada do núcleo
    private MenuBar menuBar;                    // Barra de menu superior da janela da aplicação
    private TabPane tabPane;                    // Contêiner central que mantém múltiplas abas (área principal de conteúdo)
    private Stage primaryStage;                 // Guardar a scene (tela em execução)
    private static UIController uiController;   // Referência estática usada para implementar acesso singleton ao UIController

    // Construtor padrão (exigido pelo JavaFX)
    // Invocado pelo próprio JavaFX
    // JavaFX exige que seja public
    public UIController() {
    }

    // Inicializa a instância singleton
    // Invocado pelo próprio JavaFX após o construtor e antes do start() 
    // JavaFX exige que seja public
    @Override
    public void init() {
        uiController = this;
    }

    // Fornece acesso global à instância atual do UIController
    public static UIController getInstance() {
        return uiController;
    }

    // Constrói e exibie a GUI
    // Invocado pelo próprio JavaFX após o init()
    // JavaFX exige que seja public
    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;

        // Define o título da janela fornecida pelo sistema operacional
        primaryStage.setTitle("LokiCar");

        createWelcomeScreen(); // Cria a tela de boas-vindas
        primaryStage.show(); // Exibe a janela fornecida pelo SO
    }

    /**
     * Cria a tela de boas-vindas (WelcomeScreen)
     */
    private void createWelcomeScreen() {
        // Cria a WelcomeScreen passando a ação: "quando o botão 'LOGAR' for clicado, mostre a MainScreen"
        IScreen welcomeScreen = new WelcomeScreen(() -> createMainScreen());
        setScreen(welcomeScreen);
    }

    /**
     * Cria a tela principal com menu e tabs (MainScreen)
     */
    private void createMainScreen() {
        
        // Inicializa a barra de menus e o contêiner de abas
        menuBar = new MenuBar();
        tabPane = new TabPane();
        
        IScreen mainScreen = new MainScreen(menuBar, tabPane);
        setScreen(mainScreen);

        // Inicializa os plugins após a GUI ter sido criada e exibida
            // Obtém a instância do Core em execução
            // Obtém a instância do PluginController associada ao Core em execução
            // Inicializa o PluginController
        Core.getInstance().getPluginController().init();
    }

    /**
     * Método genérico para trocar a tela (Scene) exibida
     *
     * @param screen Tela a ser exibida
     */
    private void setScreen(IScreen screen) {
        Parent content = screen.createScreen(); // Obtém o conteúdo visual da tela criada

        // Cria uma Scene e passa para a janela do SO
        Scene scene = new Scene(content, 960, 600);
        primaryStage.setScene(scene);
    }

    /**
     * Cria um item de menu na barra de menus
     * 
     * @param menuText Texto do menu (ex: "Arquivo")
     * @param menuItemText Texto do item (ex: "Abrir")
     * @return MenuItem criado, permitindo adicionar ações
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
     * Cria uma nova aba no TabPane
     * 
     * @param tabText Título da aba
     * @param contents Conteúdo visual da aba
     * @return true se a aba foi criada com sucesso
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
}
