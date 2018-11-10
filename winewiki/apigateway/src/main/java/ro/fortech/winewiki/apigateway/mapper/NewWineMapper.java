package ro.fortech.winewiki.apigateway.mapper;

import org.springframework.stereotype.Service;
import ro.fortech.winewiki.apigateway.dto.ApiGatewayNewWineDto;
import ro.fortech.winewiki.profilemicro.dto.NewWineDto;

@Service
public class NewWineMapper extends AbstractMapper<NewWineDto, ApiGatewayNewWineDto> {

    @Override
    public NewWineDto toInternal(ApiGatewayNewWineDto dto) {
        return NewWineDto.builder()
                .title(dto.getTitle())
                .description(dto.getDescription())
                .location(dto.getLocation())
                .startDate(dto.getStartDate())
                .endDate(dto.getEndDate())
                .cost(dto.getCost())
                .owner(dto.getOwner())
                .wineType(dto.getWineType())
                .build();
    }

    @Override
    public ApiGatewayNewWineDto toExternal(NewWineDto model) {
        return ApiGatewayNewWineDto.builder()
                .title(model.getTitle())
                .description(model.getDescription())
                .location(model.getLocation())
                .startDate(model.getStartDate())
                .endDate(model.getEndDate())
                .cost(model.getCost())
                .owner(model.getOwner())
                .wineType(model.getWineType())
                .build();
    }
}
