package org.una.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.una.data.dtos.data.available_space.AvailableSpaceDetails;
import org.una.data.dtos.data.available_space.AvailableSpaceInput;
import org.una.data.entities.AvailableSpace;
import org.una.data.entities.Block;
import org.una.data.entities.Student;
import org.una.data.mappers.AvailableSpaceMapper;
import org.una.data.repository.AvailableSpaceRepository;
import org.una.data.repository.BlockRepository;
import org.una.data.repository.StudentRepository;

import java.util.List;
import java.util.Optional;

@Service
public final class AvailableSpaceService {

    @Autowired
    private AvailableSpaceMapper availableSpaceMapper;
    @Autowired
    private AvailableSpaceRepository availableSpaceRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private BlockRepository blockRepository;

    public List<AvailableSpaceDetails> findAll() {
        return availableSpaceMapper.availableSpaceDetailsFromAvailableSpaceList(availableSpaceRepository.findAll());
    }




    public AvailableSpaceDetails findById(Long id) throws Exception {
        Optional<AvailableSpace> availableSpace = availableSpaceRepository.findById(id);
        if (!availableSpace.isPresent()) {
            throw new Exception(String.format("The AvailableSpace with the id: %s not found!", id));
        }
        return availableSpaceMapper.availableSpaceDetailsFromAvailableSpace(availableSpace.get());
    }


    public AvailableSpaceDetails create(AvailableSpaceInput availableSpaceInput) throws Exception {
        AvailableSpace availableSpace = availableSpaceMapper.availableSpaceFromAvailableSpaceInput(availableSpaceInput);
        return availableSpaceMapper.availableSpaceDetailsFromAvailableSpace(availableSpaceRepository.saveAndFlush(availableSpace));
    }



    public void deleteById(Long id) throws Exception {
        if (!availableSpaceRepository.findById(id).isPresent()) {
            throw new Exception(String.format("The AvailableSpaceRepository with the id: %s not found!", id));
        } else {
            availableSpaceRepository.deleteById(id);
        }
    }


}