package ro.fortech.winewiki.apigateway.service;

import ro.fortech.winewiki.apigateway.dto.ApiGatewayNewPersonDto;
import ro.fortech.winewiki.apigateway.dto.ApiGatewayPersonDto;

import java.util.Set;


public interface PersonService {

    ApiGatewayNewPersonDto create(ApiGatewayNewPersonDto newPersonDto);

    void delete(Long id);

    Set<ApiGatewayPersonDto> findAll();

    ApiGatewayPersonDto update(ApiGatewayPersonDto personDto);

    ApiGatewayPersonDto findById(Long id);

    ApiGatewayPersonDto findByUsername(String username);

//    void resetPassword(String mail);
//
//    void changePassword(String token, String password);

}
