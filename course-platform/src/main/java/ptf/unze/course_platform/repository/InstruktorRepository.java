package ptf.unze.course_platform.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ptf.unze.course_platform.model.Instruktor;

public interface InstruktorRepository extends JpaRepository<Instruktor, Long> {
}
