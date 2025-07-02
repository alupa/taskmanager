package cl.kibernumacademy.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

import cl.kibernumacademy.model.Task;

public class TaskManagerTest {
    private TaskManager manager;

    @BeforeEach
    void setUp() {
        // Arrange
        manager = new TaskManager();
    }

    @Test
    void deberiaCrearUnaTarea() {
        // Act
        Task task = manager.createTask("titulo", "descripcion");

        // Assert
        assertThat(task.getId()).isEqualTo(1);
        assertThat(manager.getAll()).hasSize(1);
    }

    @Test
    void deberiaActualizarTarea() {
        // Act
        Task task = manager.createTask("titulo", "descripcion");
        assertThat(manager.getAll()).contains(task); // Validar que la tarea existe antes de actualizarla
        boolean updated = manager.updateTask(task.getId(), "nuevo titulo", "nueva descripcion");

        // Assert
        assertThat(updated).isTrue();
        assertThat(task.getTitle()).isEqualTo("nuevo titulo");
        assertThat(task.getDescription()).isEqualTo("nueva descripcion");
    }

    @Test
    void deberiaEliminarTask() {
        // Act
        Task task = manager.createTask("titulo", "descripcion");
        assertThat(manager.getAll()).contains(task); // Validar que la tarea existe antes de eliminarla
        boolean removed = manager.deleteTask(task.getId());

        // Assert
        assertThat(removed).isTrue();
        assertThat(manager.getAll()).isEmpty();
    }
}
