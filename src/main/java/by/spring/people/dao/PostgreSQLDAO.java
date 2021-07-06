package by.spring.people.dao;

import by.spring.people.models.Person;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
//@PropertySource("classpath:musicPlayer.properties")
public class PostgreSQLDAO implements PersonDAO {

    private static final String URL = "jdbc:postgresql://localhost:5432/first_db";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "12345678";

    private static Connection connection;

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public List<Person> index() {
        List<Person> people = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            String SQL = "SELECT * FROM Person";
            ResultSet rs = statement.executeQuery(SQL);

            while (rs.next()) {
                Person person = new Person();

                person.setId(rs.getInt("id"));
                person.setName(rs.getString("name"));
                person.setSurname(rs.getString("surname"));
                person.setAge(rs.getInt("age"));
                person.setEmail(rs.getString("email"));

                people.add(person);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return people;
    }

    @Override
    public Person show(int id) {

        Person person = null;

        try {
            String SQL = "SELECT * FROM Person WHERE id=?";
            PreparedStatement statement = connection.prepareStatement(SQL);

            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();

            rs.next();

            person = new Person();
            person.setId(rs.getInt("id"));
            person.setName(rs.getString("name"));
            person.setSurname(rs.getString("surname"));
            person.setAge(rs.getInt("age"));
            person.setEmail(rs.getString("email"));

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return person;
    }

    @Override
    public void save(Person person) {
        try {
            String SQL = "INSERT INTO Person VALUES(1,?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(SQL);

            statement.setString(1, person.getName());
            statement.setString(2, person.getSurname());
            statement.setInt(3, person.getAge());
            statement.setString(4, person.getEmail());

            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void update(int id, Person updatedPerson) {
        try {
            String SQL = "UPDATE Person SET " +
                            "name     = ?, " +
                            "surname  = ?," +
                            "age      = ?," +
                            "email    = ?" +
                            "WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(SQL);

            statement.setString(1, updatedPerson.getName());
            statement.setString(2, updatedPerson.getSurname());
            statement.setInt(3, updatedPerson.getAge());
            statement.setString(4, updatedPerson.getEmail());
            statement.setInt(5, id);

            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {

        try {
            String SQL = "DELETE FROM Person WHERE id=?";
            PreparedStatement statement = connection.prepareStatement(SQL);

            statement.setInt(1, id);

            statement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
