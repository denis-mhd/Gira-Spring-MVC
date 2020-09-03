package retake.exam.service.serviceImpl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import retake.exam.model.binding.TaskAddBindingModel;
import retake.exam.model.entity.*;
import retake.exam.model.service.TaskServiceModel;
import retake.exam.model.service.UserServiceModel;
import retake.exam.repositoriy.ClassificationRepository;
import retake.exam.repositoriy.TaskRepository;
import retake.exam.service.TaskService;
import retake.exam.service.UserService;

import java.util.List;

import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final UserService userService;
    private final ClassificationRepository classificationRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository, UserService userService, ClassificationRepository classificationRepository, ModelMapper modelMapper) {
        this.taskRepository = taskRepository;
        this.userService = userService;
        this.classificationRepository = classificationRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<TaskServiceModel> getAll() {
        return this.taskRepository.findAll().stream().map(e -> this.modelMapper.map(e, TaskServiceModel.class)).collect(Collectors.toList());
    }

    @Override
    public void addTask(TaskAddBindingModel taskAddBindingModel, UserServiceModel userServiceModel) {
        TaskServiceModel taskServiceModel = this.modelMapper.map(taskAddBindingModel, TaskServiceModel.class);
        Classification classification = null;
        for (Classification c: this.classificationRepository.findAll()) {
            if (c.getClassificationName().name().equals(taskAddBindingModel.getClassification().name())){
                classification = c;
            }

        }
        taskServiceModel.setClassification(classification);
        taskServiceModel.setUser(this.modelMapper.map(this.userService.findUserById(userServiceModel.getId()), User.class));
        taskServiceModel.setProgress(Progress.OPEN);
        Task task = this.modelMapper.map(taskServiceModel, Task.class);
        this.taskRepository.saveAndFlush(task);
    }

    @Override
    public void getProgresses(String id) {
        Task task = this.taskRepository.findById(id).orElse(null);
        boolean isCompleted = false;
        switch (task.getProgress().name()) {
            case "OPEN" -> task.setProgress(Progress.IN_PROGRESS);
            case "IN_PROGRESS" -> task.setProgress(Progress.COMPLETED);
            case "COMPLETED" -> isCompleted = true;
        }
            if(!isCompleted){
                this.taskRepository.saveAndFlush(task);
            }else{
                this.taskRepository.delete(task);
            }



    }

    @Override
    public TaskServiceModel getTask(String id) {
        return this.modelMapper.map(this.taskRepository.findById(id), TaskServiceModel.class);
    }
}
