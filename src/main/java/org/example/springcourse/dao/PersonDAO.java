package org.example.springcourse.dao;

import org.example.springcourse.models.Person;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static org.example.springcourse.dao.DataForConnections.*;

@Component
public class PersonDAO {
    private static int PEOPLE_COUNT;

    //Local PostgreSQL database
//    private static final String URL = "jdbc:postgresql://localhost:5432/first_db";
//    private static final String USERNAME = "postgres";
//    private static final String PASSWORD = "postgres";

    //Heroku PostgreSQL database
//    private static final String URL = "jdbc:postgresql://HOST:5432/DATABASE";
//    private static final String USERNAME = "USER";
//    private static final String PASSWORD = "PASSWORD";

    private static Connection connection;
    static{
        try{
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }

        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Возвращаем список людей
    public List<Person> index(){
        List<Person> people = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            String SQL = "SELECT * FROM person";
            ResultSet resultSet = statement.executeQuery(SQL);

            while(resultSet.next()){
                Person person = new Person();

                person.setId(resultSet.getInt("id"));
                person.setName(resultSet.getString("name"));
                person.setAge(resultSet.getInt("age"));
                person.setEmail(resultSet.getString("email"));

                people.add(person);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return people;
    }

    //Находим человека с id и возвращаем его или NULL, если такого нет
    public Person show(int id){
//        return people.stream().filter(person -> person.getId() == id).findAny().orElse(null);
        return null;
    }

    //Добавляем человека в БД
    public void save(Person person){
//        person.setId(++PEOPLE_COUNT); //id добавляем автоматически
//        people.add(person);
        try {
            Statement statement = connection.createStatement();
            String SQL = "INSERT INTO Person VALUES(" + 1 + ",'" + person.getName() + "'," +
                    person.getAge() + ",'" + person.getEmail() + "')";
            //INSERT INTO Person VALUES(1, 'Bob', 22, 'bob@mail.ru')
            statement.executeUpdate(SQL);

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public void update(int id, Person updatePerson){
//        Person personToBeUpdated = show(id); //ищем человека по id, которого надо обновить
//        personToBeUpdated.setName(updatePerson.getName());
//        personToBeUpdated.setAge(updatePerson.getAge());
//        personToBeUpdated.setEmail(updatePerson.getEmail());
    }

    public void delete(int id){
//        people.removeIf(p -> p.getId() == id);
    }
}
