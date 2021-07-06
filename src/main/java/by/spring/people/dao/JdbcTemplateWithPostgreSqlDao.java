package by.spring.people.dao;

import by.spring.people.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JdbcTemplateWithPostgreSqlDao implements PersonDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcTemplateWithPostgreSqlDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Person> index() {
        return jdbcTemplate.query("SELECT * FROM Person", new BeanPropertyRowMapper<>(Person.class));
    }

    @Override
    public Person show(int id) {
        return jdbcTemplate.query("SELECT * FROM Person WHERE id=?", new Object[] {id}, new BeanPropertyRowMapper<>(Person.class))
                .stream()
                .findAny()
                .orElse(null);
    }

    @Override
    public void save(Person person) {
        jdbcTemplate.update("INSERT INTO Person VALUES (18,?,?,?,?)",
                person.getName(),
                person.getSurname(),
                person.getAge(),
                person.getEmail());
    }

    @Override
    public void update(int id, Person updatedPerson) {
        jdbcTemplate.update("UPDATE Person SET name=?, surname=?, age=?, email=? WHERE id=?",
                updatedPerson.getName(),
                updatedPerson.getSurname(),
                updatedPerson.getAge(),
                updatedPerson.getEmail());
    }

    @Override
    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Person WHERE id=?", id);
    }
}
