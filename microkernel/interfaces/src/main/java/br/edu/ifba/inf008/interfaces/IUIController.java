package br.edu.ifba.inf008.interfaces;

import java.util.List; // Um item dentro de um menu gráfico

import javafx.scene.Node; // Classe base para qualquer elemento gráfico
import javafx.scene.control.MenuItem;

public interface IUIController {
    
    // Cria um novo item de menu
    // menuText = nome do menu onde o novo item será inserido
    // menuItemText = texto dentro do novo item de menu
    public abstract MenuItem createMenuItem(String menuText, String menuItemText);

    // Cria uma nova aba
    // tabText = texto da nova aba
    // contents = qualquer elemento gráfico que será visível dentro da nova aba
    public abstract boolean createTab(String tabText, Node contents);

    // Adiciona um ou mais elementos visuais à tela principal
    public abstract boolean addMainNodes(List<Node> nodes);
}
