package ro.fortech.winewiki.apigateway.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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

}
