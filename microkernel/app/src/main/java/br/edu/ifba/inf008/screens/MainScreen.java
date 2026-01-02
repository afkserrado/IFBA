package br.edu.ifba.inf008.screens;

import br.edu.ifba.inf008.interfaces.IScreen;
import javafx.geometry.Side;
import javafx.scene.Parent;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TabPane;     // Representa a barra de menu superior de uma janela
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;    // Representa um item de ação dentro de um Menu (não faz parte da Scene Graph)

/**
 * Tela principal do sistema LokiCar.
 * Exibe o menu de opções e as abas de conteúdo.
 */
public class MainScreen implements IScreen {

    private final MenuBar menuBar; // Barra de menu superior da janela da aplicação
    private final TabPane tabPane; // Contêiner central que mantém múltiplas abas

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

        // Cria o contêiner principal
        VBox mainBox = new VBox();

        // Posiciona as abas na parte inferior
        tabPane.setSide(Side.BOTTOM);

        // Faz TabPane crescer verticalmente para ocupar espaço disponível
        VBox.setVgrow(tabPane, Priority.ALWAYS);

        // Adiciona a barra de menus e o contêiner de abas ao contêiner principal
        mainBox.getChildren().addAll(menuBar, tabPane);

        return mainBox;
    }
}