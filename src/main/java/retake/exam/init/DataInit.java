package retake.exam.init;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import retake.exam.service.ClassificationService;

@Component
public class DataInit implements CommandLineRunner {
    private final ClassificationService classificationService;

    public DataInit(ClassificationService classificationService) {
        this.classificationService = classificationService;
        ;
    }

    @Override
    public void run(String... args) throws Exception {
        this.classificationService.initCategories();
    }
}
