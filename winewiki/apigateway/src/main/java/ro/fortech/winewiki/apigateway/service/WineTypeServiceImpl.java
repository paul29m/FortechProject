package ro.fortech.winewiki.apigateway.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ro.fortech.winewiki.apigateway.dto.ApiGatewayNewWineTypeDto;
import ro.fortech.winewiki.apigateway.dto.ApiGatewayWineTypeDto;
import ro.fortech.winewiki.apigateway.mapper.WineTypeMapper;
import ro.fortech.winewiki.profilemicro.dto.WineTypeDto;
import ro.fortech.winewiki.profilemicro.dto.WineTypeDtoList;

import java.util.*;

@Service
public class WineTypeServiceImpl implements WineTypeService {

    private final String wineTypeServiceUrl = "http://localhost:9998/profilemicro/winetype/";

    private RestTemplate restTemplate = new RestTemplate();

    @Autowired
    private WineTypeMapper wineTypeMapper;

    @Override
    public ApiGatewayNewWineTypeDto create(ApiGatewayNewWineTypeDto wineTypeDto) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Set<ApiGatewayWineTypeDto> findAll() {
        WineTypeDtoList personDtoList = restTemplate.getForEntity(this.wineTypeServiceUrl + "getall", WineTypeDtoList.class).getBody();
        List<WineTypeDto> wineTypeDtos = new ArrayList<>();
        wineTypeDtos.addAll(personDtoList.getWineTypeDtos());
        Collections.sort(wineTypeDtos, new Comparator<WineTypeDto>() {
            @Override public int compare(WineTypeDto p1, WineTypeDto p2) {
                return p1.getName().compareToIgnoreCase(p2.getName()); // Ascending
            }

        });
        Set<WineTypeDto> typeDtos = new LinkedHashSet<>();
        for(WineTypeDto wineTypeDto: wineTypeDtos){
            typeDtos.add(wineTypeDto);
        }
        return wineTypeMapper.toExternals(typeDtos);
    }

    @Override
    public ApiGatewayWineTypeDto update(ApiGatewayWineTypeDto wineTypeDto) {
        return null;
    }

    @Override
    public ApiGatewayWineTypeDto findById(Long id) {
        return null;
    }

    @Override
    public ApiGatewayWineTypeDto findByName(String name) {
        return null;
    }
}
