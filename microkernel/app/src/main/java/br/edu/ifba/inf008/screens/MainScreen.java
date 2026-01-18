package br.edu.ifba.inf008.screens;

import java.util.List;

import br.edu.ifba.inf008.interfaces.IScreen;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

/**
 * Tela principal do sistema LokiCar.
 * Exibe o menu de opções e as abas de conteúdo.
 */
public class MainScreen implements IScreen {

    private final MenuBar menuBar; // Barra de menu superior da janela da aplicação
    private final TabPane tabPane; // Contêiner central que mantém múltiplas abas
    private final BorderPane root = new BorderPane();
    private final VBox mainBox = new VBox();

    /**
     * Construtor da tela principal
     *
     * @param menuBar Barra de menu superior
     * @param tabPane Contêiner de abas
     */
    public MainScreen(MenuBar menuBar, TabPane tabPane) {
        this.menuBar = menuBar;
        this.tabPane = tabPane;
    }

    /**
     * Cria e retorna o conteúdo visual da tela principal
     *
     * <p>Hierarquia visual:</p>
     * <pre>
     * VBox (mainBox)
     *   ├─ MenuBar
     *   │   └─ Menu
     *   │       └─ MenuItem
     *   └─ TabPane
     *       └─ Tab
     *           └─ Node (conteúdo)
     * </pre>
     *
     * @return Parent contendo todos os elementos da tela
     */
    @Override
    public Parent createScreen() {

        // Posiciona as abas na parte inferior
        tabPane.setSide(Side.BOTTOM);

        root.setTop(menuBar);
        root.setBottom(tabPane);
        root.setCenter(mainBox);

        menuBar.setStyle(
            "-fx-font-size: 24px;"
        );

        tabPane.setStyle(
            "-fx-font-size: 24px;"
        );

        mainBox.setAlignment(Pos.CENTER);

        return root;
    }

    public void addContent(List<Node> nodes) {
        mainBox.getChildren().addAll(nodes);
    }
}