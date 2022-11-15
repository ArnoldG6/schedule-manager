package org.una.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.una.data.dtos.available_space.AvailableSpaceDetails;
import org.una.data.dtos.available_space.AvailableSpaceInput;
import org.una.data.entities.AvailableSpace;
import org.una.data.mappers.AvailableSpaceMapper;
import org.una.data.repository.AvailableSpaceRepository;

import java.util.List;
import java.util.Optional;

@Service
public final class AvailableSpaceService {


    @Autowired
    AvailableSpaceRepository availableSpaceRepository;

    public List<AvailableSpaceDetails> findAll() {
        return AvailableSpaceMapper.MAPPER.availableSpaceDetailsFromAvailableSpaceList(availableSpaceRepository.findAll());
    }




    public AvailableSpaceDetails findById(Long id) throws Exception {
        Optional<AvailableSpace> availableSpace = availableSpaceRepository.findById(id);
        if (!availableSpace.isPresent()) {
            throw new Exception(String.format("The AvailableSpace with the id: %s not found!", id));
        }
        return AvailableSpaceMapper.MAPPER.availableSpaceDetailsFromAvailableSpace(availableSpace.get());
    }


    public AvailableSpaceDetails create(AvailableSpaceInput availableSpaceInput) {
        AvailableSpace availableSpace = AvailableSpaceMapper.MAPPER.availableSpaceFromAvailableSpaceInput(availableSpaceInput);
        return AvailableSpaceMapper.MAPPER.availableSpaceDetailsFromAvailableSpace(availableSpaceRepository.saveAndFlush(availableSpace));
    }


    public AvailableSpaceDetails update(AvailableSpaceInput availableSpaceInput) throws Exception {
        Optional<AvailableSpace> availableSpace = availableSpaceRepository.findById(availableSpaceInput.getId());
        if (!availableSpace.isPresent()) {
            throw new Exception(String.format("The AvailableSpace with the id: %s not found!", availableSpaceInput.getId()));
        }
        AvailableSpace availableSpaceUpdated = availableSpace.get();
        AvailableSpaceMapper.MAPPER.availableSpaceFromAvailableSpaceInput(availableSpaceInput);
        return AvailableSpaceMapper.MAPPER.availableSpaceDetailsFromAvailableSpace(availableSpaceRepository.save(availableSpaceUpdated));
    }



    public void deleteById(Long id) throws Exception {
        if (!availableSpaceRepository.findById(id).isPresent()) {
            throw new Exception(String.format("The AvailableSpaceRepository with the id: %s not found!", id));
        } else {
            availableSpaceRepository.deleteById(id);
        }
    }


}