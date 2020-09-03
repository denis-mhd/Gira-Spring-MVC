package retake.exam.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Entity
@Table(name = "classifications")
public class Classification extends BaseEntity{

    private ClassificationName classificationName;
    private String description;

    public Classification() {
    }

    public Classification(ClassificationName classificationName, String description) {
        this.classificationName = classificationName;
        this.description = description;
    }


    @Enumerated
    public ClassificationName getClassificationName() {
        return classificationName;
    }

    public void setClassificationName(ClassificationName classificationName) {
        this.classificationName = classificationName;
    }

    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
