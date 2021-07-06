package by.spring.people.dao;

import by.spring.people.models.Person;

import java.util.List;

public interface PersonDAO {
    List<Person> index();

    Person show(int id);

    void save(Person person);

    void update(int id, Person updatedPerson);

    void delete(int id);
}
