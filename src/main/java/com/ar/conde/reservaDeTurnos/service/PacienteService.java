package com.ar.conde.reservaDeTurnos.service;

import com.ar.conde.reservaDeTurnos.entity.Paciente;
import com.ar.conde.reservaDeTurnos.log4j.Log4j;
import com.ar.conde.reservaDeTurnos.repositories.PacienteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
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

        try{
            return (List<Paciente>) repository.findAll() ;
        }  catch (Exception e){
            Log4j.error(e.toString());
            throw e;
        }

    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Paciente> getById(Long id) {
        try{
            return repository.findById(id);
        } catch (Exception e){
            Log4j.error(e.toString());
            throw e;
        }

    }

    @Override
    @Transactional
    public Paciente create(Paciente paciente) {

        try{
            Optional<Paciente> isexists = repository.buscarPorDni(paciente.getDni());
            if(isexists.isPresent()){
                throw new IllegalArgumentException("El DNI ya esta registrado");
            }
            paciente.setFechaDeAlta(LocalDate.now());
            return repository.save(paciente);
        }
        catch (Exception e){
            throw e;
        }

    }

    @Override
    @Transactional
    public Paciente update(Long id, Paciente paciente) {
        try{
            Optional<Paciente> isExists = getById(id);
            if(isExists.isEmpty()){
                throw new IllegalArgumentException("el ID ingresado no existe ");
            }

            Optional<Paciente> pacienteExist = repository.buscarPorDni(paciente.getDni());
            if(pacienteExist.isPresent() && !Objects.equals(pacienteExist.get().getDni(), paciente.getDni())){
                throw new IllegalArgumentException("El Dni ya existe ");
            }

            Paciente pacienteUpdate = isExists.get();
            pacienteUpdate.setApellido(paciente.getApellido());
            pacienteUpdate.setNombre(paciente.getNombre());
            pacienteUpdate.setDni(paciente.getDni());
            pacienteUpdate.setDomicilio(paciente.getDomicilio());


            return repository.save(pacienteUpdate);
        }
        catch (Exception e){
            Log4j.error(e.toString());
            throw e;

        }
    }

    @Override
    @Transactional
    public void delete(Long id) {
        try{
            Optional<Paciente> paciente =getById(id);
            if(paciente.isPresent()){
                repository.deleteById(id);
            }else {
                throw new IllegalArgumentException("El id " + id +" no existe");
            }
        }catch (Exception e){
            Log4j.error(e.toString());
            throw e;
        }

    }
}
