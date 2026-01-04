package br.edu.ifba.inf008.plugins;

import java.sql.Connection;

import br.edu.ifba.inf008.interfaces.ICore;
import br.edu.ifba.inf008.interfaces.IDatabaseController;
import br.edu.ifba.inf008.interfaces. IPlugin;
import br.edu.ifba.inf008.interfaces.IUIController;


public class MainScreenPlugin implements IPlugin {

    private IDatabaseController db;
    private Connection connection;

    public boolean init() {
        
        // Abre a conexão com o banco de dados
        try {
            // Obtém a instância do controlador da base de dados
            db = ICore.getInstance().getDatabaseController();
            connection = db.getConnectionReadOnly();
            System.out.println("Conexão estabelecida: " + connection);
        } 
        
        // Conexão falhou
        catch (Exception ex) {
            System.err.println("Erro ao conectar com banco: " + ex.getMessage());
            ex.printStackTrace();
        }

        // Obtém a instância do controlador da interface gráfica
        IUIController uiController = ICore.getInstance().getUIController();

        /*Cria um novo item de menu
        MenuItem menuItem = uiController.createMenuItem("Menu 1", "My Menu Item");
        
        /*
        Define a ação do novo item de menu

        Assim, menuItem é um objeto que representa um botão na interface gráfica

        setOnAction é um método de MenuItem que define o que acontece quando esse botão é clicado

        setOnAction responde apenas a um ActionEvent, que é um objeto que o JavaFX cria somente
        em situações específicas, como o clique com o botão esquerdo do mouse

        EventHandler é um objeto que trata o evento que ocorre (neste caso, o clique do botão)

        EventHandler possui um método chamado handle, que executa a ação do botão

        Portanto, quando o botão é clicado, o setOnAction registra um EventHandler que recebe
        o evento de clique e chama o método handle para executar a ação

        menuItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                System.out.println("I've been clicked!");
            }
        });

        uiController.createTab("new tab", new Rectangle(200,200, Color.LIGHTSTEELBLUE));
        
        */

        // Fecha a conexão
        db.closeConnection(connection);
        System.out.println("Conexão fechada com sucesso!");

        return true;
    }
}
