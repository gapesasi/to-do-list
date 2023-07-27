package toDoList.managementAPI.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import toDoList.managementAPI.domain.Status;
import toDoList.managementAPI.domain.Task;

import java.util.List;
class TaskServiceTest {

    @Test
    void findByExactTitle_ReturnTheFirstTaskFound_WhenValidTitleIsGiven() {
        Task findTask = TaskService.findByExactTitle("Finish Crud");
        System.out.println(findTask);
        Assertions.assertNotNull(findTask);
    }

    @Test
    void findByExactTitle_ThrowAnIllegalArgumentException_WhenInvalidTitleIsGiven() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> TaskService.findByExactTitle("Wash"));
    }

    @Test
    void findById_ReturnATask_WhenValidIdIsGiven() {
        Task findTask = TaskService.findById(1);
        Assertions.assertNotNull(findTask);
    }

    @Test
    void findById_ThrowAnIllegalArgumentException_WhenInvalidIdIsGiven() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> TaskService.findById(0));
    }

    @Test
    void findAll_ReturnAListOfTasks() {
        List<Task> tasks = TaskService.findAll();
        Assertions.assertTrue(tasks.size() > 0);
    }

    @Test
    void save_SaveATaskInTheDatabase_WhenValidTaskIsGiven() {
        Task task = Task.TaskBuilder.builder()
                .title("Finish Crud")
                .description("Write unitary tests")
                .dueDate("2023-07-27")
                .status(Status.IN_PROGRESS)
                .build();
        TaskService.save(task);

        Task findTask = TaskService.findByExactTitle(task.getTitle());
        Assertions.assertNotNull(findTask);
    }

    @Test
    void update_UpdateATaskInTheDatabase_WhenValidTaskIsGiven() {

        Task task = Task.TaskBuilder.builder()
                .id(1)
                .title("Wash Clothes")
                .description("Go to The Laudring Machine and Wash Clothes")
                .dueDate("2023-07-27")
                .status(Status.DONE)
                .build();
        TaskService.update(task);
        Task findTask = TaskService.findById(task.getId());

        Assertions.assertEquals(task, findTask);
    }

    @Test
    void update_ThrowAnIllegalArgumentException_WhenInvalidTaskIdIsGiven() {
        Task task = Task.TaskBuilder.builder()
                .id(0)
                .build();

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> TaskService.update(task));
    }

    @Test
    void delete_DeleteATaskInTheDatabase_WhenValidIdIsGiven() {
        TaskService.delete(4);

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> TaskService.findById(4));
    }

    @Test
    void delete_ThrowAnIllegalArgumentException_WhenInvalidIdIsGiven() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> TaskService.delete(0));
    }
}