package ro.fortech.winewiki.apigateway.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import ro.fortech.winewiki.apigateway.dto.ApiGatewayNewWineDto;
import ro.fortech.winewiki.apigateway.dto.ApiGatewayWineDto;
import ro.fortech.winewiki.apigateway.mapper.NewWineMapper;
import ro.fortech.winewiki.apigateway.mapper.PersonMapper;
import ro.fortech.winewiki.apigateway.mapper.WineMapper;
import ro.fortech.winewiki.profilemicro.dto.NewWineDto;
import ro.fortech.winewiki.profilemicro.dto.RestResponsePage;
import ro.fortech.winewiki.profilemicro.dto.WineDto;
import ro.fortech.winewiki.profilemicro.dto.WineDtoList;

import java.util.Set;

@Service
public class WineServiceImpl implements WineService {

    private final String wineServiceUrl = "http://localhost:9998/profilemicro/wine/";

    private RestTemplate restTemplate = new RestTemplate();

    @Autowired
    private WineMapper wineMapper;

    @Autowired
    private NewWineMapper newWineMapper;

    @Autowired
    private PersonMapper personMapper;

    @Override
    public ApiGatewayNewWineDto create(ApiGatewayNewWineDto newWineDto) {
        NewWineDto dto = restTemplate
                .postForEntity(wineServiceUrl + "create", newWineMapper.toInternal(newWineDto), NewWineDto.class).getBody();
        return newWineMapper.toExternal(dto);
    }

    @Override
    public void delete(Long id) {
        restTemplate.delete(this.wineServiceUrl + "delete/" + id);
    }

    @Override
    public Set<ApiGatewayWineDto> findAll() {
        WineDtoList wineDtoList = restTemplate.getForEntity(this.wineServiceUrl + "getall", WineDtoList.class).getBody();
        return wineMapper.toExternals(wineDtoList.getWineDtoList());
    }

    @Override
    public Page<ApiGatewayWineDto> findAllWinePage(int page, int size, String type, String location, String costType, String startTime, String endTime, String availableUntil) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(this.wineServiceUrl + "pages/getall")
                .queryParam("page", page)
                .queryParam("size", size)
                .queryParam("type", type)
                .queryParam("location", location)
                .queryParam("start_time", startTime)
                .queryParam("end_time", endTime)
                .queryParam("available_until", availableUntil);
        ParameterizedTypeReference<RestResponsePage<WineDto>> responseType = new ParameterizedTypeReference<RestResponsePage<WineDto>>() {
        };

        ResponseEntity<RestResponsePage<WineDto>> result = restTemplate.exchange(builder.build().encode().toUri(), HttpMethod.GET, null/*httpEntity*/, responseType);
        return wineMapper.toExternalPage(result.getBody());
    }

    @Override
    public ApiGatewayWineDto update(ApiGatewayWineDto wineDto) {
        HttpEntity<ApiGatewayWineDto> updatedInstance = new HttpEntity<>(wineDto);
        return wineMapper.toExternal(
                restTemplate.exchange(this.wineServiceUrl + "update", HttpMethod.PUT, updatedInstance, WineDto.class).getBody());
    }

    @Override
    public ApiGatewayWineDto findById(Long id) {
        WineDto dto = restTemplate.postForEntity(this.wineServiceUrl + "getbyid", id, WineDto.class).getBody();
        return wineMapper.toExternal(dto);
    }

    @Override
    public ApiGatewayWineDto findByTitle(String name) {
        WineDto dto = restTemplate.postForEntity(this.wineServiceUrl + "getbytitle", name, WineDto.class).getBody();
        return wineMapper.toExternal(dto);
    }
}
