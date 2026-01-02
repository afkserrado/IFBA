package br.edu.ifba.inf008.screens;

import java.util.Objects;

import br.edu.ifba.inf008.interfaces.IScreen;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

/**
 * Tela de boas-vindas do sistema LokiCar.
 * Exibe logotipo, mensagem de boas-vindas e botão de login.
 */
public class WelcomeScreen implements IScreen {

    private final Runnable onLoginClick; // Guarda a ação do botão de login

    // ========== CONSTANTES DE ESTILO ==========
    private static final String ICONS_PATH = "/icons/";
    
    // Tamanhos de fonte
    private static final double TITLE_FONT_SIZE = 116.0;
    private static final double SUBTITLE_FONT_SIZE = 96.0;
    private static final double PATTERN_FONT_SIZE = 48.0;
    private static final double FOOTER_FONT_SIZE = 36.0;

    // Tamanhos de ícones (proporcionais às fontes)
    private static final double TITLE_ICON_SIZE = TITLE_FONT_SIZE * 0.7;
    private static final double SUBTITLE_ICON_SIZE = SUBTITLE_FONT_SIZE * 0.7;

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
     * @return Parent contendo todos os elementos da tela
     */
    @Override
    public Parent createScreen() {

        // Cria o contêiner principal com espaçamento de n píxeis entre os filhos
        VBox welcomeBox = new VBox(20);
        welcomeBox.setAlignment(Pos.CENTER); // Alinha o conteúdo no centro
        welcomeBox.setStyle("-fx-background-color: black;"); // Define a cor do fundo

        // ========== TÍTULO ==========

        // Cria um ícone de carro
        ImageView carIcon = createIcon("car.png", TITLE_ICON_SIZE);

        // Cria e formata os elementos visuais do contêiner principal
        Label title = new Label("LokiCar");
        title.setStyle(
            "-fx-font-size: " + TITLE_FONT_SIZE + "px;" + 
            "-fx-text-fill: white;"
        );

        // Cria um contêiner para o título
        HBox titleBox = new HBox(20, carIcon, title);
        titleBox.setAlignment(Pos.BASELINE_CENTER);

        // ========== SUBTÍTULO ==========

        // Cria um ícone de raio
        ImageView boltIcon = createIcon("bolt.png", SUBTITLE_ICON_SIZE);

        Label subtitle = new Label("Você não precisa ser o Thor para voar!");
        subtitle.setStyle(
            "-fx-font-size: " + SUBTITLE_FONT_SIZE + "px;" + 
            "-fx-text-fill: white;"
        );

        // Cria um contêiner para o subtítulo
        HBox subtitleBox = new HBox(20, subtitle, boltIcon);
        subtitleBox.setAlignment(Pos.BASELINE_CENTER);

        // ========== APRESENTAÇÃO E BOTÃO DE LIGAR ==========

        Label presentation = new Label("Seja bem vindo(a) ao sistema LokiCar.");
        presentation.setStyle(
            "-fx-font-size: " + PATTERN_FONT_SIZE + "px;" + 
            "-fx-text-fill: white;"
        );

        Button loginButton = new Button("LOGAR");
        loginButton.setPadding(new Insets(10, 40, 10, 40));
        loginButton.setStyle(
            "-fx-font-size: " + PATTERN_FONT_SIZE + "px;" + 
            "-fx-text-fill: black;"
        );
        loginButton.setOnAction(e -> onLoginClick.run()); // Executa a ação ao clicar no botão

        VBox loginBox = new VBox(20, presentation, loginButton);
        loginBox.setAlignment(Pos.CENTER);

        // ========== SPACER ==========

        // Cria uma região vazia para preencher o espaço entre os elementos visuais
        Region spacerTop = new Region();
        VBox.setVgrow(spacerTop, Priority.ALWAYS);  // Força o spacer a sempre ocupar todo o espaço disponível dentro do contêiner

        Region spacerBottom = new Region();
        VBox.setVgrow(spacerBottom, Priority.ALWAYS);  // Força o spacer a sempre ocupar todo o espaço disponível dentro do contêiner

        // ========== RODAPÉ ==========

        // Linha para separar o rodapé
        Separator separator = new Separator();
        separator.setStyle("-fx-background-color: white;");
        
        // Cria um contêiner para o rodapé com um espaçamento de n píxeis entre os filhos
        HBox footerBox = new HBox(5);
        footerBox.setAlignment(Pos.CENTER);
        footerBox.setPadding(new Insets(10)); // Margens internas
        
        Label version = new Label("Versão 1.0");
        version.setStyle(
            "-fx-font-size: " + FOOTER_FONT_SIZE + "px;" + 
            "-fx-text-fill: white;"
        );

        Label footerText = new Label(" | INF008 | IFBA");
        footerText.setStyle(
            "-fx-font-size: " + FOOTER_FONT_SIZE + "px;" +
            "-fx-text-fill: white;"
        );

        // Adiciona os elementos ao contêiner do rodapé
        footerBox.getChildren().addAll(
            version,
            footerText
        );    

        // ========== MONTAGEM DA TELA ==========

        // Adiciona os elementos ao contêiner principal
        welcomeBox.getChildren().addAll(
            titleBox,
            subtitleBox,
            spacerTop,
            loginBox,
            spacerBottom,
            separator,
            footerBox
        );

        return welcomeBox;
    }

    private ImageView createIcon(String iconName, double size) {
        ImageView icon = new ImageView(
            new Image(
                Objects.requireNonNull(
                    getClass().getResourceAsStream(ICONS_PATH + iconName)
                )
            )
        );

        icon.setFitWidth(size);
        icon.setFitHeight(size);
        icon.setPreserveRatio(true);
        return icon;
    }
}
