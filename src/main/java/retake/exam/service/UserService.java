package retake.exam.service;

import retake.exam.model.binding.UserLoginBindingModel;
import retake.exam.model.binding.UserRegisterBindingModel;
import retake.exam.model.entity.User;
import retake.exam.model.service.UserServiceModel;

public interface UserService {

    void register(UserRegisterBindingModel userRegisterBindingModel);

    UserServiceModel findByEmail(String email);

    UserServiceModel findUserById(String id);

    UserLoginBindingModel findUserByIdAndMapToBinding(String id);
}
