# LokiCar (INF008) — Como executar

Sistema em Java/JavaFX para locadora de veículos (arquitetura microkernel + plug-ins) com banco MariaDB via Docker.

## Pré-requisitos

- Java 25 (JDK 25.0.1)
- Maven (para compilar/empacotar e executar)
- Docker + Docker Engine + Docker Compose (para subir o MariaDB)

---

## 1) Subir o banco de dados (Docker)

Entre na pasta onde está o `docker-compose.yml`:

```bash
cd path/para/o/projeto/docker
```

Suba o container:

```bash
docker compose up -d
```

O compose deste projeto cria um serviço MariaDB com:

- Container name: `car_rental_database`
- Porta 3307 no host apontando para a 3306 do container
- Volume persistente: `db_data`
- Montagem de `init.sql` em `/docker-entrypoint-initdb.d/init.sql`

---

### 1.1) Execução do init.sql

O docker-compose.yml monta o init.sql em `/docker-entrypoint-initdb.d/`.

O MariaDB executa automaticamente todos os arquivos `.sql` dessa pasta apenas na primeira inicialização do container, quando o volume de dados ainda não existe.

Se o volume db_data já existir, o script não será executado novamente.

Para forçar a recriação completa do banco:

```bash
docker compose down -v
docker compose up -d
```

Isso remove o volume e executa novamente o init.sql.

Alternativamente, é possível executar manualmente:

```bash
docker exec -i car_rental_database mariadb -uroot -proot < init.sql
```

---

### 1.2) Verificar se está ok

```bash
docker ps
docker exec -it car_rental_database mariadb -uroot -proot -e "SHOW DATABASES;"
docker exec -it car_rental_database mariadb -uroot -proot -D car_rental_system -e "SHOW TABLES;"
```

O nome do banco usado pelo sistema é `car_rental_system` e a porta no host é `3307`.

---

## 2) Compilar o microkernel (Maven)

Entre na pasta do microkernel (a que contém o `pom.xml` raiz do multi-módulo):

```bash
cd path/para/o/projeto/microkernel
```

Limpe e compile:

```bash
mvn clean install
```

---

## 3) Executar a aplicação

Ainda na pasta `microkernel`:

```bash
mvn exec:java -pl app
```

Ou em um comando só:

```bash
mvn clean install && mvn exec:java -pl app
```

O `app` inicializa o Core e o JavaFX; ao entrar na tela principal, o `PluginController` procura arquivos `.jar` na pasta `.plugins` e carrega dinamicamente os plugins.

---

## 4) Onde colocar os plugins (.jar)

O carregamento dinâmico procura JARs em:

```
microkernel/.plugins
```

O `PluginController` lista todos os `*.jar` dentro de `.plugins`, cria um `URLClassLoader` e instancia a classe:

```
br.edu.ifba.inf008.plugins.<NomeDoJarSemExtensao>
```

### Exemplo

- Arquivo: `.plugins/MainScreenPlugin.jar`
- Classe esperada dentro do JAR:
  `br.edu.ifba.inf008.plugins.MainScreenPlugin`

---

## 5) Criar um novo plugin

### Opção A (plugin como submódulo Maven)

Crie a pasta do plugin em:

```
microkernel/plugins/<seu-plugin>
```

Registre o módulo no `pom.xml` raiz (na seção `<modules>`), por exemplo:

```xml
<modules>
  <module>interfaces</module>
  <module>app</module>
  <module>plugins/seu-plugin</module>
</modules>
```

Crie o `pom.xml` do plugin (baseie-se nos outros plugins do projeto).

Garanta o package/classe:

```
br.edu.ifba.inf008.plugins.<YourPluginNameInCamelCase>
```

Compile:

```bash
mvn clean install
```

Não é necessário copiar o `.jar` gerado do plugin para a pasta:

```
microkernel/.plugins
```

Se você criou o `pom.xml` do plugin baseado nos outros plugins do projeto, então isso será feito automaticamente.

---

### Opção B (apenas “drop-in” do .jar)

Se você já tiver um `.jar` pronto, basta colocar em:

```
microkernel/.plugins
```

Sem recompilar o app. O microkernel carrega em runtime.

---

## 6) Configuração do banco no código

O `DatabaseController` inicializa a URL de conexão usando:

- Subprotocol: `mariadb`
- Porta: `3307`
- Database: `car_rental_system`
- User: `root`
- Password: `root`

Ou seja, se você mudou o `docker-compose.yml`, ajuste essas credenciais/porta para combinar.

## 7) Convenção de nomes
O microkernel carrega plugins a partir do nome do `.jar`: para um arquivo `X.jar`, ele tenta instanciar a classe `br.edu.ifba.inf008.plugins.X` (ou seja, o nome do JAR *sem extensão* precisa ser igual ao nome da classe).

Além disso, os plugins de **tipos de veículos** são buscados pela chave `"<TIPO>PLUGIN"` (ex.: `COMPACTPLUGIN`, `SUVPLUGIN`), então padronize:
- Nome da classe do plugin de veículo: `TipoVeiculoPlugin` (ex.: `CompactPlugin`, `SuvPlugin`, `EconomyPlugin`). 
- Nome do JAR gerado (finalName no `pom.xml`): exatamente o mesmo da classe (ex.: `CompactPlugin.jar`).
