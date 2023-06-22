package com.ar.conde.reservaDeTurnos.controller;


import com.ar.conde.reservaDeTurnos.entity.Paciente;
import com.ar.conde.reservaDeTurnos.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
public class PacienteController {
    @Autowired
    @Qualifier("paciente")
    private IService service;

    @GetMapping("/api/pacientes")
    public List<Paciente> getAll(){
        return service.getAll();
    }

    @GetMapping("/api/pacientes/{id}")
    public ResponseEntity<?> paciente(@PathVariable Long id){
        Optional<Paciente> pacienteOptional = service.getById(id);
        if(pacienteOptional.isPresent()){
            return ResponseEntity.ok(pacienteOptional.get());
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/api/pacientes")
    public ResponseEntity<?> save(@RequestBody Paciente paciente){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(paciente));
    }

    @PutMapping("/api/pacientes/{id}")
    public ResponseEntity<?> update(@RequestBody Paciente paciente, @PathVariable Long id){

        return ResponseEntity.status(HttpStatus.CREATED).body(service.update(id, paciente));

    }

    @DeleteMapping("/api/pacientes/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        Optional<Paciente> paciente = service.getById(id);
        if(paciente.isPresent()){
            service.delete(id);
            return  ResponseEntity.noContent().build();
        }else {
            return ResponseEntity.notFound().build();
        }
    }


}
