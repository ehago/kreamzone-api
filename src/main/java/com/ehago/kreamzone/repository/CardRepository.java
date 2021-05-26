package com.ehago.kreamzone.repository;

import com.ehago.kreamzone.entity.Card;
import org.springframework.data.repository.CrudRepository;

public interface CardRepository extends CrudRepository<Card, Long> {
}
