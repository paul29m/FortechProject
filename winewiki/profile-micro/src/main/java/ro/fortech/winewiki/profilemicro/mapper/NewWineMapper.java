package ro.fortech.winewiki.profilemicro.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.fortech.winewiki.profilemicro.dto.NewWineDto;
import ro.fortech.winewiki.profilemicro.model.Wine;
import ro.fortech.winewiki.profilemicro.repository.PersonRepository;
import ro.fortech.winewiki.profilemicro.repository.WineTypeRepository;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@Service
public class NewWineMapper extends AbstractMapper<Wine, NewWineDto> {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private WineTypeRepository wineTypeRepository;

    @Override
    public Wine toInternal(NewWineDto dto) {
        try {
            return Wine.builder()
                    .title(dto.getTitle())
                    .description(dto.getDescription())
                    .location(dto.getLocation())
                    .startDate(dto.getStartDate())
                    .endDate(dto.getEndDate())
                    .cost(dto.getCost())
                    .owner(personRepository.findByUsername(dto.getOwner()))
                    .winetype(wineTypeRepository.findByName(dto.getWineType()))
                    .startTime(new Time(new SimpleDateFormat("HH:mm:ss").parse("00:00:00").getTime()))
                    .endTime(new Time(new SimpleDateFormat("HH:mm:ss").parse("23:59:59").getTime()))
                    .build();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public NewWineDto toExternal(Wine model) {
        return NewWineDto.builder()
                .title(model.getTitle())
                .description(model.getDescription())
                .location(model.getLocation())
                .startDate(model.getStartDate())
                .endDate(model.getEndDate())
                .cost(model.getCost())
                .owner(model.getOwner().getUsername())
                .wineType(model.getWinetype().getName())
                .build();
    }
}
