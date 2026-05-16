package ifba.inf011.q1_2;

import ifba.inf011.q1_2.classes.Documentos.DocumentoCalculoPericial;
import ifba.inf011.q1_2.classes.Modulos.ModuloABC;
import ifba.inf011.q1_2.classes.Modulos.ModuloConfiguravel;
import ifba.inf011.q1_2.classes.Operadores.OperadorCalculista;
import ifba.inf011.q1_2.classes.error.FWDocumentException;
import ifba.inf011.q1_2.enums.Privacidade;
import ifba.inf011.q1_2.interfaces.IDocumento;
import ifba.inf011.q1_2.interfaces.IModulo;
import ifba.inf011.q1_2.interfaces.IOperador;

public class App {
    public static void main(String[] args) {
        
        // q1_2
        IModulo moduloABC = new ModuloABC();
        IOperador operador = moduloABC.criarOperador();
        IDocumento documento = moduloABC.criarDocumento();

        // Q2
        IModulo moduloXYZ = new ModuloConfiguravel(new OperadorCalculista(), new DocumentoCalculoPericial());
        IOperador operador2 = moduloXYZ.criarOperador();
        IDocumento documento2 = moduloXYZ.criarDocumento();

        try {
            documento.inicializar(operador, Privacidade.Publico);
            documento2.inicializar(operador2, Privacidade.Publico);
        } catch (FWDocumentException e) {
            System.err.println("O operador e a privacidade não podem ser nulos: " + e);
        }
    }
}
