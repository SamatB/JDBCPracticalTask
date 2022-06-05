package peaksoft.dao;

import peaksoft.model.User;
import peaksoft.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJdbcImpl implements UserDao {

    public UserDaoJdbcImpl() {

    }

    public void createUsersTable() {
        String sql = "create table if not exists users(" +
                "id serial primary key," +
                "name varchar(50) not null," +
                "last_name varchar(50) not null," +
                "age int2);";
        try (Connection connection = Util.connection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
            System.out.println("Table with name \"users\" created!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void dropUsersTable() {
        String sql = "drop table if exists users";
        try (Connection connection = Util.connection();
             Statement statement = connection.createStatement()){
            statement.executeUpdate(sql);
            System.out.println("The table was dropped");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String sql = "insert into users(name, last_name, age) values (?, ?, ?);";
        try (Connection connection = Util.connection();
             PreparedStatement prestat = connection.prepareStatement(sql)) {
            prestat.setString(1, name);
            prestat.setString(2, lastName);
            prestat.setByte(3, age);
            prestat.executeUpdate();
            System.out.println("User " + name + " saved successfully!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void removeUserById(long id) {
        String sql = "delete from users where id = ?;";
        try (Connection connection = Util.connection();
             PreparedStatement prestat = connection.prepareStatement(sql)) {
            prestat.setLong(1, id);
            prestat.executeUpdate();
            System.out.println("Chosen user with " + id + " id was deleted successfully");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<User> getAllUsers() {
        String sql = "select * from users;";
        List<User> users = new ArrayList<>();
        User user;
        try (Connection conn = Util.connection();
             Statement statement = conn.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)){
            while (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("last_name"));
                user.setAge(resultSet.getByte("age"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public void cleanUsersTable() {
        String sql = "truncate users";
        try (Connection connection =Util.connection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
            System.out.println("The table was cleaned");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}