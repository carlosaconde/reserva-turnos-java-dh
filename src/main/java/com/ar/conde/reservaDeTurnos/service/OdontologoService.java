package com.ar.conde.reservaDeTurnos.service;

import com.ar.conde.reservaDeTurnos.entity.Odontologo;
import com.ar.conde.reservaDeTurnos.log4j.Log4j;
import com.ar.conde.reservaDeTurnos.repositories.OdontologoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service("odontologo")
public class OdontologoService implements IService<Odontologo> {

    private OdontologoRepository repository;

    public OdontologoService(OdontologoRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public List getAll() {
        try {
            return (List<Odontologo>) repository.findAll();
        } catch (Exception e) {
            Log4j.error(e.toString());
            throw e;
        }

    }

    @Override
    @Transactional
    public Optional<Odontologo> getById(Long id) {
        try {
            return repository.findById(id);
        } catch (Exception e) {
            Log4j.error(e.toString());
            throw e;
        }

    }

    @Override
    @Transactional
    public Odontologo create(Odontologo odontologo) {
        try {
            Optional<Odontologo> isExists = repository.buscarPorMatricula(odontologo.getMatricula());
            if (isExists.isPresent()) {
                throw new IllegalArgumentException("la Matricula ya esta registrada ");
            }
            return repository.save(odontologo);
        } catch (Exception e) {
            Log4j.error(e.toString());
            throw e;
        }

    }

    @Override
    @Transactional
    public Odontologo update(Long id, Odontologo odontologo) {
        try {
            Optional<Odontologo> isExists = getById(id);
            if (isExists.isEmpty()) {
                throw new IllegalArgumentException("el ID ingresado no existe ");
            }

            Optional<Odontologo> odontologoExist = repository.buscarPorMatricula(odontologo.getMatricula());
            if (odontologoExist.isPresent() && !Objects.equals(odontologoExist.get().getMatricula(), odontologo.getMatricula())) {
                throw new IllegalArgumentException("la matricula ya existe ");
            }

            Odontologo odontologoUpdate = isExists.get();
            odontologoUpdate.setApellido(odontologo.getApellido());
            odontologoUpdate.setNombre(odontologo.getNombre());
            odontologoUpdate.setMatricula(odontologo.getMatricula());

            return repository.save(odontologoUpdate);
        } catch (Exception e) {
            Log4j.error(e.toString());
            throw e;
        }
    }


    @Override
    @Transactional
    public void delete(Long id) {
        try {
            Optional<Odontologo> odontologo = getById(id);
            if (odontologo.isPresent()) {
                repository.deleteById(id);
            } else {
                throw new IllegalArgumentException("El id " + id + " no existe");
            }

        } catch (Exception e) {
            Log4j.error(e.toString());
            throw e;
        }

    }
}
