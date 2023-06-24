package com.ar.conde.reservaDeTurnos.repositories;

import com.ar.conde.reservaDeTurnos.entity.Usuario;
import org.springframework.data.repository.CrudRepository;

public interface IUsuarioRepository extends CrudRepository<Usuario,Long> {
}
