package br.edu.ifba.inf008.interfaces;

// Representa o núcleo do sistema
public abstract class ICore
{
    // Mantém a instância global do núcleo da aplicação
    protected static ICore instance = null;

    // Retorna a instância global atual do núcleo
    // Permite que os plugins consigam acessar o UIController do Core do App em execução
    public static ICore getInstance() {
        return instance;
    }

    // Retorna o controlador da interface gráfica responsável pela interação com o usuário
    public abstract IUIController getUIController();

    // Retorna o controlador de autenticação, responsável por operações de login, logout e cadastro
    public abstract IAuthenticationController getAuthenticationController();

    // Retorna o controlador de entrada e saída (IO), utilizado para operações internas de dados
    public abstract IIOController getIOController();

    // Retorna o controlador de plugins, responsável por carregar e gerenciar os plugins do sistema
    public abstract IPluginController getPluginController();
}
