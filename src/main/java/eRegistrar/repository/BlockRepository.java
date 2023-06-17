package eRegistrar.repository;

import eRegistrar.model.Block;
import eRegistrar.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BlockRepository extends JpaRepository<Block,Integer> {

    List<Block> findByblockNameContaining(String blockName);

    List<Block> findAllByStartingDateEqualsOrEndingDateEquals(LocalDate startingDate, LocalDate endingDate);
}
