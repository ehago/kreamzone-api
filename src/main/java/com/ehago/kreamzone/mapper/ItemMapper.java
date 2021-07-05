package com.ehago.kreamzone.mapper;

import com.ehago.kreamzone.dto.response.item.ItemResponseDto;
import com.ehago.kreamzone.entity.Item;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ItemMapper extends BaseMapper<Item, ItemResponseDto> {
}