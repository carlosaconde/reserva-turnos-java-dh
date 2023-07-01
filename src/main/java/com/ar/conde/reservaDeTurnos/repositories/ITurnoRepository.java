package com.ar.conde.reservaDeTurnos.repositories;

import com.ar.conde.reservaDeTurnos.entity.Turno;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ITurnoRepository extends CrudRepository<Turno,Long> {
    @Query("SELECT t FROM Turno t JOIN FETCH t.paciente p JOIN FETCH t.odontologo o WHERE p.id = :pacienteId")
    List<Turno> findAllByPacienteId(@Param("pacienteId") Long pacienteId);
}
