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

    public boolean updateTask(int id, String newTitle, String newDescription) {
        for (Task task : tasks) {
            if (task.getId() == id) {
                task.setTitle(newTitle);
                task.setDescription(newDescription);
                return true;
            }
        }
        return false;
    }

    public boolean deleteTask(int id) {
        return tasks.removeIf(task -> task.getId() == id);
    }

    public List<Task> getAll() {
        return new ArrayList<>(tasks);
    }
} 