package com.ar.conde.reservaDeTurnos.service;

import com.ar.conde.reservaDeTurnos.entity.Turno;
import com.ar.conde.reservaDeTurnos.log4j.Log4j;
import com.ar.conde.reservaDeTurnos.repositories.TurnoRepository;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
@Service("turno")
public class TurnoService implements IService<Turno>{
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
    private TurnoRepository repository;

    public TurnoService(TurnoRepository repository){
        this.repository=repository;
    }
    @Override
    @Transactional()
    public List<Turno> getAll() {
        try{
            return (List<Turno>) repository.findAll();
        } catch (Exception e){
            Log4j.error(e.toString());
            throw e;
        }

    }

    @Override
    @Transactional()
    public Optional<Turno> getById(Long id) {
        try{
            return repository.findById(id);
        } catch (Exception e){
            Log4j.error(e.toString());
            throw e;
        }


    }

    @Override
    @Transactional
    public Turno create(Turno turno) {

        try{
            Optional<Turno> isexist = getById(turno.getId());
            if(isexist.isPresent()){
                throw new IllegalArgumentException("El ID ya esta siendo utilizado");
            }
            turno.setFechaTurno(((LocalDate.parse(turno.getFechaTurno()))));
            turno.setHora(String.valueOf(LocalTime.parse(turno.getHora(),formatter)));
            Log4j.info("Turno creado");
            return repository.save(turno);
        } catch (Exception e){
            Log4j.error(e.toString());
            throw e;
        }


    }

    @Override
    public Turno update(Long id, Turno turno) {
        try {
            Optional<Turno> isexists = getById(id);
            if (isexists.isEmpty()) {
                throw new IllegalArgumentException("El ID ingresado no existe");
            } else {
                Turno turnoUpdate = isexists.get();
                turnoUpdate.setFechaTurno(((LocalDate.parse(turno.getFechaTurno()))));
                turnoUpdate.setHora(String.valueOf(LocalTime.parse(turno.getHora(), formatter)));
                turnoUpdate.setOdontologo(turno.getOdontologo());
                turnoUpdate.setPaciente(turno.getPaciente());

                return repository.save(turnoUpdate);
            }
        } catch (Exception e) {
            Log4j.error(e.toString());
            throw e;
        }
    }


    @Override
    @Transactional
    public void delete(Long id) {
        try{
            Optional<Turno>  turno = getById(id);
            if(turno.isPresent()){
                repository.deleteById(id);
            } else {
                throw new IllegalArgumentException("El id " + id +" no existe");
            }
        }catch (Exception e){
            Log4j.error(e.toString());
            throw e;
        }
    }

}
