package cz.czechitas.java2webapps.lekce5.people;

import cz.czechitas.java2webapps.lekce5.entity.Gender;
import cz.czechitas.java2webapps.lekce5.entity.Person;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class FamousPeopleService {
  private final List<Person> people = new ArrayList<>();

  public FamousPeopleService() {
    people.add(new Person("Angela", "Merkelová", LocalDate.of(1954, 7, 17), Gender.Female));
    people.add(new Person("Bill", "Gates", LocalDate.of(1955, 10, 28), Gender.Male));
    people.add(new Person("Greta", "Thunbergová", LocalDate.of(2003, 1, 3), Gender.Female));
  }

  public List<Person> getAll() { // zobrazí všechny osoby
    return people;
  }

  public List<Person> getByName(String query) { // vyhledávací pole - zda je zadaný text ve jméně či příjmení (query)
    return people.stream()
            .filter(person -> person.getGivenName().contains(query) || person.getLastName().contains(query))
            .toList();
  }

  public Person getById(int id) { // zobrazí osoby dle id
    return people.get(id);
  } // zobrazí konkrétní osobu dle ID

  public void append(Person person) { // přidá osobu do seznamu
    people.add(person);
  } // přidá osobu do seznamu

  public void edit(int id, Person person) { // editace osoby
    people.set(id, person);
  } // upraví konkrétní osobu v seznamu

  public void deleteById(int id) { // vymaže osobu dle id
    people.remove(id);
  } // vymaže konkrétní osobu ze seznamu
}