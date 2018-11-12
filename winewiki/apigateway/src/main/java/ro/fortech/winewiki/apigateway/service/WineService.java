package ro.fortech.winewiki.apigateway.service;

import org.springframework.data.domain.Page;
import ro.fortech.winewiki.apigateway.dto.ApiGatewayNewWineDto;
import ro.fortech.winewiki.apigateway.dto.ApiGatewayWineDto;


import java.util.Set;

public interface WineService {

    ApiGatewayNewWineDto create(ApiGatewayNewWineDto apiGatewayNewWineDto);

    void delete(Long id);

    Set<ApiGatewayWineDto> findAll();

    ApiGatewayWineDto update(ApiGatewayWineDto apiGatewayWineDto);

    ApiGatewayWineDto findById(Long id);

    ApiGatewayWineDto findByTitle(String name);

    Page<ApiGatewayWineDto> findAllWinePage(int page, int size, String type, String location, String costType, String startTime, String endTime, String availableUntil);

}
