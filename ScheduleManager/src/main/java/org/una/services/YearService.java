package org.una.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.una.data.dtos.year.YearDetails;
import org.una.data.entities.Year;
import org.una.data.mappers.YearMapper;
import org.una.data.repository.YearRepository;

import java.util.List;
import java.util.Optional;

public final class YearService {

    @Autowired
    YearRepository yearRepository;

    public List<YearDetails> findAll() {
        return YearMapper.MAPPER.yearDetailsFromYearList(yearRepository.findAll());
    }




    public YearDetails findById(Long id) throws Exception {
        Optional<Year> year = yearRepository.findById(id);
        if (!year.isPresent()) {
            throw new Exception(String.format("The Year with the id: %s not found!", id));
        }
        return YearMapper.MAPPER.yearDetailsFromYear(year.get());
    }


    public YearDetails create(YearDetails yearDetails) {
        Year year = YearMapper.MAPPER.yearFromYearDetails(yearDetails);
        return YearMapper.MAPPER.yearDetailsFromYear(yearRepository.saveAndFlush(year));
    }


    public YearDetails update(YearDetails yearDetails) throws Exception {
        Optional<Year> year = yearRepository.findById(yearDetails.getId());
        if (!year.isPresent()) {
            throw new Exception(String.format("The Year with the id: %s not found!", yearDetails.getId()));
        }
        Year yearUpdated = year.get();
        YearMapper.MAPPER.yearFromYearDetails(yearDetails, yearUpdated);
        return YearMapper.MAPPER.yearDetailsFromYear(yearRepository.save(yearUpdated));
    }



    public void deleteById(Long id) throws Exception {
        if (!yearRepository.findById(id).isPresent()) {
            throw new Exception(String.format("The Year with the id: %s not found!", id));
        } else {
            yearRepository.deleteById(id);
        }
    }
}
