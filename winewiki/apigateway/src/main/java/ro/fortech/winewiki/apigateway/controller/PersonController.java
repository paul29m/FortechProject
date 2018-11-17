package ro.fortech.winewiki.apigateway.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ro.fortech.winewiki.apigateway.dto.ApiGatewayNewPersonDto;
import ro.fortech.winewiki.apigateway.dto.ApiGatewayPersonDto;
import ro.fortech.winewiki.apigateway.dto.ApiGatewayPersonDtoList;
import ro.fortech.winewiki.apigateway.service.PersonService;

@RequestMapping("/api/person/")
@RestController
public class PersonController {

    @Autowired
    private PersonService personService;

    @RequestMapping(value = "getall", method = RequestMethod.GET)
    public ApiGatewayPersonDtoList getAllUsers() {
        return new ApiGatewayPersonDtoList(personService.findAll());
    }

    @RequestMapping(value = "getbyid", method = RequestMethod.POST)
    public ApiGatewayPersonDto getById(@RequestBody Long id) {
        return personService.findById(id);
    }

    @RequestMapping(value = "getbyid/{id}", method = RequestMethod.GET)
    public ApiGatewayPersonDto getByIdFromUrl(@PathVariable Long id) {
        return personService.findById(id);
    }

    @RequestMapping(value = "create", method = RequestMethod.POST)
    public ApiGatewayNewPersonDto createPerson(@RequestBody ApiGatewayNewPersonDto newPersonDto) {
        return personService.create(newPersonDto);
    }

    @RequestMapping(value = "update/image", method = RequestMethod.PUT, consumes = "multipart/form-data")
    @ResponseBody
    public ApiGatewayPersonDto updatePerson(@RequestPart("file") MultipartFile file,
                                            @RequestPart("person") ApiGatewayPersonDto personDto) {
        byte[] content = null;
        if (!file.isEmpty()) {
            try {
                content = file.getBytes();
            } catch (Exception e) {
                return null;
            }
        }
        ApiGatewayPersonDto updatedUser = new ApiGatewayPersonDto(personDto.getId(), personDto.getUsername(),
                personDto.getPassword(), personDto.getLocation(),
                personDto.getFirstname(),
                personDto.getLastname(),
                personDto.getDescription(),
                personDto.getPhonenumber(),
                content,
                personDto.getWines());

        System.out.println(updatedUser.toString());

        return personService.update(updatedUser);
    }


    @RequestMapping(value = "update/noimage", method = RequestMethod.PUT, consumes = "multipart/form-data")
    @ResponseBody
    public ApiGatewayPersonDto updatePerson(@RequestPart("person") ApiGatewayPersonDto personDto) {

        ApiGatewayPersonDto updatedUser = new ApiGatewayPersonDto(personDto.getId(), personDto.getUsername(),
                personDto.getPassword(), personDto.getLocation(),
                personDto.getFirstname(),
                personDto.getLastname(),
                personDto.getDescription(),
                personDto.getPhonenumber(),
                null,
                personDto.getWines());

        System.out.println(updatedUser.toString());

        return personService.update(updatedUser);
    }

    @RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE)
    public void deletePerson(@PathVariable Long id) {
        personService.delete(id);
    }

    @RequestMapping(value = "getbyusername", method = RequestMethod.POST)
    public ApiGatewayPersonDto getPersonByUsername(@RequestBody String username) {
        return personService.findByUsername(username);
    }

    @RequestMapping(value = "getbyusername/{username}", method = RequestMethod.GET)
    public ApiGatewayPersonDto getPersonByUsernameFromUrl(@PathVariable String username) {
        return personService.findByUsername(username);
    }

//    @RequestMapping(value = "reset/{mail}/", method = RequestMethod.POST)
//    public void resetPassword(@PathVariable String mail) {
//        personService.resetPassword(mail);
//    }
//
//
//    @RequestMapping(value = "changepassword/{token}", method = RequestMethod.POST)
//    public void changePassword(@PathVariable String token, @RequestParam String password) {
//        personService.changePassword(token, password);
//    }
}
