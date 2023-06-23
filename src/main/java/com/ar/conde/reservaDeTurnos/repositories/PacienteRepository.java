package com.ar.conde.reservaDeTurnos.repositories;

import com.ar.conde.reservaDeTurnos.entity.Paciente;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PacienteRepository extends CrudRepository<Paciente,Long> {

    @Query("SELECT p FROM Paciente p WHERE p.dni = :dni")
    Optional<Paciente> buscarPorDni(@Param("dni") int dni);
}
