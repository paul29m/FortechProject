package ro.fortech.winewiki.profilemicro.service;

import ro.fortech.winewiki.profilemicro.dto.NewPersonDto;
import ro.fortech.winewiki.profilemicro.dto.PersonDto;
import ro.fortech.winewiki.profilemicro.model.Person;
import java.util.Set;


public interface PersonService {

    NewPersonDto create(NewPersonDto newPersonDto);

    void delete(Long id);

    Set<PersonDto> findAll();

    PersonDto update(PersonDto personDto);

    PersonDto findById(Long id);

    PersonDto findByUsername(String username);

    Person findByUsernameNormal(String username);


    Person findByMail(String mail);

    void updateToken(Person person);


}
