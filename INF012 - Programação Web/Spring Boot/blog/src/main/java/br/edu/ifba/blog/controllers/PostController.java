package br.edu.ifba.blog.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifba.blog.model.Post;

@RestController
public class PostController {
    
    @RequestMapping("/posts")
    public Post listar() {
        Post post = new Post();
        post.setTitulo("Titulo de exemplo");
        return post;
    }
}
