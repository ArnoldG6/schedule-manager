package org.una.data.mappers;


import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import org.una.data.dtos.year.YearDetails;
import org.una.data.entities.Year;

import java.util.List;

@Mapper
public interface YearMapper {
    YearMapper MAPPER = Mappers.getMapper(YearMapper.class);
    public YearDetails yearDetailsFromYear(Year year);
    public List<YearDetails> yearDetailsFromYearList(List<Year> year);
    public Year yearFromYearDetails(YearDetails yearDetails);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public void yearFromYearDetails(YearDetails dto, @MappingTarget Year year);



}