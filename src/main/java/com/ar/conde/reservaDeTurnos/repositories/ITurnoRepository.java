package com.ar.conde.reservaDeTurnos.repositories;

import com.ar.conde.reservaDeTurnos.entity.Turno;
import org.springframework.data.repository.CrudRepository;

public interface ITurnoRepository extends CrudRepository<Turno,Long> {
}
