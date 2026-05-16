package ifba.inf011.q1_2.interfaces;

public interface IOperadorPrototipavel extends IOperador, IPrototipo {
    @Override
    // Retorno covariante
    IOperadorPrototipavel clone();
}