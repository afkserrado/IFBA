package br.edu.ifba.inf008.interfaces;

public interface IPluginController {
    abstract boolean init();
    
    // Registra os plugins ativos
    abstract void registerPlugin(String typeKey, IPlugin plugin);

    // Obtém um plugin ativo
    abstract IPlugin getPlugin(String typeKey);
}
