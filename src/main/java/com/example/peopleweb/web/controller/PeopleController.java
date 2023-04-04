package com.example.peopleweb.web.controller;

import com.example.peopleweb.biz.model.Person;
import com.example.peopleweb.data.PersonRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/people")
public class PeopleController {
    private PersonRepository personRepository;

    public PeopleController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @ModelAttribute("people")
    public Iterable<Person> getPeople() {
        return personRepository.findAll();
    }


    @GetMapping
    public String showPoeplePage() {
        return "people";
    }

    @ModelAttribute
    public Person getPerson() {
        return new Person();
    }
    @PostMapping
    public String savePerson(@Valid Person person, Errors errors) {
        if(!errors.hasErrors()) {
        personRepository.save(person);
        return "redirect:people";
        } else {
            return "people";
        }
    }
}
