package ro.fortech.winewiki.apigateway.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.fortech.winewiki.apigateway.dto.ApiGatewayPersonDto;
import ro.fortech.winewiki.apigateway.dto.ApiGatewayWineDto;
import ro.fortech.winewiki.apigateway.dto.ApiGatewayWineDtoList;
import ro.fortech.winewiki.profilemicro.dto.PersonDto;
import ro.fortech.winewiki.profilemicro.dto.WineDtoList;

import java.util.LinkedHashSet;
import java.util.stream.Collectors;

@Service
public class PersonMapper extends AbstractMapper<PersonDto, ApiGatewayPersonDto> {

    @Autowired
    private WineMapper wineMapper;

    @Override
    public PersonDto toInternal(ApiGatewayPersonDto dto) {
        if(!dto.getWines().getApiGatewayWineDtoList().isEmpty()) {
            PersonDto personDto = new PersonDto(dto.getId(), dto.getUsername(), dto.getPassword(), dto.getLocation(),
                    dto.getFirstname(),
                    dto.getLastname(),
                    dto.getDescription(),
                    dto.getPicture(),
                    new WineDtoList(dto.getWines().getApiGatewayWineDtoList().stream()
                            .map(wine -> wineMapper.toInternal(wine))
                            .collect(Collectors.toSet())));
            return personDto;
        } else {
            PersonDto personDto = new PersonDto(dto.getId(), dto.getUsername(), dto.getPassword(), dto.getLocation(),
                    dto.getFirstname(),
                    dto.getLastname(),
                    dto.getDescription(),
                    dto.getPicture(),
                    new WineDtoList(new LinkedHashSet<>()));
            return personDto;
        }
    }

    @Override
    public ApiGatewayPersonDto toExternal(PersonDto model) {
        ApiGatewayPersonDto personDto = new ApiGatewayPersonDto(model.getId(), model.getUsername(), model.getPassword(),
                model.getLocation(),
                model.getFirstname(),
                model.getLastname(),
                model.getDescription(),
                model.getPicture(),
                new ApiGatewayWineDtoList(model.getWines().getWineDtoList().stream()
                        .map(wine -> new ApiGatewayWineDto(wine.getId(), wine.getTitle(), wine.getDescription(),
                                wine.getLocation(), wine.getStartDate(), wine.getEndDate(),
                                wine.getStartTime(), wine.getEndTime(), wine.getCost(),
                                wine.getOwner(), wine.getWineType()))
                        .collect(Collectors.toSet())));
        return personDto;
    }
}