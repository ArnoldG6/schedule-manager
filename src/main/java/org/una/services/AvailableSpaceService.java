/**
 * @author: ArnoldG6.
 * @version: 1.0
 * Contact me via "arnoldgq612@gmail.com".
 *
 */
package org.una.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.una.data.dtos.data.available_space.AvailableSpaceDetails;
import org.una.data.dtos.data.available_space.AvailableSpaceInput;
import org.una.data.entities.AvailableSpace;
import org.una.mappers.AvailableSpaceMapper;
import org.una.data.repository.AvailableSpaceRepository;
import org.una.data.repository.BlockRepository;
import org.una.data.repository.StudentRepository;

import java.util.HashSet;
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
        try{
            AvailableSpace availableSpace = availableSpaceMapper.availableSpaceFromAvailableSpaceInput(availableSpaceInput);
            return availableSpaceMapper.availableSpaceDetailsFromAvailableSpace(availableSpaceRepository.saveAndFlush(availableSpace));
        }catch(Exception e){
            e.printStackTrace();
            throw e;
        }
    }




    public void deleteById(Long id) throws Exception {
        if (!availableSpaceRepository.findById(id).isPresent()) {
            throw new Exception(String.format("The AvailableSpaceRepository with the id: %s not found!", id));
        } else {
            availableSpaceRepository.deleteById(id);
        }
    }


    public void deleteAll(Iterable<Long> availableSpaceIDs) throws Exception {
        HashSet<AvailableSpace> availableSpacesToDelete = new HashSet<>();
        Optional<AvailableSpace> availableSpace;
        for(Long id: availableSpaceIDs){
            availableSpace =  availableSpaceRepository.findById(id);
            if(!availableSpace.isPresent())
                throw new Exception(String.format("AvailableSpace with id %s was not found.",id));
            availableSpacesToDelete.add(availableSpace.get());
        }
        try{
            availableSpaceRepository.deleteAll(availableSpacesToDelete);
        }catch(Exception e){
            e.printStackTrace();
        }

    }


}