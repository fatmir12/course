package ptf.unze.course_platform.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ptf.unze.course_platform.model.Lekcija;

public interface LekcijaRepository extends JpaRepository<Lekcija, Long> {
}
