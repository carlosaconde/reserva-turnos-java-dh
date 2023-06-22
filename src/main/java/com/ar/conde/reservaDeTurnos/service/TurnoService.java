package com.ar.conde.reservaDeTurnos.service;

import com.ar.conde.reservaDeTurnos.entity.Turno;
import com.ar.conde.reservaDeTurnos.log4j.Log4j;
import com.ar.conde.reservaDeTurnos.repositories.TurnoRepository;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Range;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional(readOnly = true)
    public List<Turno> getAll() {
        return (List<Turno>) repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Turno> getById(Long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional
    public Turno create(Turno turno) {
        turno.setFechaTurno(((LocalDate.parse(turno.getFechaTurno()))));
        turno.setHora(String.valueOf(LocalTime.parse(turno.getHora(),formatter)));
        Log4j.info("Turno creado");
        return (Turno) repository.save(turno);
    }

    @Override
    public Turno update(Long id, Turno turno) {
        return null;
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);

    }
}
