package by.spring.people.dao;

import by.spring.people.models.Person;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAOtest implements PersonDAO {
    private static int PEOPLE_COUNT;
    private final List<Person> people;

    {
        people = new ArrayList<>(List.of(
                new Person(++PEOPLE_COUNT, "Vlas", "Kastsiukovich", 19, "ulas.kastsiukovich@gmial.com"),
                new Person(++PEOPLE_COUNT, "Artem", "Marchenko", 19, "Artem.Marchenko@gmial.com"),
                new Person(++PEOPLE_COUNT, "Egor", "Rogulev", 19,"Egor.Rogulev@gmial.com"),
                new Person(++PEOPLE_COUNT, "Mark", "Durankov", 18, "Mark.Durankov@gmial.com")
                ));
    }

    @Override
    public List<Person> index() {
        return people;
    }

    @Override
    public Person show(int id) {
        return people
                .stream()
                .filter((e) -> e.getId() == id)
                .findAny()
                .orElse(null);
    }

    @Override
    public void save(Person person) {
        person.setId(++PEOPLE_COUNT);
        people.add(person);
    }

    @Override
    public void update(int id, Person updatedPerson) {
        Person personToBeUpdated = show(id);
        personToBeUpdated.setName(updatedPerson.getName());
        personToBeUpdated.setSurname(updatedPerson.getSurname());
        personToBeUpdated.setAge(updatedPerson.getAge());
        personToBeUpdated.setEmail(updatedPerson.getEmail());

    }

    @Override
    public void delete(int id) {
        people.remove(show(id));
    }

}
