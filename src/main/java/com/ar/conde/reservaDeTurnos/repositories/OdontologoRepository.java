package com.ar.conde.reservaDeTurnos.repositories;

import com.ar.conde.reservaDeTurnos.entity.Odontologo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface OdontologoRepository extends CrudRepository<Odontologo,Long> {
    @Query("SELECT O FROM ODONTOLOGOS O WHERE O.MATRICULA = :MATRICULA")
    Optional<Odontologo> buscarPorMatricula(@Param("matricula") int matricula);

}
