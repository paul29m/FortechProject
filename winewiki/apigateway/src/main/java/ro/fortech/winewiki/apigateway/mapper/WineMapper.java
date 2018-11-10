package ro.fortech.winewiki.apigateway.mapper;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import ro.fortech.winewiki.apigateway.dto.ApiGatewayWineDto;
import ro.fortech.winewiki.profilemicro.dto.RestResponsePage;
import ro.fortech.winewiki.profilemicro.dto.WineDto;

@Service
public class WineMapper extends AbstractMapper<WineDto, ApiGatewayWineDto> {

    @Override
    public WineDto toInternal(ApiGatewayWineDto dto) {
        return new WineDto(dto.getId(), dto.getTitle(), dto.getDescription(),
                dto.getLocation(), dto.getStartDate(), dto.getEndDate(),
                dto.getStartTime(), dto.getEndTime(), dto.getCost(),
                dto.getOwner(), dto.getWineType());
    }

    @Override
    public ApiGatewayWineDto toExternal(WineDto model) {
        return new ApiGatewayWineDto(model.getId(), model.getTitle(), model.getDescription(),
                model.getLocation(), model.getStartDate(), model.getEndDate(),
                model.getStartTime(), model.getEndTime(), model.getCost(),
                model.getOwner(), model.getWineType() );
    }

    public Page<ApiGatewayWineDto> toExternalPage(RestResponsePage<WineDto> objectEntityPage) {
        return objectEntityPage.map(model -> new ApiGatewayWineDto(model.getId(), model.getTitle(), model.getDescription(),
                model.getLocation(), model.getStartDate(), model.getEndDate(),
                model.getStartTime(), model.getEndTime(), model.getCost(),
                model.getOwner(), model.getWineType()));
    }
}
