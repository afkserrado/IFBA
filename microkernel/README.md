# Navigate to the microkernel folder

Example (Linux):

cd "/mnt/Arquivos_D0P6LA/Codes/Projetos privados/INF008 (POO) - Trabalho/microkernel/"

# Clean the project (optionally)

To avoid problems caused by previous compilations, use:

mvn clean

before mvn install

# Build instructions:

mvn install

# Execution instructions:

mvn exec:java -pl app

# Build and execution instructions:

mvn install && mvn exec:java -pl app

Or

mvn clean install && mvn exec:java -pl app

# New plugin creation instructions:

1. Create your plugin folder in "plugins"
2. Add you new plugin submodule in main pom.xml:

    <modules>
        <module>interfaces</module>
        <module>app</module>
        <module>plugins/myplugin</module>
        ADD IT HERE
    </modules>
    
3. Create your new plugin's pom.xml (check myplugin/pom.xml)
4. Remember to use plugin's package conventions:

    br/edu/ifba/inf008/plugins/<YourPluginNameInCamelCase>.java
    
5. Run "mvn install" and "mvn exec:java -pl app"
