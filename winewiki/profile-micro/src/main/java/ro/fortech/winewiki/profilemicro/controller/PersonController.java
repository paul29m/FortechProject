package ro.fortech.winewiki.profilemicro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ro.fortech.winewiki.profilemicro.dto.NewPersonDto;
import ro.fortech.winewiki.profilemicro.dto.PersonDto;
import ro.fortech.winewiki.profilemicro.dto.PersonDtoList;
import ro.fortech.winewiki.profilemicro.service.PersonService;
import ro.fortech.winewiki.profilemicro.service.WineService;

@RequestMapping("/profilemicro/person/")
@RestController
public class PersonController {

    @Autowired
    private PersonService personService;

    @Autowired
    private WineService wineService;




    @RequestMapping (value = "getall", method = RequestMethod.GET)
    public PersonDtoList getAll() {
        PersonDtoList personDtoList = new PersonDtoList(personService.findAll());
        return personDtoList;
    }

    @RequestMapping (value = "getbyid", method = RequestMethod.POST)
    public PersonDto getById(@RequestBody Long id) {
        return personService.findById(id);
    }

    @RequestMapping (value = "getbyid/{id}", method = RequestMethod.GET)
    public PersonDto getByIdFromUrl(@PathVariable Long id) {
        return personService.findById(id);
    }

    @RequestMapping (value = "create", method = RequestMethod.POST)
    public NewPersonDto createPerson(@RequestBody NewPersonDto newPersonDto) {
        return personService.create(newPersonDto);
    }

    @RequestMapping (value = "update", method = RequestMethod.PUT)
    public PersonDto updatePerson(@RequestBody PersonDto personDto) {
        return personService.update(personDto);
    }

    @RequestMapping (value = "delete/{id}", method = RequestMethod.DELETE)
    public void deletePerson(@PathVariable Long id) {
        personService.delete(id);
    }

    @RequestMapping (value = "getbyusername", method = RequestMethod.POST)
    public PersonDto getPersonByUsername(@RequestBody String username) {
        return personService.findByUsername(username);
    }

    @RequestMapping (value = "getbyusername/{username}", method = RequestMethod.GET)
    public PersonDto getPersonByUsernameFromUrl(@PathVariable String username) {
        return personService.findByUsername(username);
    }

}
