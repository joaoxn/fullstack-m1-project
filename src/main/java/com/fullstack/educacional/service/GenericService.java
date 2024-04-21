package com.fullstack.educacional.service;

import java.util.List;

public interface GenericService<E> {
    E get(Long id);
    List<E> getAll();
    E create(E entity);
    E alter(Long id, E entity);
    E equalProperties(E entity, E data);
}
