package com.ar.ndifabio.reservaDeTurnos.repositories;

import com.ar.ndifabio.reservaDeTurnos.entity.Usuario;
import org.springframework.data.repository.CrudRepository;

public interface IUsuarioRepository extends CrudRepository<Usuario,Long> {
}
