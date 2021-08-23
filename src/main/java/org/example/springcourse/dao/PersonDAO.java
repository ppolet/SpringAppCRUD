package org.example.springcourse.dao;

import org.example.springcourse.models.Person;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {
    private static int PEOPLE_COUNT;
    private List<Person> people;

    {
        people = new ArrayList<>();

        people.add(new Person(++PEOPLE_COUNT, "Tom", 24, "tom@mail.ru"));
        people.add(new Person(++PEOPLE_COUNT, "Bob", 35, "bob@mail.ru"));
        people.add(new Person(++PEOPLE_COUNT, "Mike", 55, "mike@mail.ru"));
        people.add(new Person(++PEOPLE_COUNT, "Katy", 25, "katy@gmail.com"));
    }

    //Возвращаем список людей
    public List<Person> index(){
        return people;
    }

    //Находим человека с id и возвращаем его или NULL, если такого нет
    public Person show(int id){
        return people.stream().filter(person -> person.getId() == id).findAny().orElse(null);
    }

    //Добавляем человека в БД
    public void save(Person person){
        person.setId(++PEOPLE_COUNT); //id добавляем автоматически
        people.add(person);
    }

    public void update(int id, Person updatePerson){
        Person personToBeUpdated = show(id); //ищем человека по id, которого надо обновить
        personToBeUpdated.setName(updatePerson.getName());
        personToBeUpdated.setAge(updatePerson.getAge());
        personToBeUpdated.setEmail(updatePerson.getEmail());
    }

    public void delete(int id){
        people.removeIf(p -> p.getId() == id);
    }
}
