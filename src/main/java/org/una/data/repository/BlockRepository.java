package org.una.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.una.data.entities.Block;
import org.una.data.entities.Year;

import javax.swing.text.html.Option;
import java.util.Optional;

@Repository
public interface BlockRepository extends JpaRepository<Block, Long> {
}
