package ro.fortech.winewiki.apigateway.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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
}
