package ro.fortech.winewiki.profilemicro.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.fortech.winewiki.profilemicro.dto.NewPersonDto;
import ro.fortech.winewiki.profilemicro.dto.PersonDto;
import ro.fortech.winewiki.profilemicro.mapper.NewPersonMapper;
import ro.fortech.winewiki.profilemicro.mapper.PersonMapper;
import ro.fortech.winewiki.profilemicro.mapper.WineMapper;
import ro.fortech.winewiki.profilemicro.model.Person;
import ro.fortech.winewiki.profilemicro.model.Wine;
import ro.fortech.winewiki.profilemicro.repository.PersonRepository;
import ro.fortech.winewiki.profilemicro.repository.WineRepository;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Service
public class PersonServiceImpl implements PersonService {

    private static final String EMPTY_STRING = "";

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private WineRepository wineRepository;

    @Autowired
    private WineMapper wineMapper;

    @Autowired
    private PersonMapper personMapper;

    @Autowired
    private NewPersonMapper newPersonMapper;

    @Override
    public NewPersonDto create(NewPersonDto newPersonDto) {
        if (personRepository.findByUsername(newPersonDto.getUsername()) == null) {
            Person createdPerson = newPersonMapper.toInternal(newPersonDto);
            return newPersonMapper.toExternal(personRepository.save(createdPerson));
        }
        return null;
    }

    @Transactional
    @Override
    public void delete(Long id) {
        Person deletedPerson = personRepository.findOne(id);
        personRepository.delete(deletedPerson);
    }

    @Override
    public Set<PersonDto> findAll() {
        Set<PersonDto> persons = personMapper.toExternals(new LinkedHashSet<>((List<Person>) personRepository.findAll()));
        return persons;
    }

    @Override
    public PersonDto findById(Long id) {
        return personMapper.toExternal(personRepository.findOne(id));
    }

    @Override
    public PersonDto findByUsername(String username) {
        Person dto = personRepository.findByUsername(username);
        return personMapper.toExternal(dto);
    }

    @Override
    public Person findByUsernameNormal(String username) {
        return personRepository.findByUsername(username);
    }


    @Override
    public Person findByMail(String mail) {
        return personRepository.findByMail(mail);
    }

    @Override
    public void updateToken(Person person) {
        personRepository.save(person);
    }

    @Override
    @Transactional
    public PersonDto update(PersonDto personDto) {
        Person personToUpdate = personRepository.findOne(personDto.getId());
        String firstName = personDto.getFirstname();
        String lastName = personDto.getLastname();
        String description = personDto.getDescription();
        String location = personDto.getLocation();
        String phonenumber = personDto.getPhonenumber();
        byte[] pic = personDto.getPicture();
        if (firstName != EMPTY_STRING) {
            personToUpdate.setFirstname(personDto.getFirstname());
        }
        if (lastName != EMPTY_STRING) {
            personToUpdate.setLastname(personDto.getLastname());
        }
        if (description != EMPTY_STRING) {
            personToUpdate.setDescription(personDto.getDescription());
        }

        if (phonenumber != EMPTY_STRING) {
            personToUpdate.setPhonenumber(personDto.getPhonenumber());
        }
        if (location != EMPTY_STRING) {
            personToUpdate.setLocation(personDto.getLocation());
        }
        if (pic != null) {
            personToUpdate.setPicture(personDto.getPicture());
        }

        for(Wine wine : personToUpdate.getWineSet()) {
            wine.setOwner(personToUpdate);
            wineRepository.save(wine);
        }

        Person personUpdated = personRepository.save(personToUpdate);
        return personMapper.toExternal(personUpdated);
    }

}