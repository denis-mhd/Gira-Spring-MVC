package retake.exam.service.serviceImpl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import retake.exam.model.binding.UserLoginBindingModel;
import retake.exam.model.binding.UserRegisterBindingModel;
import retake.exam.model.entity.User;
import retake.exam.model.service.UserServiceModel;
import retake.exam.repositoriy.UserRepository;
import retake.exam.service.UserService;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void register(UserRegisterBindingModel userRegisterBindingModel) {
        UserServiceModel userServiceModel = this.modelMapper.map(userRegisterBindingModel, UserServiceModel.class);
        User user = this.modelMapper.map(userServiceModel, User.class);
        this.modelMapper.map(this.userRepository.saveAndFlush(user), UserServiceModel.class);
    }

    @Override
    public UserServiceModel findByEmail(String email) {
        return this.userRepository
                .findByEmail(email)
                .map(user -> this.modelMapper.map(user, UserServiceModel.class))
                .orElse(null);
    }

    @Override
    public UserServiceModel findUserById(String id) {
        return this.userRepository
                .findById(id)
                .map(user -> this.modelMapper.map(user, UserServiceModel.class))
                .orElse(null);
    }

    @Override
    public UserLoginBindingModel findUserByIdAndMapToBinding(String id) {
        return this.modelMapper.map(this.userRepository.findById(id), UserLoginBindingModel.class);
    }
}
