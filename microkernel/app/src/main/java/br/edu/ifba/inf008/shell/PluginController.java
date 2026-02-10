package br.edu.ifba.inf008.shell;

import java.io.File;
import java.io.FilenameFilter;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.LinkedHashMap;
import java.util.Map;

import br.edu.ifba.inf008.App;
import br.edu.ifba.inf008.interfaces.IPlugin;
import br.edu.ifba.inf008.interfaces.IPluginController;

/**
 * Controlador responsável por carregar e gerenciar plug-ins em tempo de execução.
 *
 * <p>Este controller procura arquivos {@code .jar} no diretório {@code ./plugins}, cria um {@link URLClassLoader} para esses JARs e instancia classes de plug-in por reflexão, seguindo uma convenção de nomes (por exemplo: {@code br.edu.ifba.inf008.plugins.<NomeDoJarSemExtensao>}).</p>
 *
 * <p>Os plug-ins carregados são armazenados internamente e podem ser recuperados por uma chave normalizada (trim + uppercase).</p>
 */
public class PluginController implements IPluginController {

    private final Map<String, IPlugin> registeredPlugins = new LinkedHashMap<>();

    /**
     * Carrega e inicializa dinamicamente todos os plug-ins disponíveis no diretório {@code ./plugins}.
     *
     * <p>Fluxo geral:</p>
     * <ul>
     *   <li>Lista os arquivos {@code .jar} do diretório de plug-ins usando um {@link FilenameFilter}.</li>
     *   <li>Cria um {@link URLClassLoader} apontando para os JARs encontrados.</li>
     *   <li>Para cada JAR: determina o nome do plug-in, carrega a classe e instancia via reflexão.</li>
     *   <li>Registra o plug-in via {@link #registerPlugin(String, IPlugin)} e chama {@link IPlugin#init()}.</li>
     * </ul>
     *
     * @return {@code true} se todos os plug-ins foram carregados/inicializados; {@code false} em caso de erro
     */
    @Override
    public boolean init() {
        try {
            
            // Diretório onde estão localizados os arquivos .jar dos plugins
            File currentDir = new File("./plugins");

            // Define um filtro de nomes de arquivo para aceitar apenas arquivos .jar
            FilenameFilter jarFilter = new FilenameFilter() {

                // Método chamado para cada arquivo presente no diretório
                // Retorna true apenas se o nome do arquivo terminar com ".jar" (ignorando maiúsculas/minúsculas)
                @Override
                public boolean accept(File dir, String name) {
                    return name.toLowerCase().endsWith(".jar");
                }
            };

            // Obtém um array com os nomes dos arquivos do diretório,
            // considerando apenas aqueles aceitos pelo filtro acima
            // Exemplo de resultado: ["MyPlugin.JAR", "test.jar", "PLUGIN.jar"]
            String []plugins = currentDir.list(jarFilter);

            // Cria um array de URLs contendo o caminho completo para cada plugin
            URL[] jars = new URL[plugins.length];
            int i;
            for (i = 0; i < plugins.length; i++)
            {
                // Cria um objeto File apontando para o .jar do plugin
                // e o converte para URL, que será usada pelo class loader
                jars[i] = (new File("./plugins/" + plugins[i])).toURL();
            }

            // Cria um carregador de classes capaz de carregar classes a partir dos arquivos .jar dos plugins
            // O class loader pai é o class loader da aplicação, permitindo que os plugins acessem as classes do programa principal
            URLClassLoader ulc = new URLClassLoader(jars, App.class.getClassLoader());
            for (i = 0; i < plugins.length; i++)
            {
                // Extrai o nome do plugin removendo a extensão ".jar"
                // Exemplo: "MyPlugin.JAR" -> "MyPlugin"
                String pluginName = plugins[i].split("\\.")[0];

                // Carrega dinamicamente a classe do plugin usando seu nome totalmente qualificado
                // O carregamento é feito pelo class loader customizado (ulc),
                // que busca a classe dentro do arquivo .jar correspondente
                // Exemplo de nome resolvido: "br.edu.ifba.inf008.plugins.MyPlugin"
                IPlugin plugin = (IPlugin) Class.forName(
                        "br.edu.ifba.inf008.plugins." + pluginName,
                        true,
                        ulc
                ).newInstance();

                registerPlugin(pluginName, plugin);

                // Inicializa o plugin após ele ter sido carregado
                plugin.init();
            }

            return true;
        }

        catch (Exception e) {
            // Em caso de erro durante o carregamento ou inicialização dos plugins,
            // exibe o tipo da exceção e sua mensagem
            System.out.println("Error: " + e.getClass().getName() + " - " + e.getMessage());

            return false;
        }
    }

    /**
     * Registra um plug-in em memória, associado a uma chave de tipo.
     *
     * <p>A chave recebida é normalizada (trim + uppercase) antes de ser usada como chave do mapa.</p>
     *
     * @param typeKey chave de identificação do plug-in (será normalizada)
     * @param plugin instância do plug-in a registrar
     */
    @Override
    public void registerPlugin(String typeKey, IPlugin plugin) {
        typeKey = typeKey.trim().toUpperCase(); // Normalização
        registeredPlugins.put(typeKey, plugin);
        //System.out.println(typeKey + " - " + plugin);
    }

    /**
     * Recupera um plug-in previamente registrado.
     *
     * <p>Se {@code typeKey} for {@code null}, retorna {@code null}. Caso contrário, a chave é normalizada (trim + uppercase) antes da consulta no mapa.</p>
     *
     * @param typeKey chave de identificação do plug-in
     * @return plug-in registrado para a chave informada, ou {@code null} se não existir
     */
    @Override
    public IPlugin getPlugin(String typeKey) {
        if(typeKey == null) return null;
        typeKey = typeKey.trim().toUpperCase(); // Normalização
        return registeredPlugins.get(typeKey);
    }
}
