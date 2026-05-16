package ifba.inf011.q1;

import ifba.inf011.q1.classes.Modulos.ModuloABC;
import ifba.inf011.q1.classes.error.FWDocumentException;
import ifba.inf011.q1.enums.Privacidade;
import ifba.inf011.q1.interfaces.IDocumento;
import ifba.inf011.q1.interfaces.IModulo;
import ifba.inf011.q1.interfaces.IOperador;

public class App {
    public static void main(String[] args) {
        
        IModulo moduloABC = new ModuloABC();
        IOperador operador = moduloABC.criarOperador();
        IDocumento documento = moduloABC.criarDocumento();

        try {
            documento.inicializar(operador, Privacidade.Publico);
        } catch (FWDocumentException e) {
            System.err.println("O operador e a privacidade não podem ser nulos: " + e);
        }
    }
}
