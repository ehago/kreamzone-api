package com.ehago.kreamzone.mapper;

import java.util.List;

public interface BaseMapper<E, D> {

    E toEntity(D dto);

    D toDto(E entity);

    List<E> toEntities(List<D> dtos);

    List<D> toDtos(List<E> entities);

}
