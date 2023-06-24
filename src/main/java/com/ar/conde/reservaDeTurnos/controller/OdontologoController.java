package com.ar.conde.reservaDeTurnos.controller;

import com.ar.conde.reservaDeTurnos.entity.Odontologo;
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

import static com.ar.conde.reservaDeTurnos.exceptions.CustomFieldException.customResponseError;
import static com.ar.conde.reservaDeTurnos.exceptions.CustomFieldException.validate;

@RestController
@RequestMapping("/api/odontologos")
public class OdontologoController {
    @Autowired
    @Qualifier("odontologo")
    private IService service;

    @GetMapping("/")
    public ResponseEntity<List<Odontologo>> getAll() {
        try {
            List<Odontologo> odontologos = service.getAll();
            return ResponseEntity.ok().body(odontologos);
        } catch (Exception e) {
            Log4j.error(e.toString());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> odontologo(@PathVariable Long id) {
        try {
            Optional<Odontologo> odontologoOptional = service.getById(id);
            if (odontologoOptional.isPresent()) {
                return ResponseEntity.ok(odontologoOptional.get());
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            Log4j.error(e.toString());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/")
    public ResponseEntity<?> save(@Valid @RequestBody Odontologo odontologo, BindingResult result) {
        try {
            if (result.hasErrors()) {
                return validate(result);
            }

            return ResponseEntity.status(HttpStatus.CREATED).body(service.create(odontologo));
        } catch (Exception e) {
            Log4j.error(e.toString());
            return customResponseError(e);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody Odontologo odontologo, BindingResult result, @PathVariable Long id) {
        try {
            if (result.hasErrors()) {
                return validate(result);
            }

            return ResponseEntity.status(HttpStatus.CREATED).body(service.update(id, odontologo));
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
