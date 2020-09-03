package retake.exam.repositoriy;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import retake.exam.model.entity.Classification;

@Repository
public interface ClassificationRepository extends JpaRepository<Classification, String> {

}
