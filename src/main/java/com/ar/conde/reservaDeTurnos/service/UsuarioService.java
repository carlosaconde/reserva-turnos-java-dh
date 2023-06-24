package com.ar.conde.reservaDeTurnos.service;

import com.ar.conde.reservaDeTurnos.entity.Odontologo;
import com.ar.conde.reservaDeTurnos.entity.Paciente;
import com.ar.conde.reservaDeTurnos.entity.Rol;
import com.ar.conde.reservaDeTurnos.entity.Usuario;
import com.ar.conde.reservaDeTurnos.log4j.Log4j;
import com.ar.conde.reservaDeTurnos.repositories.IUsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("usuario")
public class UsuarioService {
    private IUsuarioRepository repository;
    private OdontologoService odontologoService;
    private PacienteService pacienteService;

    public UsuarioService(IUsuarioRepository repository, OdontologoService odontologoService, PacienteService pacienteService) {
        this.repository = repository;
        this.odontologoService = odontologoService;
        this.pacienteService = pacienteService;
    }

    @Transactional
    public Usuario create(Usuario body) {
        try{
            if(body.getIsAdmin()){
                Optional<Odontologo> odontologo = odontologoService.buscarPorMatricula(body.getUserName());
                if(odontologo.isPresent()) {
                    body.setRol(Rol.ROLE_ADMIN);
                    return repository.save(body);
                }
                throw new IllegalArgumentException("La matricula ingresada no existe");

            } else {
                Optional<Paciente> paciente = pacienteService.buscarPorDni(body.getUserName());
                if(paciente.isPresent()) {
                    body.setRol(Rol.ROLE_USER);
                    return repository.save(body);
                }
                throw new IllegalArgumentException("El dni ingresado no existe");
            }
        } catch (Exception exception) {
            Log4j.error(exception.toString());
            throw exception;
        }
    }
}
