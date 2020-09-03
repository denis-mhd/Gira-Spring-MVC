package retake.exam.service.serviceImpl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import retake.exam.model.entity.Classification;
import retake.exam.model.entity.ClassificationName;
import retake.exam.repositoriy.ClassificationRepository;
import retake.exam.service.ClassificationService;

import java.util.Arrays;

@Service
public class ClassificationServiceImpl implements ClassificationService {
    private final ClassificationRepository classificationRepository;
    private final ModelMapper modelMapper;

    public ClassificationServiceImpl(ClassificationRepository classificationRepository, ModelMapper modelMapper) {
        this.classificationRepository = classificationRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void initCategories() {
        if(this.classificationRepository.count() == 0){
            Arrays.stream(ClassificationName.values())
                    .forEach(classificationName -> {
                        this.classificationRepository
                                .save(new Classification(classificationName,
                                        String.format("Description for %s"
                                                , classificationName.name())));
                    });
        }
    }
}
