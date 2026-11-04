package ptf.unze.course_platform.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ptf.unze.course_platform.model.Kurs;

public interface KursRepository extends JpaRepository<Kurs, Long> {
}
