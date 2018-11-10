package ro.fortech.winewiki.apigateway.service;

import ro.fortech.winewiki.apigateway.dto.ApiGatewayNewWineTypeDto;
import ro.fortech.winewiki.apigateway.dto.ApiGatewayWineTypeDto;

import java.util.Set;

public interface WineTypeService {

    ApiGatewayNewWineTypeDto create(ApiGatewayNewWineTypeDto wineTypeDto);

    void delete(Long id);

    Set<ApiGatewayWineTypeDto> findAll();

    ApiGatewayWineTypeDto update(ApiGatewayWineTypeDto wineTypeDto);

    ApiGatewayWineTypeDto findById(Long id);

    ApiGatewayWineTypeDto findByName(String name);
}
