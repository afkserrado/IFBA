package com.ifba.l1q5;

class AppConfig {
    private String appName, version;
    private int maxConnections = 100, timeoutSeconds = 30;
    private boolean isDebugMode = true;

    // Constructors
    public AppConfig(String appName, String version, int maxConnections, int timeoutSeconds, boolean isDebugMode) {
        this(appName, version, timeoutSeconds, isDebugMode);
        this.maxConnections = maxConnections;
    }

    public AppConfig(String appName, String version, int timeoutSeconds, boolean isDebugMode) {
        this(appName, version, 100);
        this.timeoutSeconds = timeoutSeconds;
        this.isDebugMode = isDebugMode;
    }

    public AppConfig(String appName, String version, int maxConnections) {
        this.appName = appName;
        this.version = version;
        this.maxConnections = maxConnections;
    }

    // Métodos
    public void updateSettings(int maxConnections, int timeoutSeconds) {
        // Verifica se o maxConnections é maior que zero e o atualiza. Se não, mantém o valor anterior
        this.maxConnections = maxConnections >= 0 ? maxConnections : this.maxConnections;

        // Verifica se o timeoutSeconds é maior que zero e o atualiza. Se não, mantém o valor anterior
        this.timeoutSeconds = timeoutSeconds >= 0 ? timeoutSeconds : this.timeoutSeconds;
    }

    public void updateSettings(boolean isDebugMode) {
        this.isDebugMode = isDebugMode;
    }

    public void validateConfig() {
        boolean statusAppName = this.appName != null;
        boolean statusVersion = this.version != null;
        boolean statusMaxConnections = this.maxConnections > 0;
        boolean statusTimeoutSeconds = this.timeoutSeconds > 0;

        // Imprime os resultados
        print(this.appName, statusAppName);
        print(this.version, statusVersion);
        print(this.maxConnections, statusMaxConnections);
        print(this.timeoutSeconds, statusTimeoutSeconds);
    }

    private void print(String parameter, boolean status) {
        String validStatus = status ? "valid" : "invalid";
        System.out.println(parameter + " is " + validStatus);
    }

    private void print(int parameter, boolean status) {
        String validStatus = status ? "valid" : "invalid";
        System.out.println(parameter + " is " + validStatus);
    }
}