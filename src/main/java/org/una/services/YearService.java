/**
 * @author: ArnoldG6.
 * @version: 1.0
 * Contact me via "arnoldgq612@gmail.com".
 *
 */
package org.una.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.una.data.dtos.data.year.YearDetails;
import org.una.data.entities.Year;
import org.una.mappers.EntityMapper;
import org.una.data.repository.YearRepository;

import java.util.List;
import java.util.Optional;

public final class YearService {

    @Autowired
    private YearRepository yearRepository;

    private EntityMapper entityMapper;
    public YearService(){
        this.entityMapper = new EntityMapper();
    }
    public List<YearDetails> findAll() {
        return entityMapper.yearDetailsFromYearList(yearRepository.findAll());
    }




    public YearDetails findById(Long id) throws Exception {
        Optional<Year> year = yearRepository.findById(id);
        if (!year.isPresent()) {
            throw new Exception(String.format("The Year with the id: %s not found!", id));
        }
        return entityMapper.yearDetailsFromYear(year.get());
    }


    public YearDetails create(YearDetails yearDetails) {
        Year year = entityMapper.yearFromYearDetails(yearDetails);
        return entityMapper.yearDetailsFromYear(yearRepository.saveAndFlush(year));
    }

    public void deleteById(Long id) throws Exception {
        if (!yearRepository.findById(id).isPresent()) {
            throw new Exception(String.format("The Year with the id: %s not found!", id));
        } else {
            yearRepository.deleteById(id);
        }
    }
}
