package com.ehago.kreamzone.repository;

import com.ehago.kreamzone.entity.Member;
import org.springframework.data.repository.CrudRepository;

public interface MemberRepository extends CrudRepository<Member, Long> {
}
