package com.example.peopleweb.web.controller;

import com.example.peopleweb.biz.model.Person;
import com.example.peopleweb.data.PersonRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/people")
@Log4j2
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
    public String savePerson(@Valid Person person, Errors errors, @RequestParam MultipartFile photoFilename) {
        log.info("File name " + photoFilename.getOriginalFilename());
        log.info("File size " + photoFilename.getSize());
        log.info("Error" + errors);
        if(!errors.hasErrors()) {
        personRepository.save(person);
        return "redirect:people";
        } else {
            return "people";
        }
    }
    @PostMapping(params = "delete=true")
    public String deletePeople(@RequestParam Optional<List<Long>> selections) {
        if(selections.isPresent()) {
            personRepository.deleteAllById(selections.get());
        }
        return "redirect:people";
    }

    @PostMapping(params = "edit=true")
    public String editPerson(@RequestParam Optional<List<Long>> selections, Model model) {
        if(selections.isPresent()){
            Optional<Person> person = personRepository.findById(selections.get().get(0));
            model.addAttribute("person", person);
        }
        return  "people";
    }
}
