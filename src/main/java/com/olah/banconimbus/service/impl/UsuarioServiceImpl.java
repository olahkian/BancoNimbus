package com.olah.banconimbus.service.impl;

import com.olah.banconimbus.domain.model.Usuario;
import com.olah.banconimbus.domain.repository.UsuarioRepository;
import com.olah.banconimbus.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    @Override
    public Usuario retornaPorId(Long id) {
        return repository.findById(id).orElseThrow( () -> new NoSuchElementException("Usuário não encontrado com o id " + id.toString()) );
    }

    @Override
    public Usuario cria(Usuario u) {

        if ( u.getId() != null && repository.existsById (u.getId()) ) {
            throw new IllegalArgumentException("Já existe um usuário com este id: " + u.getId().toString());
        }

        if ( u.getConta() != null && u.getConta().getNumero() != null &&
             repository.existsByContaNumero(u.getConta().getNumero())) {
            throw new IllegalArgumentException("Já existe um usuário com esta conta " + u.getConta().getNumero());
        }

        return repository.save(u);
    }
}
