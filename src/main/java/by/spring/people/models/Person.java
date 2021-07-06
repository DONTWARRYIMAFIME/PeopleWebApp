package by.spring.people.models;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class Person {
    private int id;

    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 15, message = "Name should be in 2-15")
    private String name;

    @NotEmpty(message = "Surname should not be empty")
    @Size(min = 2, max = 40, message = "Surname should be in 2-40")
    private String surname;

    @Min(value = 0, message = "Age should be greater then 0")
    private int age;

    @NotEmpty(message = "Email should not be empty")
    @Email(message = "Not valid email")
    private String email;

    public Person() {};

    public Person(int id, String name, String surname, int age, String email) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
