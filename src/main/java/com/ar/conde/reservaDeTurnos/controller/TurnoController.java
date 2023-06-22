package com.ar.conde.reservaDeTurnos.controller;


import com.ar.conde.reservaDeTurnos.entity.Odontologo;
import com.ar.conde.reservaDeTurnos.entity.Paciente;
import com.ar.conde.reservaDeTurnos.entity.Turno;
import com.ar.conde.reservaDeTurnos.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.Optional;


@RestController
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


    @GetMapping("/api/turnos")
    public List<Turno> getAll(){
        return turnoservice.getAll();
    }

    @GetMapping("/api/turnos/{id}")
    public ResponseEntity<?> turno(@PathVariable Long id) {
        Optional<Turno> turnoOptional = turnoservice.getById(id);
        if (turnoOptional.isPresent()) {
            return ResponseEntity.ok(turnoOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/api/turnos")
    public ResponseEntity<?> turno(@RequestBody Turno turno )  {
        Optional<Paciente> pacienteBuscado = pacienteService.getById(turno.getPaciente().getId());
        Optional<Odontologo> odontologoBuscado = odontologoService.getById(turno.getOdontologo().getId());

        if(pacienteBuscado.isPresent()&&odontologoBuscado.isPresent()){
            return ResponseEntity.ok(turnoservice.create(turno));
        }else{
            return null;
        }

    }

}
