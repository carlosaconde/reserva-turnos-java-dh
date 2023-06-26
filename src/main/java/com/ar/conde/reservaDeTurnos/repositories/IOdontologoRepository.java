package com.ar.conde.reservaDeTurnos.repositories;
import org.springframework.stereotype.Repository;
import com.ar.conde.reservaDeTurnos.entity.Odontologo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
@Repository
public interface IOdontologoRepository extends CrudRepository<Odontologo,Long> {
    @Query("SELECT o FROM Odontologo o WHERE o.matricula = :matricula")
    /*@Query("SELECT O FROM ODONTOLOGOS O WHERE O.MATRICULA = :matricula")*/
    Optional<Odontologo>  buscarPorMatricula(@Param("matricula") int matricula);

}
