package com.ehago.kreamzone.repository;

import com.ehago.kreamzone.entity.Bookmark;
import org.springframework.data.repository.CrudRepository;

public interface BookmarkRepository extends CrudRepository<Bookmark, Long> {
}
