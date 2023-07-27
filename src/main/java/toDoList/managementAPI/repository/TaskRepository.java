package toDoList.managementAPI.repository;

import lombok.extern.log4j.Log4j2;
import toDoList.managementAPI.conn.ConnectionFactory;
import toDoList.managementAPI.domain.Status;
import toDoList.managementAPI.domain.Task;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Log4j2
public class TaskRepository {

    public static Optional<Task> findByExactTitle(String title) {
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = createPreparedStatementFindByExactTitle(conn, title);
             ResultSet rs = ps.executeQuery()) {

            if (!rs.next()) return Optional.empty();
            Status status = Status.getStatusByValue(rs.getInt("status_id"));

            return Optional.of(Task.TaskBuilder.builder()
                    .id(rs.getInt("id"))
                    .title(rs.getString("title"))
                    .description(rs.getString("description"))
                    .dueDate(rs.getString("due_date"))
                    .status(status)
                    .build());

        } catch (SQLException exception) {
            log.error("Error while trying to find task");
            exception.printStackTrace();
        }
        return Optional.empty();
    }

    private static PreparedStatement createPreparedStatementFindByExactTitle(Connection conn, String title) throws SQLException {
        String sql = "SELECT * FROM `to_do_list`.`task` WHERE title = ?;";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, title);
        return ps;
    }

    public static Optional<Task> findById(Integer id) {
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = createPreparedStatementFindById(conn, id);
             ResultSet rs = ps.executeQuery()) {

            if (!rs.next()) return Optional.empty();
            Status status = Status.getStatusByValue(rs.getInt("status_id"));

            return Optional.of(Task.TaskBuilder.builder()
                    .id(rs.getInt("id"))
                    .title(rs.getString("title"))
                    .description(rs.getString("description"))
                    .dueDate(rs.getString("due_date"))
                    .status(status)
                    .build());

        } catch (SQLException exception) {
            log.error("Error while trying to find task");
            exception.printStackTrace();
        }
        return Optional.empty();
    }

    private static PreparedStatement createPreparedStatementFindById(Connection conn, Integer id) throws SQLException {
        String sql = "SELECT * FROM `to_do_list`.`task` WHERE id = ?;";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, id);
        return ps;
    }

    public static List<Task> findAll() {
        String sql = "SELECT * FROM `to_do_list`.`task`;";
        List<Task> tasks = new ArrayList<>();

        try (Connection conn = ConnectionFactory.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Status status = Status.getStatusByValue(rs.getInt("status_id"));
                Task task = Task.TaskBuilder.builder()
                        .id(rs.getInt("id"))
                        .title(rs.getString("title"))
                        .description(rs.getString("description"))
                        .dueDate(rs.getString("due_date"))
                        .status(status)
                        .build();
                tasks.add(task);
            }

        } catch (SQLException exception) {
            log.error("Error while trying to find task");
            exception.printStackTrace();
        }
        return tasks;
    }

    public static void save(Task task) {
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = createPreparedStatementSave(conn, task)
        ) {

            ps.execute();
            log.info("Inserted task '{}' in the database", task.getTitle());

        } catch (SQLException exception) {
            log.error("Error while trying to save task");
            exception.printStackTrace();
        }
    }

    private static PreparedStatement createPreparedStatementSave(Connection conn, Task task) throws SQLException {
        String sql = """
                INSERT INTO `to_do_list`.`task` (`title`, `description`, `due_date`, `status_id`)
                VALUES (?, ?, ?, ?);""";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, task.getTitle());
        ps.setString(2, task.getDescription());
        ps.setString(3, task.getDueDate());
        ps.setInt(4, task.getStatus().getVALUE());
        return ps;
    }

    public static void update(Task task) {
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = createPreparedStatementUpdate(conn, task)
        ) {

            ps.execute();
            log.info("Updated task '{}' in the database", task.getId());

        } catch (SQLException exception) {
            log.error("Error while trying to save task");
            exception.printStackTrace();
        }
    }

    private static PreparedStatement createPreparedStatementUpdate(Connection conn, Task task) throws SQLException {
        String sql = """
                UPDATE `to_do_list`.`task`
                SET `title` = ?, `description` = ?, `due_date` = ?, `status_id` = ?
                WHERE `id` = ?;
                """;
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, task.getTitle());
        ps.setString(2, task.getDescription());
        ps.setString(3, task.getDueDate());
        ps.setInt(4, task.getStatus().getVALUE());
        ps.setInt(5, task.getId());
        return ps;
    }

    public static void delete(Integer id) {
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = createPreparedStatementDelete(conn, id)
        ) {

            ps.execute();
            log.info("Deleted task '{}' in the database", id);

        } catch (SQLException exception) {
            log.error("Error while trying to save task");
            exception.printStackTrace();
        }
    }

    private static PreparedStatement createPreparedStatementDelete(Connection conn, Integer id) throws SQLException {
        String sql = "DELETE FROM `to_do_list`.`task` WHERE id = ?;";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, id);
        return ps;
    }
}
