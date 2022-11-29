package org.una.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.una.data.entities.Year;

@Repository
public interface YearRepository extends JpaRepository<Year, Long> {

}
