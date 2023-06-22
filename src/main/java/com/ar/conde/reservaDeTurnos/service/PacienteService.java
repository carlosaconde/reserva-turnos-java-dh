package com.ar.conde.reservaDeTurnos.service;

import com.ar.conde.reservaDeTurnos.entity.Paciente;
import com.ar.conde.reservaDeTurnos.repositories.PacienteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service("paciente")
public class PacienteService implements IService<Paciente>{


    private PacienteRepository repository;

    public PacienteService(PacienteRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional(readOnly = true)
    public List getAll() {
        return (List<Paciente>) repository.findAll() ;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Paciente> getById(Long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional
    public Paciente create(Paciente paciente) {
        paciente.setFechaDeAlta(LocalDate.now());
        return repository.save(paciente);
    }

    @Override
    @Transactional
    public Paciente update(Long id, Paciente paciente) {
        return null;
    }

    @Override
    @Transactional
    public void delete(Long id) {
    repository.deleteById(id);
    }
}
