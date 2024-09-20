package com.olah.banconimbus.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

@RestControllerAdvice
public class TratamentoDeExcecoesController {

    private final Logger logger = LoggerFactory.getLogger(TratamentoDeExcecoesController.class);

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> tratarErroArgumentoInvalido(IllegalArgumentException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> tratarErroElementoNaoExiste(NoSuchElementException ex) {
        //return ResponseEntity.badRequest().body(ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<String> tratarErroGenerico(Throwable ex) {
        logger.error("Erro inesperado", ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro inesperado. Solicite ajuda da equipe de TI.");
    }

}
