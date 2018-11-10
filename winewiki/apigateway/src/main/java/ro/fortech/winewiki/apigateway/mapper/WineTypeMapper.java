package ro.fortech.winewiki.apigateway.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.fortech.winewiki.apigateway.dto.ApiGatewayWineDtoList;
import ro.fortech.winewiki.apigateway.dto.ApiGatewayWineTypeDto;
import ro.fortech.winewiki.profilemicro.dto.WineDtoList;
import ro.fortech.winewiki.profilemicro.dto.WineTypeDto;

import java.util.LinkedHashSet;
import java.util.stream.Collectors;

@Service
public class WineTypeMapper extends AbstractMapper<WineTypeDto, ApiGatewayWineTypeDto> {

    @Autowired
    private WineMapper wineMapper;

    @Override
    public WineTypeDto toInternal(ApiGatewayWineTypeDto dto) {
        if(!dto.getWines().getApiGatewayWineDtoList().isEmpty()) {
            WineTypeDto wineTypeDto = new WineTypeDto(dto.getId(), dto.getName(),
                    new WineDtoList(dto.getWines().getApiGatewayWineDtoList().stream()
                            .map(wineDto -> wineMapper.toInternal(wineDto))
                            .collect(Collectors.toSet())));
            return wineTypeDto;
        }else{
            WineTypeDto wineTypeDto = new WineTypeDto(dto.getId(), dto.getName(),
                    new WineDtoList(new LinkedHashSet<>()));
            return wineTypeDto;
        }
    }

    @Override
    public ApiGatewayWineTypeDto toExternal(WineTypeDto model) {
        if (!model.getWines().getWineDtoList().isEmpty()) {
            ApiGatewayWineTypeDto gatewayWineTypeDto = new ApiGatewayWineTypeDto(model.getId(), model.getName(),
                    new ApiGatewayWineDtoList(model.getWines().getWineDtoList().stream()
                            .map(wineDTo -> wineMapper.toExternal(wineDTo))
                            .collect(Collectors.toSet())));
            return gatewayWineTypeDto;
        } else {
            ApiGatewayWineTypeDto apiGatewayWineTypeDto = new ApiGatewayWineTypeDto(model.getId(), model.getName(),
                    new ApiGatewayWineDtoList(new LinkedHashSet<>()));
            return apiGatewayWineTypeDto;
        }
    }
}