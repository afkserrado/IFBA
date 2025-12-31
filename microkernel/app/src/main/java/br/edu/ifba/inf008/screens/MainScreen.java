package br.edu.ifba.inf008.screens;

import br.edu.ifba.inf008.interfaces.IScreen;
import javafx.scene.Node;
import javafx.scene.layout.VBox;

public class MainScreen implements IScreen {

    @Override
    public Node create() {

        return new VBox();
    }
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

