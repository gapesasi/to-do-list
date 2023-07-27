package toDoList.managementAPI.service;

import toDoList.managementAPI.domain.Task;
import toDoList.managementAPI.repository.TaskRepository;

import java.util.List;
import java.util.Optional;

public class TaskService {

    public static Task findByExactTitle(String title) {
        Optional<Task> task = TaskRepository.findByExactTitle(title);
        return task.orElseThrow(IllegalArgumentException::new);
    }

    public static Task findById(Integer id) {
        Optional<Task> task = TaskRepository.findById(id);
        return task.orElseThrow(IllegalArgumentException::new);
    }

    public static List<Task> findAll() {
        return TaskRepository.findAll();
    }

    public static void save(Task task) {
        TaskRepository.save(task);
    }

    public static void update(Task task) {
        if (!isIdValid(task.getId())) throw new IllegalArgumentException("Invalid Id");

        TaskRepository.update(task);
    }

    public static void delete(Integer id) {
        if (!isIdValid(id)) throw new IllegalArgumentException("Invalid Id");

        TaskRepository.delete(id);
    }

    private static boolean isIdValid(Integer id) {
        return id > 0;
    }
}