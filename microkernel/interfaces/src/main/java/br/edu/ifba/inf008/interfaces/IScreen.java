package br.edu.ifba.inf008.interfaces;

import javafx.scene.Node;

/**
 * Representa as telas/scenes da aplicação.
 * Cada implementação deve criar e retornar seu conteúdo visual.
 */
public interface IScreen {
    
    /**
     * Cria e retorna o conteúdo visual da tela
     * @return Node contendo todos os elementos da tela
     */
    Node create(); // Cria um Node, que é o pai de todos elementos visuais do JavaFX
}
