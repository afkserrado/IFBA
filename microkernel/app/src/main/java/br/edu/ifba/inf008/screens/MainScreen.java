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
 * Tela principal do sistema.
 *
 * <p>Responsável por compor a estrutura base da aplicação, exibindo a barra de menus
 * e a área de abas (tabs) onde funcionalidades (incluindo plug-ins) podem apresentar conteúdo.</p>
 *
 * <p>Implementa {@link br.edu.ifba.inf008.interfaces.IScreen}, fornecendo o nó raiz
 * do layout por meio de {@link #createScreen()}.</p>
 */
public class MainScreen implements IScreen {

    /** Barra de menu superior da janela da aplicação. */
    private final MenuBar menuBar;
    
    /** Contêiner central que mantém múltiplas abas. */
    private final TabPane tabPane;
    
    /** Layout raiz da tela, usado para posicionar menu, abas e conteúdo central. */
    private final BorderPane root = new BorderPane();
    
    /** Contêiner principal para componentes exibidos no centro da tela. */
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
     * Cria e retorna o conteúdo visual da tela principal.
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
     * @return {@link Parent} contendo todos os elementos da tela
     */
    @Override
    public Parent createScreen() {

        // Posiciona as abas na parte inferior
        tabPane.setSide(Side.TOP);

        // Define a estrutura do BorderPane
        root.setTop(menuBar);
        root.setBottom(tabPane);
        root.setCenter(mainBox);

        // Formatação
        menuBar.setStyle(
            "-fx-font-size: 24px;"
        );

        tabPane.setStyle(
            "-fx-font-size: 24px;"
        );
        tabPane.setPrefHeight(600);

        mainBox.setAlignment(Pos.CENTER);

        // Desabilita a opção de fechar uma aba
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

        return root;
    }

    /**
     * Adiciona uma lista de nós ao contêiner central da tela.
     *
     * <p>Os nós são adicionados ao {@link VBox} {@code mainBox}, preservando a ordem
     * fornecida na lista.</p>
     *
     * @param nodes Lista de componentes JavaFX a serem adicionados ao conteúdo central
     * @throws NullPointerException se {@code nodes} for {@code null}
     */
    public void addContent(List<Node> nodes) {
        mainBox.getChildren().addAll(nodes);
    }
}