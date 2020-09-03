package retake.exam.web;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import retake.exam.model.binding.TaskAddBindingModel;
import retake.exam.model.entity.User;
import retake.exam.model.service.UserServiceModel;
import retake.exam.service.TaskService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService taskService;
    private final ModelMapper modelMapper;

    public TaskController(TaskService taskService, ModelMapper modelMapper) {
        this.taskService = taskService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/add")
    public String addProduct(Model model){
        if(!model.containsAttribute("taskAddBindingModel")){
            model.addAttribute("taskAddBindingModel", new TaskAddBindingModel());
        }
        return "add-task";
    }

    @PostMapping("/add")
    public String addProductConfirm(@Valid @ModelAttribute("taskAddBindingModel")
                                            TaskAddBindingModel taskAddBindingModel,
                                    BindingResult bindingResult, RedirectAttributes redirectAttributes,
                                    HttpSession httpSession){
        if(bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("taskAddBindingModel", taskAddBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.taskAddBindingModel",bindingResult);
            return "redirect:/tasks/add";
        }else{
            UserServiceModel userServiceModel = (UserServiceModel) httpSession.getAttribute("user");
            this.taskService.addTask(taskAddBindingModel, userServiceModel);
            return "redirect:/";
        }
    }
}
