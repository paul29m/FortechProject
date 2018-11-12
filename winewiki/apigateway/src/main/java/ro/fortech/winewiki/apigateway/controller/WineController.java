package ro.fortech.winewiki.apigateway.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import ro.fortech.winewiki.apigateway.dto.ApiGatewayNewWineDto;
import ro.fortech.winewiki.apigateway.dto.ApiGatewayWineDto;
import ro.fortech.winewiki.apigateway.service.WineService;

@RequestMapping("/api/wine/")
@RestController
public class WineController {

    @Autowired
    private WineService wineService;

    @RequestMapping(value = "getall", method = RequestMethod.GET, params = {"page", "size"})
    public Page<ApiGatewayWineDto> getAll(@RequestParam("page") int page,
                                          @RequestParam("size") int size,
                                          @RequestParam(value = "type", required = false) String type,
                                          @RequestParam(value = "location", required = false) String location,
                                          @RequestParam(value = "cost_type", required = false) String costType,
                                          @RequestParam(value = "start_time", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) String startTime,
                                          @RequestParam(value = "end_time", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) String endTime,
                                          @RequestParam(value = "available_until", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") String availableUntil) {
        return wineService.findAllWinePage(page, size, type, location, costType, startTime, endTime, availableUntil);
    }

    @RequestMapping(value = "getbyid", method = RequestMethod.POST)
    public ApiGatewayWineDto getById(@RequestBody Long id) {
        return wineService.findById(id);
    }

    @RequestMapping(value = "getbyid/{id}", method = RequestMethod.GET)
    public ApiGatewayWineDto getByIdFromUrl(@PathVariable Long id) {
        return wineService.findById(id);
    }

    @RequestMapping(value = "create", method = RequestMethod.POST)
    public ApiGatewayNewWineDto createWine(@RequestBody ApiGatewayNewWineDto newWineDto) {
        return wineService.create(newWineDto);
    }

    @RequestMapping(value = "update", method = RequestMethod.PUT)
    public ApiGatewayWineDto updateWine(@RequestBody ApiGatewayWineDto wineDto) {
        return wineService.update(wineDto);
    }

    @RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE)
    public void deleteWine(@PathVariable Long id) {
        wineService.delete(id);
    }

    @RequestMapping(value = "getbytitle", method = RequestMethod.POST)
    public ApiGatewayWineDto getWineByTitle(@RequestBody String title) {
        return wineService.findByTitle(title);
    }

    @RequestMapping(value = "getbytitle/{title}", method = RequestMethod.GET)
    public ApiGatewayWineDto getWineByTitleFromUrl(@PathVariable String title) {
        return wineService.findByTitle(title);
    }


}
