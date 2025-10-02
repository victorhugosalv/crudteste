package com.example.crud.controller;

import com.example.crud.model.Usuario;
import com.example.crud.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository repository;

    //Listar Todos
    @GetMapping
    public List<Usuario> listarTodos(){
        return repository.findAll();
    }

    //Buscar por ID
    @GetMapping("/{id}")
    public Optional<Usuario> buscarPorId(@PathVariable Long id){
        return repository.findById(id);
    }

    //Criar Novo
    @PostMapping
    public Usuario criar(@RequestBody Usuario usuario){
        return repository.save(usuario);
    }

    //Atualizar
    @PutMapping("/{id}")
    public Usuario atualizar(@PathVariable Long id, @RequestBody Usuario usuarioAtulizado){
        return buscarPorId(id).map( usuario -> {
            usuario.setNome(usuarioAtulizado.getNome());
            usuario.setEmail(usuarioAtulizado.getEmail());
            return repository.save(usuario);
        }).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }

    //Deletar
    @DeleteMapping("/{id}")
    public String deletar(@PathVariable Long id){
        repository.deleteById(id);
        return "Usuário Deletado com Sucesso";

    }
}
