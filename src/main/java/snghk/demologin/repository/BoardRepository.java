package snghk.demologin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import snghk.demologin.domain.Board;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {

}
