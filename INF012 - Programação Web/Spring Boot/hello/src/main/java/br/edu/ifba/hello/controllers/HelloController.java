package br.edu.ifba.hello.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller // Diz ao Spring que esta classe é um controlador web
public class HelloController { // Classe responsável por receber requisições HTTP

    @RequestMapping("/helloworld") // Mapeia a rota /helloworld para este método
    @ResponseBody // Faz o retorno do método ir direto no corpo da resposta HTTP
    public String hello() { // Método chamado quando a rota for acessada
        return "Hello INF012!"; // Texto devolvido ao cliente
    }
}