package ro.fortech.winewiki.apigateway.mapper;

import org.springframework.stereotype.Service;
import ro.fortech.winewiki.apigateway.dto.ApiGatewayNewPersonDto;
import ro.fortech.winewiki.profilemicro.dto.NewPersonDto;

@Service
public class NewPersonMapper extends AbstractMapper<NewPersonDto, ApiGatewayNewPersonDto> {

    @Override
    public NewPersonDto toInternal(ApiGatewayNewPersonDto dto) {
        NewPersonDto person = new NewPersonDto(dto.getUsername(), dto.getPassword(), dto.getLocation(), dto.getEmail());
        return person;
    }

    @Override
    public ApiGatewayNewPersonDto toExternal(NewPersonDto model) {
        ApiGatewayNewPersonDto newPersonDto = new ApiGatewayNewPersonDto(model.getUsername(), model.getPassword(), model.getLocation(), model.getEmail());
        return newPersonDto;
    }
}