package com.ar.conde.reservaDeTurnos.service;

import java.util.List;
import java.util.Optional;

public interface IService<T> {

    List<T> getAll();
    Optional<T> getById(Long id);

    T create(T t);

    T update(Long id, T t);
    void delete(Long id);
}
