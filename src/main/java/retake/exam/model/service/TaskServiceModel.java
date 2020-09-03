package retake.exam.model.service;


import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;
import retake.exam.model.entity.Classification;
import retake.exam.model.entity.Progress;
import retake.exam.model.entity.User;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;

import java.time.LocalDate;


public class TaskServiceModel extends BaseServiceModel{

    private String name;
    private String description;
    private Progress progress;
    private LocalDate dueDate;
    private Classification classification;
    private User user;

    public TaskServiceModel() {
    }

    @Length(min = 3, max = 20, message = "Name length must be between 3 and 20 characters!")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Length(min = 5, message = "Description min length must be minimum 5 characters!")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @NotNull(message = "Enter valid progress!")
    public Progress getProgress() {
        return progress;
    }

    public void setProgress(Progress progress) {
        this.progress = progress;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @FutureOrPresent(message = "The date can not be in the past!")
    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    @NotNull(message = "Classification cannot be null!")
    public Classification getClassification() {
        return classification;
    }

    public void setClassification(Classification classification) {
        this.classification = classification;
    }

    @NotNull
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


}
