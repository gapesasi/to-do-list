package toDoList.managementAPI.domain;

import java.util.Objects;

public class Task {
    private Integer id;
    private String title;
    private String description;
    private String dueDate;
    private Status status;

    public static final class TaskBuilder {
        private Integer id;
        private String title;
        private String description;
        private String dueDate;
        private Status status;

        private TaskBuilder() {
        }

        public static TaskBuilder builder() {
            return new TaskBuilder();
        }

        public TaskBuilder id(Integer id) {
            this.id = id;
            return this;
        }

        public TaskBuilder title(String title) {
            this.title = title;
            return this;
        }

        public TaskBuilder description(String description) {
            this.description = description;
            return this;
        }

        public TaskBuilder dueDate(String dueDate) {
            this.dueDate = dueDate;
            return this;
        }

        public TaskBuilder status(Status status) {
            this.status = status;
            return this;
        }

        public Task build() {
            Task task = new Task();
            task.dueDate = this.dueDate;
            task.id = this.id;
            task.status = this.status;
            task.title = this.title;
            task.description = this.description;
            return task;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(id, task.id) && Objects.equals(title, task.title) && Objects.equals(description, task.description) && Objects.equals(dueDate, task.dueDate) && status == task.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, dueDate, status);
    }

    @Override
    public String toString() {
        return "Task (" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", dueDate='" + dueDate + '\'' +
                ", status=" + status +
                ')';
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getDueDate() {
        return dueDate;
    }

    public Status getStatus() {
        return status;
    }
}
