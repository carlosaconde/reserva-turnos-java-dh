package com.ar.conde.reservaDeTurnos.service;

import com.ar.conde.reservaDeTurnos.entity.Domicilio;
import com.ar.conde.reservaDeTurnos.repositories.IDomicilioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service("domicilio")
public class DomicilioService implements IService<Domicilio>{

    private IDomicilioRepository repository;

    public DomicilioService(IDomicilioRepository repository){
        this.repository = repository;
    }
    @Override
    @Transactional(readOnly=true)
    public List<Domicilio> getAll() {
        return (List<Domicilio>) repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Domicilio> getById(Long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional
    public Domicilio create(Domicilio domicilio) {
        return repository.save(domicilio);
    }

    @Override
    @Transactional
    public Domicilio update(Long id, Domicilio domicilio) {
        return null;
    }

    @Override
    @Transactional
    public void delete(Long id) {
    repository.deleteById(id);
    }
}
