package ro.fortech.winewiki.apigateway.mapper;

import org.springframework.stereotype.Service;
import ro.fortech.winewiki.apigateway.dto.ApiGatewayNewWineTypeDto;
import ro.fortech.winewiki.profilemicro.dto.NewWineTypeDto;

@Service
public class NewWineTypeMapper extends AbstractMapper<NewWineTypeDto, ApiGatewayNewWineTypeDto> {

    @Override
    public NewWineTypeDto toInternal(ApiGatewayNewWineTypeDto dto) {
        return NewWineTypeDto.builder()
                .name(dto.getName())
                .build();
    }

    @Override
    public ApiGatewayNewWineTypeDto toExternal(NewWineTypeDto model) {
        return ApiGatewayNewWineTypeDto.builder()
                .name(model.getName())
                .build();
    }
}
