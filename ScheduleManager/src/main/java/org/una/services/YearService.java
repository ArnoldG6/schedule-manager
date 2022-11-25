package org.una.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.una.data.dtos.data.year.YearDetails;
import org.una.data.entities.Year;
import org.una.data.mappers.YearMapper;
import org.una.data.repository.YearRepository;

import java.util.List;
import java.util.Optional;

public final class YearService {

    @Autowired
    YearRepository yearRepository;

    private YearMapper yearMapper;
    public YearService(){
        this.yearMapper = new YearMapper();
    }
    public List<YearDetails> findAll() {
        return yearMapper.yearDetailsFromYearList(yearRepository.findAll());
    }




    public YearDetails findById(Long id) throws Exception {
        Optional<Year> year = yearRepository.findById(id);
        if (!year.isPresent()) {
            throw new Exception(String.format("The Year with the id: %s not found!", id));
        }
        return yearMapper.yearDetailsFromYear(year.get());
    }


    public YearDetails create(YearDetails yearDetails) {
        Year year = yearMapper.yearFromYearDetails(yearDetails);
        return yearMapper.yearDetailsFromYear(yearRepository.saveAndFlush(year));
    }

    public void deleteById(Long id) throws Exception {
        if (!yearRepository.findById(id).isPresent()) {
            throw new Exception(String.format("The Year with the id: %s not found!", id));
        } else {
            yearRepository.deleteById(id);
        }
    }
}
