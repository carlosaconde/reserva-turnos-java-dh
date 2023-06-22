package com.ar.conde.reservaDeTurnos.service;

import com.ar.conde.reservaDeTurnos.entity.Odontologo;
import com.ar.conde.reservaDeTurnos.repositories.OdontologoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service("odontologo")
public class OdontologoService implements IService<Odontologo>{



    private OdontologoRepository repository;

    public OdontologoService(OdontologoRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional(readOnly = true)
    public List getAll() {
        return (List<Odontologo>) repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Odontologo> getById(Long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional
    public Odontologo create(Odontologo odontologo) {
       try{
           Optional<Odontologo> isexists = repository.buscarPorMatricula(odontologo.getMatricula());
           if(isexists.isPresent()){
                throw new IllegalArgumentException("la Matricula ya esta en uso");
           }
           return repository.save(odontologo);
       }
       catch(Exception exception){
           throw exception;
       }

    }

    @Override
    @Transactional
    public Odontologo update(Long id, Odontologo odontologo) {
        return null;
    }


    @Override
    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
