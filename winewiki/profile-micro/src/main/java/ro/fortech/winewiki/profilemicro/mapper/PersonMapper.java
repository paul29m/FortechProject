package ro.fortech.winewiki.profilemicro.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.fortech.winewiki.profilemicro.dto.PersonDto;
import ro.fortech.winewiki.profilemicro.dto.WineDto;
import ro.fortech.winewiki.profilemicro.dto.WineDtoList;
import ro.fortech.winewiki.profilemicro.model.Person;
import ro.fortech.winewiki.profilemicro.repository.WineRepository;

import java.util.LinkedHashSet;
import java.util.stream.Collectors;

@Service
public class PersonMapper extends AbstractMapper<Person, PersonDto> {

    @Autowired
    private WineRepository wineRepository;

    @Override
    public Person toInternal(PersonDto dto) {
        if(!dto.getWines().getWineDtoList().isEmpty()) {
            Person person = Person.builder()
                    .username(dto.getUsername())
                    .password(dto.getPassword())
                    .location(dto.getLocation())
                    .firstname(dto.getFirstname())
                    .lastname(dto.getLastname())
                    .description(dto.getDescription())
                    .picture(dto.getPicture())
                    .wineSet(dto.getWines().getWineDtoList().stream()
                            .map(wineDto -> wineRepository.findOne(wineDto.getId()))
                            .collect(Collectors.toSet()))
                    .build();
            person.setId(dto.getId());
            return person;
        } else {
            Person person = Person.builder()
                    .username(dto.getUsername())
                    .password(dto.getPassword())
                    .location(dto.getLocation())
                    .firstname(dto.getFirstname())
                    .lastname(dto.getLastname())
                    .description(dto.getDescription())
                    .picture(dto.getPicture())
                    .wineSet(new LinkedHashSet<>())
                    .build();
            person.setId(dto.getId());
            return person;
        }
    }

    @Override
    public PersonDto toExternal(Person model) {
        if(!model.getWineSet().isEmpty()) {
            PersonDto personDto = new PersonDto(model.getId(), model.getUsername(), model.getPassword(), model.getLocation(),
                    model.getFirstname(),
                    model.getLastname(),
                    model.getDescription(),
                    model.getPicture(),
                    new WineDtoList(model.getWineSet().stream()
                            .map(wine -> new WineDto(wine.getId(), wine.getTitle(), wine.getDescription(),
                                    wine.getLocation(), wine.getStartDate(), wine.getEndDate(),
                                    wine.getStartTime(), wine.getEndTime(), wine.getCost(),
                                    wine.getOwner().getUsername(), wine.getWinetype().getName()))
                            .collect(Collectors.toSet())));
            return personDto;
        } else {
            PersonDto personDto = new PersonDto(model.getId(), model.getUsername(), model.getPassword(), model.getLocation(),
                    model.getFirstname(),
                    model.getLastname(),
                    model.getDescription(),
                    model.getPicture(),
                    new WineDtoList(new LinkedHashSet<>()));
            return personDto;
        }
    }
}