package ro.fortech.winewiki.profilemicro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ro.fortech.winewiki.profilemicro.dto.NewWineTypeDto;
import ro.fortech.winewiki.profilemicro.dto.WineTypeDto;
import ro.fortech.winewiki.profilemicro.dto.WineTypeDtoList;
import ro.fortech.winewiki.profilemicro.service.WineTypeService;

@RequestMapping("/profilemicro/winetype/")
@RestController
public class WineTypeController {

    @Autowired
    private WineTypeService wineTypeService;

    @RequestMapping (value = "getall", method = RequestMethod.GET)
    public WineTypeDtoList getAll() {
        WineTypeDtoList wineTypeDtoList = new WineTypeDtoList(wineTypeService.findAll());
        return wineTypeDtoList;
    }

    @RequestMapping (value = "getbyid", method = RequestMethod.POST)
    public WineTypeDto getById(@RequestBody Long id) {
        return wineTypeService.findById(id);
    }

    @RequestMapping (value = "getbyid/{id}", method = RequestMethod.GET)
    public WineTypeDto getByIdFromUrl(@PathVariable Long id) {
        return wineTypeService.findById(id);
    }

    @RequestMapping (value = "create", method = RequestMethod.POST)
    public NewWineTypeDto createPerson(@RequestBody NewWineTypeDto newWineTypeDto) {
        return wineTypeService.create(newWineTypeDto);
    }

    @RequestMapping (value = "update", method = RequestMethod.PUT)
    public WineTypeDto updatePerson(@RequestBody WineTypeDto wineTypeDto) {
        return wineTypeService.update(wineTypeDto);
    }

    @RequestMapping (value = "delete/{id}", method = RequestMethod.DELETE)
    public void deletePerson(@PathVariable Long id) {
        wineTypeService.delete(id);
    }

    @RequestMapping (value = "getbyname", method = RequestMethod.POST)
    public WineTypeDto getWineTypeByName(@RequestBody String name) {
        return wineTypeService.findByName(name);
    }

    @RequestMapping (value = "getbyname/{name}", method = RequestMethod.GET)
    public WineTypeDto getWineTypeByNameFromUrl(@PathVariable String name) {
        return wineTypeService.findByName(name);
    }
}
