package cz.czechitas.java2webapps.lekce5.people;

import cz.czechitas.java2webapps.lekce5.entity.Gender;
import cz.czechitas.java2webapps.lekce5.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.util.List;

/**
 * @author Filip Jirsák
 */
@Controller
public class FamousPeopleController {
  private final FamousPeopleService service;

  public FamousPeopleController(FamousPeopleService service) { // propojení service a controller
    this.service = service;
  }

  @GetMapping("/")
  public ModelAndView list() {
    ModelAndView result = new ModelAndView("index");
    result.addObject("people", service.getAll()); // vrací všechny osoby
    result.addObject("gender", Gender.values());
    return result;
  }

  @GetMapping("/{id}")
  public ModelAndView detail(@PathVariable int id) {
    ModelAndView result = new ModelAndView("detail");
    result.addObject("person", service.getById(id)); // vrací osoby dle ID
    result.addObject("gender", List.of(Gender.values()));
    return result;
  }

  @PostMapping("/{id}") // odeslání editovaných dat na server
  public String edit(@PathVariable int id, Person person) {
    service.edit(id, person);
    return "redirect:/";
  }

  @GetMapping("/search") // search v osobách
  public ModelAndView search(@RequestParam String query) {
    ModelAndView result = new ModelAndView("index");
    result.addObject("people", service.getByName(query));
    result.addObject("gender", Gender.values());
    result.addObject("query", query);
    return result;
  }

  @PostMapping ("/") // přidání osoby
  public String append (Person person) {
    service.append(person);
    return "redirect:/";
  }

  @PostMapping ("/delete") // vymazání osoby
  public String delete (int id) {
    service.deleteById(id);
    return "redirect:/";
  }
}