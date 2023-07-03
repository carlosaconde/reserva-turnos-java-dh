package com.ar.conde.reservaDeTurnos.repositories;

import com.ar.conde.reservaDeTurnos.entity.Odontologo;
import com.ar.conde.reservaDeTurnos.entity.Turno;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ITurnoRepository extends CrudRepository<Turno,Long> {
    @Query("SELECT t FROM Turno t JOIN FETCH t.paciente p JOIN FETCH t.odontologo o WHERE p.id = :pacienteId")
    List<Turno> findAllByPacienteId(@Param("pacienteId") Long pacienteId);

    @Query("SELECT COUNT(t) = 0 FROM Turno t WHERE t.odontologo.id = :odontologoId AND t.fechaTurno = :fechaTurno AND t.hora = :hora")
    boolean isTurnoAvailable(@Param("odontologoId") Long odontologoId, @Param("fechaTurno") String fechaTurno, @Param("hora") String hora);
}
