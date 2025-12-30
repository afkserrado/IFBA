package br.edu.ifba.inf008.shell;

import br.edu.ifba.inf008.interfaces.IDatabaseController;

// Classe responsável por controlar o acesso à base de dados
public class DatabaseController extends IDatabaseController{
    
    public DatabaseController() {
        IDatabaseController.init("mariadb", "3307", "car_rental_system", "root", "root");
    }
}
