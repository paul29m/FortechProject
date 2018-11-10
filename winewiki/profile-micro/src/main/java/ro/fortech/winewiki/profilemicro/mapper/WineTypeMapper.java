package ro.fortech.winewiki.profilemicro.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.fortech.winewiki.profilemicro.dto.WineDto;
import ro.fortech.winewiki.profilemicro.dto.WineDtoList;
import ro.fortech.winewiki.profilemicro.dto.WineTypeDto;
import ro.fortech.winewiki.profilemicro.model.WineType;
import ro.fortech.winewiki.profilemicro.repository.WineRepository;

import java.util.LinkedHashSet;
import java.util.stream.Collectors;

@Service
public class WineTypeMapper extends AbstractMapper<WineType, WineTypeDto> {

    @Autowired
    private WineRepository wineRepository;

    @Override
    public WineType toInternal(WineTypeDto dto) {
        if(!dto.getWines().getWineDtoList().isEmpty()) {
            WineType wineType = WineType.builder()
                    .name(dto.getName())
                    .wines(dto.getWines().getWineDtoList().stream()
                            .map(wineDto -> wineRepository.findOne(wineDto.getId()))
                            .collect(Collectors.toSet()))
                    .build();
            wineType.setId(dto.getId());
            return wineType;
        }else{
            WineType wineType = WineType.builder()
                    .name(dto.getName())
                    .wines(new LinkedHashSet<>())
                    .build();
            wineType.setId(dto.getId());
            return wineType;
        }
    }

    @Override
    public WineTypeDto toExternal(WineType model) {
        if(!model.getWines().isEmpty()) {
            WineTypeDto wineTypeDto = new WineTypeDto(model.getId(), model.getName(),
                    new WineDtoList(model.getWines().stream()
                            .map(wine -> new WineDto(wine.getId(), wine.getTitle(), wine.getDescription(),
                                    wine.getLocation(),  wine.getStartDate(), wine.getEndDate(),
                                    wine.getStartTime(), wine.getEndTime(), wine.getCost(),
                                    wine.getOwner().getUsername(), wine.getWinetype().getName()))
                            .collect(Collectors.toSet())));
            return wineTypeDto;
        } else {
            return new WineTypeDto(model.getId(), model.getName(),
                    new WineDtoList(new LinkedHashSet<>()));
        }
    }
}