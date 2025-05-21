package snghk.demologin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import snghk.demologin.domain.LoginHistory;

public interface LoginHistoryRepository extends JpaRepository<LoginHistory, Long> {
}
