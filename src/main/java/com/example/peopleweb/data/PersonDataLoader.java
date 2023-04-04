package com.example.peopleweb.data;

import com.example.peopleweb.biz.model.Person;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

//@Component
public class PersonDataLoader implements ApplicationRunner {
    private PersonRepository personRepository;

    public PersonDataLoader(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (personRepository.count() == 0) {
            List<Person> people = List.of(
                    new Person(null, "Austin","Perrine", LocalDate.of(1995, 8, 22), "akperrine@gmail",
                            new BigDecimal("200000")),
                    new Person(null, "Michelle","Perrine", LocalDate.of(1995, 8, 1), "akperrine@gmail",
                            new BigDecimal("35000")),
                    new Person(null, "Jack","Perrine", LocalDate.of(2020, 5, 1), "akperrine@gmail",
                            new BigDecimal("10000"))
            );
            personRepository.saveAll(people);
        }
    }
}
