package com.ehago.kreamzone.repository;

import com.ehago.kreamzone.entity.Item;
import org.springframework.data.repository.CrudRepository;

public interface ItemRepository extends CrudRepository<Item, Long> {
}
