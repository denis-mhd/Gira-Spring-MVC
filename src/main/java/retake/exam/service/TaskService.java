package retake.exam.service;

import retake.exam.model.binding.TaskAddBindingModel;
import retake.exam.model.entity.Progress;
import retake.exam.model.service.ProgressServiceModel;
import retake.exam.model.service.TaskServiceModel;
import retake.exam.model.service.UserServiceModel;

import java.util.List;

public interface TaskService {

    List<TaskServiceModel> getAll();

    void addTask(TaskAddBindingModel taskAddBindingModel, UserServiceModel userServiceModel);

    void getProgresses(String id);

    TaskServiceModel getTask(String id);
}
