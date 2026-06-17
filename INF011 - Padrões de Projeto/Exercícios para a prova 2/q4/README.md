## Exercício 4 — Integração com codec externo e funcionalidades opcionais de codificação

**Padrões-alvo: Adapter + Decorator**
**Origem: [Perplexity]**

O framework possui a interface `Encoder`, utilizada em toda a arquitetura do sistema:

```java
public interface Encoder {
    byte[] encode(Timeline timeline);
}
```

Uma empresa parceira forneceu uma biblioteca de codec de alta performance chamada `FFmpegCodec`. A classe não pode ser modificada e possui a seguinte API:

```java
public class FFmpegCodec {

    public void initialize(String format) {
        ...
    }

    public byte[] compress(String[] tracks, Map<String, String> options) {
        ...
    }

    public void release() {
        ...
    }
}
```

Deseja-se permitir que `FFmpegCodec` seja utilizado como um `Encoder` no framework, sem modificar nem `FFmpegCodec` nem a interface `Encoder`.

### Questão I

Apresente uma solução baseada em padrão de projeto que permita a utilização de `FFmpegCodec` como um `Encoder` no framework.

Identifique os participantes do padrão. Forneça o código Java esqueleto, demonstrando o uso de `FFmpegCodec` como `Encoder` em uma classe cliente.

### Questão II

O `Encoder` do framework deve ainda suportar a adição de funcionalidades opcionais e combináveis no processo de codificação, como:

* injeção de metadados XMP;
* adição de timecode SMPTE;
* criptografia de conteúdo.

Essas funcionalidades devem poder ser combinadas livremente. A adição de novas funcionalidades não deve exigir modificação das classes existentes.

Apresente o padrão adequado. Implemente `MetadadosXMPEncoder` e `TimecodeEncoder`. Demonstre o uso com um `Encoder` que aplique ambas as funcionalidades.