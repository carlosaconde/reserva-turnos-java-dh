package com.ar.conde.reservaDeTurnos.controller;


import com.ar.conde.reservaDeTurnos.entity.Odontologo;
import com.ar.conde.reservaDeTurnos.entity.Paciente;
import com.ar.conde.reservaDeTurnos.entity.Turno;
import com.ar.conde.reservaDeTurnos.log4j.Log4j;
import com.ar.conde.reservaDeTurnos.service.IService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.Optional;

import static com.ar.conde.reservaDeTurnos.exceptions.CustomFieldException.customResponseError;
import static com.ar.conde.reservaDeTurnos.exceptions.CustomFieldException.validate;


@RestController
@RequestMapping("/api/turnos")
public class TurnoController {

    @Autowired
    @Qualifier("turno")
    private IService turnoservice;
    @Autowired
    @Qualifier("paciente")
    private IService pacienteService;
    @Autowired
    @Qualifier("odontologo")
    private IService odontologoService;

    public TurnoController() {
    }


    @GetMapping("/")
    public ResponseEntity<List<Turno>> getAll(){

        try{
            List<Turno> turnos = turnoservice.getAll();
            return ResponseEntity.ok().body(turnos);
        } catch (Exception e){
            Log4j.error(e.toString());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<?> turno(@PathVariable Long id) {

        try {
            Optional<Turno> turnoOptional = turnoservice.getById(id);
            if (turnoOptional.isPresent()) {
                return ResponseEntity.ok(turnoOptional.get());
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            Log4j.error(e.toString());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @PostMapping("/")
    public ResponseEntity<?>  save(@Valid @RequestBody Turno turno, BindingResult result)  {
        try {
            if (result.hasErrors()) {
                return validate(result);

            }

            return ResponseEntity.status(HttpStatus.CREATED).body(turnoservice.create(turno));
        } catch (Exception e){
            Log4j.error(e.toString());
            return customResponseError(e);

        }

    }

}
