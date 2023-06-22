package com.ar.conde.reservaDeTurnos.controller;
import com.ar.conde.reservaDeTurnos.entity.Odontologo;
import com.ar.conde.reservaDeTurnos.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class OdontologoController {
    @Autowired
    @Qualifier("odontologo")
    private IService service;

    @GetMapping("/api/odontologos")
    public List<Odontologo> getAll(){
        return service.getAll();
    }

    @GetMapping("/api/odontologos/{id}")
    public ResponseEntity<?> odontologo(@PathVariable Long id){
        Optional<Odontologo> odontologoOptional = service.getById(id);
        if(odontologoOptional.isPresent()){
            return ResponseEntity.ok(odontologoOptional.get());
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/api/odontologos")
    public ResponseEntity<?> save(@RequestBody Odontologo odontologo){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(odontologo));
    }

    @PutMapping("/api/odontologos/{id}")
    public ResponseEntity<?> update(@RequestBody Odontologo odontologo, @PathVariable Long id){

        return ResponseEntity.status(HttpStatus.CREATED).body(service.update(id, odontologo));

    }

    @DeleteMapping("/api/odontologos/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
    Optional<Odontologo> odontologo = service.getById(id);
    if(odontologo.isPresent()){
        service.delete(id);
        return  ResponseEntity.noContent().build();
    }else {
        return ResponseEntity.notFound().build();
    }
    }



}
