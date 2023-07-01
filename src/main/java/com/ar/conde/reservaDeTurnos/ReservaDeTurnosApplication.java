package com.ar.conde.reservaDeTurnos;

import com.ar.conde.reservaDeTurnos.entity.*;
import com.ar.conde.reservaDeTurnos.repositories.IOdontologoRepository;
import com.ar.conde.reservaDeTurnos.repositories.IPacienteRepository;
import com.ar.conde.reservaDeTurnos.repositories.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class ReservaDeTurnosApplication {

    @Autowired
    private IPacienteRepository iPacienteRepository;
    @Autowired
    private IOdontologoRepository iOdontologoRepository;
    @Autowired
    private IUsuarioRepository iUsuarioRepository;

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(ReservaDeTurnosApplication.class, args);

        ReservaDeTurnosApplication aplicacion = context.getBean(ReservaDeTurnosApplication.class);

        aplicacion.crearPacientes();
        aplicacion.crearOdontologos();
        aplicacion.crearUsuarios();
    }

    public void crearPacientes() {
        iPacienteRepository.deleteAll();

        List<Paciente> pacientes = new ArrayList<>();

        pacientes.add(new Paciente(null, new Domicilio(null, "Calle 1", "1", "Localidad 1", "Provincia 1"), "Nombre1", "Apellido1", 11111111, LocalDate.now()));
        pacientes.add(new Paciente(null, new Domicilio(null, "Calle 2", "2", "Localidad 2", "Provincia 2"), "Nombre2", "Apellido2", 22222222, LocalDate.now()));
        pacientes.add(new Paciente(null, new Domicilio(null, "Calle 3", "3", "Localidad 3", "Provincia 2"), "Nombre3", "Apellido3", 33333333, LocalDate.now()));
        pacientes.add(new Paciente(null, new Domicilio(null, "Calle 4", "4", "Localidad 4", "Provincia 2"), "Nombre4", "Apellido4", 44444444, LocalDate.now()));
        pacientes.add(new Paciente(null, new Domicilio(null, "Calle 5", "5", "Localidad 5", "Provincia 2"), "Nombre5", "Apellido5", 55555555, LocalDate.now()));

        iPacienteRepository.saveAll(pacientes);
    }

    public void crearOdontologos() {
        iOdontologoRepository.deleteAll();

        List<Odontologo> odontologos = new ArrayList<>();

        odontologos.add(new Odontologo("Odontologo 1", "Odontologo 1", 111));
        odontologos.add(new Odontologo("Odontologo 2", "Odontologo 2", 222));
        odontologos.add(new Odontologo("Odontologo 3", "Odontologo 3", 333));
        odontologos.add(new Odontologo("Odontologo 4", "Odontologo 4", 444));
        odontologos.add(new Odontologo("Odontologo 5", "Odontologo 5", 555));

        iOdontologoRepository.saveAll(odontologos);
    }

    public void crearUsuarios() {
        iUsuarioRepository.deleteAll();

        List<Usuario> usuarios = new ArrayList<>();

        usuarios.add(new Usuario("usuario1", "usuario1", false, 11111111, Rol.ROLE_USER));
        usuarios.add(new Usuario("admin1", "admin1", true, 111, Rol.ROLE_ADMIN));

        iUsuarioRepository.saveAll(usuarios);
    }
}
