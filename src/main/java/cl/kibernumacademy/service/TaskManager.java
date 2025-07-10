package cl.kibernumacademy.service;

import cl.kibernumacademy.model.Task;
import java.util.*;

public class TaskManager {
    private final List<Task> tasks = new ArrayList<>();
    private int nextId = 1;

    public Task createTask(String title, String description) {
        Task task = new Task(nextId++, title, description);
        tasks.add(task);
        return task;
    }

    // GREEN
    // public boolean updateTask(int id, String newTitle, String newDescription) {
    //     for (Task task : tasks) {
    //         if (task.getId() == id) {
    //             task.setTitle(newTitle);
    //             task.setDescription(newDescription);
    //             return true;
    //         }
    //     }
    //     return false;
    // }

    // REFACTOR
    public boolean updateTask(int id, String newTitle, String newDescription) {
        return findTaskById(id)
            .map(i -> {
                i.setTitle(newTitle);
                i.setDescription(newDescription);
                return true;
            })
            .orElse(false);
    }

    private Optional<Task> findTaskById(int id) {
        return tasks.stream()
            .filter(i -> i.getId() == id)
            .findFirst();
    }

    // GREEN
    // public boolean deleteTask(int id){
    //     for(Task task : tasks){
    //         if(task.getId() == id){
    //             tasks.remove(task);
    //             return true;
    //         }
    //     }
    //     return false;
    // }

    // REFACTOR
    public boolean deleteTask(int id) {
        return tasks.removeIf(task -> task.getId() == id);
    }

    // GREEN
    // public List<Task> getAll() {
    //     return new ArrayList<>(tasks);
    // }

    // REFACTOR
    public List<Task> getAll() {
        return Collections.unmodifiableList(tasks);
    }
} 