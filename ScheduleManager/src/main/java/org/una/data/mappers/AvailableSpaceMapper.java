package org.una.data.mappers;

import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import org.una.data.dtos.available_space.AvailableSpaceDetails;
import org.una.data.entities.AvailableSpace;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AvailableSpaceMapper {
    AvailableSpaceMapper MAPPER = Mappers.getMapper(AvailableSpaceMapper.class);
    public AvailableSpaceDetails priorityDetailsFromPriority(AvailableSpace priority);
    public List<AvailableSpaceDetails> priorityDetailsFromPriorityList(List<AvailableSpace> priority);
    public AvailableSpace priorityFromPriorityDetails(AvailableSpaceDetails priorityDetails);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public void priorityFromPriorityDetails(AvailableSpaceDetails dto, @MappingTarget AvailableSpace priority);
}