package ro.fortech.winewiki.profilemicro.mapper;

import org.springframework.stereotype.Service;
import ro.fortech.winewiki.profilemicro.dto.NewPersonDto;
import ro.fortech.winewiki.profilemicro.model.Person;

import java.util.LinkedHashSet;

@Service
public class NewPersonMapper extends AbstractMapper<Person, NewPersonDto> {

    @Override
    public Person toInternal(NewPersonDto dto) {
        Person person = Person.builder()
                .username(dto.getUsername())
                .password(dto.getPassword())
                .location(dto.getLocation())
                .mail(dto.getEmail())
                .wineSet(new LinkedHashSet<>())
                .build();
        return person;
    }

    @Override
    public NewPersonDto toExternal(Person model) {
        NewPersonDto newPersonDto = NewPersonDto.builder()
                .username(model.getUsername())
                .password(model.getPassword())
                .location(model.getLocation())
                .email(model.getMail())
                .build();
        return newPersonDto;
    }
}
