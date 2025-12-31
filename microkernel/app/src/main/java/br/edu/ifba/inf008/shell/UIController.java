package br.edu.ifba.inf008.shell;

// Importando bibliotecas internas
import br.edu.ifba.inf008.interfaces.ICore;
import br.edu.ifba.inf008.interfaces.IScreen;
import br.edu.ifba.inf008.interfaces.IUIController;
import br.edu.ifba.inf008.screens.MainScreen;
import br.edu.ifba.inf008.screens.WelcomeScreen;   // Classe base de qualquer aplicação JavaFX; define o ciclo de vida da aplicação
import javafx.application.Application;             // Enum que define posições laterais (TOP, BOTTOM, LEFT, RIGHT)
import javafx.scene.Node;                // Componente de interface que pode ser usado como conteúdo em layouts
import javafx.scene.Parent;               // Representa um contêiner para todo o conteúdo visual
import javafx.scene.Scene;        // Representa um menu suspenso que agrupa vários MenuItems
import javafx.scene.control.Menu;     // Representa a barra de menu superior de uma janela
import javafx.scene.control.MenuBar;    // Representa um item de ação dentro de um Menu (não faz parte da Scene Graph)
import javafx.scene.control.MenuItem;         // Representa uma aba individual; possui um título e um Node como conteúdo
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;

// Classe responsável por controlar a interface gráfica da aplicação
// Atua como ponto de entrada para o JavaFX e como uma fachada entre o núcleo da aplicação e a interface gráfica gerenciada pelo JavaFX
// UIController não é a interface gráfica em si: ele a constrói e controla
public class UIController extends Application implements IUIController {

    private ICore core;                         // Referência para a camada do núcleo
    private MenuBar menuBar;                    // Barra de menu superior da janela da aplicação
    private TabPane tabPane;                    // Contêiner central que mantém múltiplas abas (área principal de conteúdo)
    private Stage primaryStage;                 // Guardar a scene (tela em execução)
    private static UIController uiController;   // Referência estática usada para implementar acesso singleton ao UIController

    // Construtor padrão (exigido pelo JavaFX)
    public UIController() {
    }

    // Método chamado antes de 'start()'; usado aqui para inicializar a instância singleton
    @Override
    public void init() {
        uiController = this;
    }

    // Fornece acesso global à instância atual do UIController
    public static UIController getInstance() {
        return uiController;
    }

    // Método principal do ciclo de vida do JavaFX; responsável por construir e exibir a GUI
    @Override
    public void start(Stage primaryStage) {
        uiController = this;
        this.primaryStage = primaryStage;

        // Define o título da janela, fornecido pelo sistema operacional
        primaryStage.setTitle("LokiCar");

        showWelcomeScreen();
        primaryStage.show();
    }

    /**
     *
     * Exibe a tela de boas-vindas (WelcomeScreen)
     */
    private void showWelcomeScreen() {
        // Cria a WelcomeScreen passando a ação: "quando o botão 'LOGAR' for clicado, mostre a MainScreen"
        IScreen welcomeScreen = new WelcomeScreen(() -> showMainScreen());
        showScreen(welcomeScreen);
    }

    /**
     * Exibe a tela principal com menu e tabs (MainScreen)
     */
    private void showMainScreen() {
        MainScreen mainScreen = new MainScreen();
        showScreen(mainScreen);

        //Core.getInstance().getPluginController().init();
        System.out.println("✅ MainScreen exibida! (teste)");
    }

    /**
     * Método genérico para trocar a tela (Scene) exibida
     *
     * @param screen Tela a ser exibida
     */
    private void showScreen(IScreen screen) {
        Node content = screen.create();

        // Cria a Scene usando o VBox como nó raiz
        // Contém cada conteúdo visual
        Scene scene = new Scene((Parent) content, 960, 600);
        primaryStage.setScene(scene);
    }

    // Cria (ou recupera) um menu e adiciona um novo item de menu a ele
    // Retorna o MenuItem criado para que ações possam ser associadas
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
