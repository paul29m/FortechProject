package ro.fortech.winewiki.apigateway.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ro.fortech.winewiki.apigateway.dto.ApiGatewayNewWineTypeDto;
import ro.fortech.winewiki.apigateway.dto.ApiGatewayWineTypeDto;
import ro.fortech.winewiki.apigateway.dto.ApiGatewayWineTypeDtoList;
import ro.fortech.winewiki.apigateway.service.WineTypeService;

@RequestMapping("/api/winetype/")
@RestController
public class WineTypeController {

    @Autowired
    private WineTypeService wineTypeService;


    @RequestMapping (value = "getall", method = RequestMethod.GET)
    public ApiGatewayWineTypeDtoList getAll() {
        ApiGatewayWineTypeDtoList wineTypeDtoList = new ApiGatewayWineTypeDtoList(wineTypeService.findAll());
        return wineTypeDtoList;
    }
    @RequestMapping (value = "getbyid", method = RequestMethod.POST)
    public ApiGatewayWineTypeDto getById(@RequestBody Long id) {
        return wineTypeService.findById(id);
    }

    @RequestMapping (value = "getbyid/{id}", method = RequestMethod.GET)
    public ApiGatewayWineTypeDto getByIdFromUrl(@PathVariable Long id) {
        return wineTypeService.findById(id);
    }

    @RequestMapping (value = "create", method = RequestMethod.POST)
    public ApiGatewayNewWineTypeDto createPerson(@RequestBody ApiGatewayNewWineTypeDto newWineTypeDto) {
        return wineTypeService.create(newWineTypeDto);
    }

    @RequestMapping (value = "update", method = RequestMethod.PUT)
    public ApiGatewayWineTypeDto updatePerson(@RequestBody ApiGatewayWineTypeDto wineTypeDto) {
        return wineTypeService.update(wineTypeDto);
    }

    @RequestMapping (value = "delete/{id}", method = RequestMethod.DELETE)
    public void deletePerson(@PathVariable Long id) {
        wineTypeService.delete(id);
    }

    @RequestMapping (value = "getbyname", method = RequestMethod.POST)
    public ApiGatewayWineTypeDto getWineTypeByName(@RequestBody String name) {
        return wineTypeService.findByName(name);
    }

    @RequestMapping (value = "getbyname/{name}", method = RequestMethod.GET)
    public ApiGatewayWineTypeDto getWineTypeByNameFromUrl(@PathVariable String name) {
        return wineTypeService.findByName(name);
    }
}
