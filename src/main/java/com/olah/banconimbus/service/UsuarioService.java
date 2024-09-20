package com.olah.banconimbus.service;

import com.olah.banconimbus.domain.model.Usuario;

public interface UsuarioService {

    Usuario retornaPorId (Long id);

    Usuario cria (Usuario u);
}
