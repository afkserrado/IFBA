package br.edu.ifba.inf008.plugins;

import java.sql.Connection;

import br.edu.ifba.inf008.interfaces.ICore;
import br.edu.ifba.inf008.interfaces.IDatabaseController;
import br.edu.ifba.inf008.interfaces. IPlugin;
import br.edu.ifba.inf008.interfaces.IUIController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.MenuItem;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


public class MainScreenPlugin implements IPlugin
{
    public boolean init() {
        
        // Tratando SQLException
        try {
            IDatabaseController db = ICore.getInstance().getDatabaseController();
            Connection connection = db.getConnectionReadOnly();
            System.out.println("Conexão estabelecida: " + connection);
            db.closeConnection(connection);
            System.out.println("Conexão fechada com sucesso!");
        } catch (Exception ex) {
            System.err.println("Erro ao conectar com banco: " + ex.getMessage());
            ex.printStackTrace();
        }

         /*
        Obtém a instância do programa e, em seguida, a interface gráfica

        getInstance() retorna a referência estática "instance", que aponta para o objeto Core que representa o programa

        getUIController() chama UIController.getInstance(), que retorna a referência estática "uiController",
        contendo o objeto que controla a interface gráfica
        */
        IUIController uiController = ICore.getInstance().getUIController();

        // Cria um novo item de menu
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
        */
        menuItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                System.out.println("I've been clicked!");
            }
        });

        uiController.createTab("new tab", new Rectangle(200,200, Color.LIGHTSTEELBLUE));

        

        return true;
    }
}
