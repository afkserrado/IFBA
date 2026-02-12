package br.edu.ifba.inf008.interfaces;

import javafx.scene.Parent;

/**
 * Representa as telas/scenes da aplicação.
 * Cada implementação deve criar e retornar seu conteúdo visual.
 */
public interface IScreen {
    
    /**
     * Cria e retorna o conteúdo visual da tela
     * @return Parent contendo todos os elementos da tela
     */
    Parent createScreen();
}
