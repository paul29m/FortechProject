package ro.fortech.winewiki.profilemicro.service;


import ro.fortech.winewiki.profilemicro.dto.NewWineTypeDto;
import ro.fortech.winewiki.profilemicro.dto.WineTypeDto;
import java.util.Set;

public interface WineTypeService {

    NewWineTypeDto create(NewWineTypeDto newWineTypeDto);

    void delete(Long id);

    Set<WineTypeDto> findAll();

    WineTypeDto update(WineTypeDto wineTypeDto);

    WineTypeDto findById(Long id);

    WineTypeDto findByName(String name);
}
