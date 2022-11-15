package org.una.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.una.data.entities.Block;

@Repository
public interface BlockRepository extends JpaRepository<Block, Long> {

}