package ro.fortech.winewiki.apigateway.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ro.fortech.winewiki.apigateway.dto.ApiGatewayNewPersonDto;
import ro.fortech.winewiki.apigateway.dto.ApiGatewayPersonDto;
import ro.fortech.winewiki.apigateway.mapper.NewPersonMapper;
import ro.fortech.winewiki.apigateway.mapper.PersonMapper;
import ro.fortech.winewiki.profilemicro.dto.NewPersonDto;
import ro.fortech.winewiki.profilemicro.dto.PersonDto;
import ro.fortech.winewiki.profilemicro.dto.PersonDtoList;

import java.util.Objects;
import java.util.Set;

@Service
public class PersonServiceImpl implements PersonService{

    private final String personServiceUrl = "http://localhost:9998/profilemicro/person/";

    private RestTemplate restTemplate = new RestTemplate();

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private PersonMapper personMapper;

    @Autowired
    private NewPersonMapper newPersonMapper;

    @Override
    public ApiGatewayNewPersonDto create(ApiGatewayNewPersonDto newPersonDto) {
        newPersonDto.setPassword(passwordEncoder.encode(newPersonDto.getPassword()));
        NewPersonDto dto = restTemplate
                .postForEntity(personServiceUrl + "create", newPersonMapper.toInternal(newPersonDto), NewPersonDto.class).getBody();
        return newPersonMapper.toExternal(dto);
    }

    @Override
    public void delete(Long id) {
        restTemplate.delete(this.personServiceUrl + "delete/" + id);
    }

    @Override
    public Set<ApiGatewayPersonDto> findAll() {
        PersonDtoList personDtoList = restTemplate.getForEntity(this.personServiceUrl + "getall", PersonDtoList.class).getBody();
        return personMapper.toExternals(personDtoList.getPersonDtoList());
    }

    @Override
    public ApiGatewayPersonDto update(ApiGatewayPersonDto personDto) {
        if (!Objects.equals(findByUsername(personDto.getUsername()).getPassword(), personDto.getPassword())) {
            personDto.setPassword(passwordEncoder.encode(personDto.getPassword()));
        }
        HttpEntity<ApiGatewayNewPersonDto> updatedInstance = new HttpEntity<>(personDto);
        ApiGatewayPersonDto apiGatewayPersonDto = personMapper.toExternal(
                restTemplate.exchange(this.personServiceUrl + "update", HttpMethod.PUT, updatedInstance, PersonDto.class).getBody());
        return apiGatewayPersonDto;
    }

    @Override
    public ApiGatewayPersonDto findById(Long id) {
        PersonDto dto = restTemplate.postForEntity(this.personServiceUrl + "getbyid", id, PersonDto.class).getBody();
        return personMapper.toExternal(dto);
    }

    @Override
    public ApiGatewayPersonDto findByUsername(String username) {
        PersonDto dto = restTemplate.postForEntity(this.personServiceUrl + "getbyusername", username, PersonDto.class).getBody();
        return personMapper.toExternal(dto);
    }

}
