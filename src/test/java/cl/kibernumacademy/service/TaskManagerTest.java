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
    void noDeberiaActualizarTareaInexistente() {
        // Act
        Task task = manager.createTask("titulo", "descripcion");
        boolean updated = manager.updateTask(999, "nuevo titulo", " nueva descripcion"); // 999 es un ID que no existe

        // Assert
        assertThat(updated).isFalse();
    }

    @Test
    void deberiaActualizarTarea() {
        // Act
        Task task = manager.createTask("titulo", "descripcion");
        boolean updated = manager.updateTask(task.getId(), "nuevo titulo", "nueva descripcion");

        // Assert
        assertThat(updated).isTrue();
        assertThat(task.getTitle()).isEqualTo("nuevo titulo");
        assertThat(task.getDescription()).isEqualTo("nueva descripcion");
    }

    @Test
    void noDeberiaEliminarTareaInexistente() {
        // Act
        Task task = manager.createTask("titulo", "descripcion");
        boolean removed = manager.deleteTask(999, "titulo", "descripcion"); // 999 es un ID que no existe

        // Assert
        assertThat(removed).isFalse();
    }

    @Test
    void deberiaEliminarTask() {
        // Act
        Task task = manager.createTask("titulo", "descripcion");
        boolean removed = manager.deleteTask(task.getId());

        // Assert
        assertThat(removed).isTrue();
        assertThat(manager.getAll()).isEmpty();
    }
}
