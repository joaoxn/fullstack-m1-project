package com.fullstack.educacional.service;

import java.util.List;

public interface GenericService<E, DTO> {
    E get(Long id);
    List<E> getAll();
    E create(DTO entity);
    E alter(Long id, DTO entity);
    E equalProperties(E entity, DTO data);
}
