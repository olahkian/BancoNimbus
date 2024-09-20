package com.olah.banconimbus.controller;

import com.olah.banconimbus.domain.model.Usuario;
import com.olah.banconimbus.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> RetornaPorId(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.retornaPorId(id));
    }

    @PostMapping
    public ResponseEntity<Usuario> cria(@RequestBody Usuario usuarioRecebido) {
        Usuario usuarioCriado = service.cria(usuarioRecebido);
        URI localizacao = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(usuarioCriado.getId())
                .toUri();
        return ResponseEntity.created(localizacao).body(usuarioCriado);
    }

}
