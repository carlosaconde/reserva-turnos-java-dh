package com.ar.conde.reservaDeTurnos.controller;

import com.ar.conde.reservaDeTurnos.entity.Usuario;
import com.ar.conde.reservaDeTurnos.exceptions.CustomFieldException;
import com.ar.conde.reservaDeTurnos.log4j.Log4j;
import com.ar.conde.reservaDeTurnos.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController extends CustomFieldException {
    @Autowired
    @Qualifier("usuario")
    private UsuarioService usuarioService;
    @PostMapping("/")
    public ResponseEntity<?> create(@Valid @RequestBody Usuario body, BindingResult result) {
        try {
            if(result.hasErrors()) {
                return validate(result);
            }
            return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.create(body));
        } catch (Exception e) {
            Log4j.error(e.toString());
            return customResponseError(e);
        }
    }
}
