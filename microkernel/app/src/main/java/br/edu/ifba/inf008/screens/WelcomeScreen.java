package br.edu.ifba.inf008.screens;

import br.edu.ifba.inf008.interfaces.IScreen;   // Interface para telas da aplicação

import javafx.geometry.Insets;                  // Margens e padding (espaçamento interno/externo)
import javafx.geometry.Pos;                     // Posicionamento/alinhamento de elementos
import javafx.scene.Node;                       // Classe pai de todos elementos visuais do JavaFX
import javafx.scene.control.Button;             // Botão
import javafx.scene.control.Label;              // Texto/rótulo
import javafx.scene.control.Separator;          // Linha separadora horizontal/vertical
import javafx.scene.layout.HBox;                // Contêiner com layout horizontal
import javafx.scene.layout.Priority;            // Prioridade de crescimento de elementos em layouts
import javafx.scene.layout.Region;              // Região vazia (usada como espaçador)
import javafx.scene.layout.VBox;                // Contêiner com layout vertical

/**
 * Tela de boas-vindas do sistema LokiCar.
 * Exibe logotipo, mensagem de boas-vindas e botão de login.
 */
public class WelcomeScreen implements IScreen {

    private final Runnable onLoginClick; // Guarda a ação do botão de login

    /**
     * Construtor da tela de boas-vindas
     *
     * @param onLoginClick Ação a ser executada quando o botão LOGAR for clicado
     */
    public WelcomeScreen(Runnable onLoginClick) {
        this.onLoginClick = onLoginClick; // Recebe uma ação (função anônima) do UIController
    }

    /**
     * Cria e retorna o conteúdo visual da tela de boas-vindas
     *
     * @return Node contendo todos os elementos da tela
     */
    @Override
    public Node create() {

        // Cria o contêiner principal com espaçamento de n píxeis entre os filhos
        VBox welcomeBox = new VBox(20);
        welcomeBox.setAlignment(Pos.CENTER); // Alinha o conteúdo no centro
        welcomeBox.setStyle("-fx-background-color: black;"); // Define a cor do fundo

        // Cria e formata os elementos visuais do contêiner principal
        Label title = new Label("\uD83D\uDE97 Loki");
        title.setStyle(
            "-fx-font-size: 48px;" + 
            "-fx-text-fill: white;");

        Label subtitle = new Label("Você não precisa ser o Thor para voar! ⚡");
        subtitle.setStyle(
            "-fx-font-size: 24px;" + 
            "-fx-text-fill: white;");

        Label presentation = new Label("Seja bem vindo(a) ao sistema LokiCar.");
        presentation.setStyle(
            "-fx-font-size: 12px;" + 
            "-fx-text-fill: white;");

        Button loginButton = new Button("LOGAR");
        loginButton.setPadding(new Insets(10, 40, 10, 40));
        loginButton.setOnAction(e -> onLoginClick.run()); // Executa a ação ao clicar no botão

        // Cria uma região vazia para preencher o espaço entre os elementos visuais do contêiner principal e o rodapé
        Region spacer = new Region();
        VBox.setVgrow(spacer, Priority.ALWAYS);  // Força o spacer a sempre ocupar todo o espaço disponível dentro do contêiner, empurrando o rodapé para o fundo da VBox

        // Linha para separar o rodapé
        Separator separator = new Separator();
        separator.setStyle("-fx-background-color: white;");
        
        // Cria um contêiner para o rodapé com um espaçamento de n píxeis entre os filhos
        HBox footerBox = new HBox(5);
        footerBox.setAlignment(Pos.CENTER);
        footerBox.setPadding(new Insets(10)); // Margens internas
        
        Label version = new Label("Versão 1.0");
        version.setStyle(
            "-fx-font-size: 8px;" +
            "-fx-text-fill: white;"
            );

        Label footerText = new Label(" | INF008 | IFBA");
        footerText.setStyle(
            "-fx-font-size: 8px;" +
            "-fx-text-fill: white;"
            );

        // Adiciona os elementos ao contêiner do rodapé
        footerBox.getChildren().addAll(
            version,
            footerText
        );    

        // Adiciona os elementos ao contêiner principal
        welcomeBox.getChildren().addAll(
            title,
            subtitle,
            presentation,
            loginButton,
            spacer,
            separator,
            footerBox
        );

        return welcomeBox;
    }
}
