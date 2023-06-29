package com.ar.conde.reservaDeTurnos.controller;

import com.ar.conde.reservaDeTurnos.entity.Paciente;
import com.ar.conde.reservaDeTurnos.exceptions.CustomFieldException;
import com.ar.conde.reservaDeTurnos.log4j.Log4j;
import com.ar.conde.reservaDeTurnos.service.IService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/pacientes")
public class PacienteController extends CustomFieldException {
    @Autowired
    @Qualifier("paciente")
    private IService service;

    @GetMapping("/")
    public ResponseEntity<List<Paciente>> getAll() {
        try {
            List<Paciente> pacientes = service.getAll();
            return ResponseEntity.ok().body(pacientes);
        } catch (Exception e) {
            Log4j.error(e.toString());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> paciente(@PathVariable Long id) {
        try {
            Optional<Paciente> paciente = service.getById(id);
            if(paciente.isPresent()) {
                return ResponseEntity.ok(paciente);
            }
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            Log4j.error(e.toString());
            return customResponseError(e);
        }
    }

    @PostMapping("/")
    public ResponseEntity<?> save(@Valid @RequestBody Paciente paciente, BindingResult result) {
        try {
            if(result.hasErrors()) {
                return validate(result);
            }
            return ResponseEntity.status(HttpStatus.CREATED).body(service.create(paciente));
        } catch (Exception e) {
            Log4j.error(e.toString());
            return customResponseError(e);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody Paciente paciente, BindingResult result, @PathVariable Long id) {
        try {
            if(result.hasErrors()) {
                return validate(result);
            }

            return ResponseEntity.status(HttpStatus.CREATED).body(service.update(id,paciente));
        } catch (Exception e) {
            Log4j.error(e.toString());
            return customResponseError(e);
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            service.delete(id);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception e) {
            Log4j.error(e.toString());
            return customResponseError(e);
        }
    }


}
