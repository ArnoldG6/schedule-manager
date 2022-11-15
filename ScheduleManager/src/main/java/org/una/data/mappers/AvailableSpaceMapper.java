package org.una.data.mappers;

import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import org.una.data.dtos.available_space.AvailableSpaceDetails;
import org.una.data.dtos.available_space.AvailableSpaceInput;
import org.una.data.entities.AvailableSpace;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AvailableSpaceMapper {
    AvailableSpaceMapper MAPPER = Mappers.getMapper(AvailableSpaceMapper.class);
    public AvailableSpaceDetails availableSpaceDetailsFromAvailableSpace(AvailableSpace availableSpace);
    public List<AvailableSpaceDetails> availableSpaceDetailsFromAvailableSpaceList(List<AvailableSpace> availableSpace);
    public AvailableSpace availableSpaceFromAvailableSpaceDetails(AvailableSpaceDetails availableSpaceDetails);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public void availableSpaceFromAvailableSpaceDetails(AvailableSpaceDetails dto, @MappingTarget AvailableSpace availableSpace);

    AvailableSpace availableSpaceFromAvailableSpaceInput(AvailableSpaceInput availableSpaceInput);
}