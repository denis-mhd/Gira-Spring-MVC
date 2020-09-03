package retake.exam.web;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import retake.exam.model.binding.UserLoginBindingModel;
import retake.exam.model.service.TaskServiceModel;
import retake.exam.service.TaskService;
import retake.exam.service.UserService;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class HomeController {
    private final TaskService taskService;
    private final UserService userService;
    private final ModelMapper modelMapper;

    @Autowired
    public HomeController(TaskService taskService, UserService userService, ModelMapper modelMapper) {
        this.taskService = taskService;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/")
    public String index(HttpSession httpSession, Model model) {
        if (httpSession.getAttribute("user") == null) {
            return "index";
        } else {
            List<TaskServiceModel> tasks = this.taskService.getAll();
            model.addAttribute("tasks", tasks);
            return "home";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession httpSession) {
        httpSession.invalidate();
        return "redirect:/";
    }

    @GetMapping("/progress/{id}")
    public String progressChange(@PathVariable("id")String id, RedirectAttributes redirectAttributes) {
        this.taskService.getProgresses(id);
        List<TaskServiceModel> tasks = this.taskService.getAll();
        redirectAttributes.addFlashAttribute("tasks", tasks);

        return "redirect:/";
    }
}
