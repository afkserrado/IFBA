package br.edu.ifba.inf008.shell;

// Importando bibliotecas internas
import br.edu.ifba.inf008.interfaces.ICore;
import br.edu.ifba.inf008.interfaces.IUIController;
import javafx.application.Application;   // Classe base de qualquer aplicação JavaFX; define o ciclo de vida da aplicação
import javafx.scene.Node;             // Enum que define posições laterais (TOP, BOTTOM, LEFT, RIGHT)
import javafx.scene.control.Menu;                // Componente de interface que pode ser usado como conteúdo em layouts
import javafx.scene.control.MenuBar;               // Representa um contêiner para todo o conteúdo visual
import javafx.scene.control.MenuItem;        // Representa um menu suspenso que agrupa vários MenuItems
import javafx.scene.control.Tab;     // Representa a barra de menu superior de uma janela
import javafx.scene.control.TabPane;    // Representa um item de ação dentro de um Menu (não faz parte da Scene Graph)
import javafx.stage.Stage;         // Representa uma aba individual; possui um título e um Node como conteúdo

// Classe responsável por controlar a interface gráfica da aplicação
// Atua como ponto de entrada para o JavaFX e como uma fachada entre o núcleo da aplicação e a interface gráfica gerenciada pelo JavaFX
// UIController não é a interface gráfica em si: ele a constrói e controla
public class UIController extends Application implements IUIController {

    private ICore core;                         // Referência para a camada do núcleo
    private MenuBar menuBar;                    // Barra de menu superior da janela da aplicação
    private TabPane tabPane;                    // Contêiner central que mantém múltiplas abas (área principal de conteúdo)
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
        
        // Define o título da janela, fornecido pelo sistema operacional
        primaryStage.setTitle("LokiCar");

        VBox welcomeScreen = new VBox();
        

        
        // // Define o título da janela, fornecido pelo sistema operacional
        // primaryStage.setTitle("Hello World!");

        // // Cria a barra de menus que aparecerá no topo da janela
        // menuBar = new MenuBar();

        // // Cria um layout vertical
        // // Organiza a hierarquia visual
        //     // VBox
        //         // MenuBar
        //             // Menu
        //                 // MenuItem
        //         // TabPane
        //             // Tab
        //                 // Node
        // VBox vBox = new VBox(menuBar); // Adiciona a barra de menus como o primeiro filho

        // // Cria o TabPane que conterá as principais visualizações da aplicação
        // tabPane = new TabPane();

        // // Posiciona as abas na parte inferior do TabPane
        // tabPane.setSide(Side.BOTTOM);

        // // Adiciona o TabPane abaixo da barra de menus no layout vertical
        // vBox.getChildren().addAll(tabPane);

        // // Cria a Scene usando o VBox como nó raiz
        // // Contém cada conteúdo visual
        // Scene scene = new Scene(vBox, 960, 600);

        // // Associa a Scene ao Stage (janela)
        // primaryStage.setScene(scene);

        // // Exibe a janela na tela
        // primaryStage.show();

        // // Inicializa os plugins após a GUI ter sido criada e exibida
        //     // Obtém a instância do Core em execução
        //     // Obtém a instância do PluginController associada ao Core em execução
        //     // Inicializa o PluginController
        // Core.getInstance().getPluginController().init();
    }

    // Cria (ou recupera) um menu e adiciona um novo item de menu a ele
    // Retorna o MenuItem criado para que ações possam ser associadas
    public MenuItem createMenuItem(String menuText, String menuItemText) {
        // Tenta localizar um menu existente com o texto informado
        Menu newMenu = null;
        for (Menu menu : menuBar.getMenus()) {
            if (menu.getText() == menuText) {
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
