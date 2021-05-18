package com.ehago.kreamzone.mapper;

import com.ehago.kreamzone.dto.MemberDto;
import com.ehago.kreamzone.entity.Member;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MemberMapper extends BaseMapper<Member, MemberDto> {

    MemberMapper MAPPER = Mappers.getMapper(MemberMapper.class);

}
