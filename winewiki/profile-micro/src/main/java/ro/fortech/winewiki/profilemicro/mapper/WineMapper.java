package ro.fortech.winewiki.profilemicro.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import ro.fortech.winewiki.profilemicro.dto.WineDto;
import ro.fortech.winewiki.profilemicro.model.Wine;
import ro.fortech.winewiki.profilemicro.repository.PersonRepository;
import ro.fortech.winewiki.profilemicro.repository.WineTypeRepository;

@Service
public class WineMapper extends AbstractMapper<Wine, WineDto> {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private WineTypeRepository wineTypeRepository;

    @Override
    public Wine toInternal(WineDto dto) {
        Wine wine = Wine.builder()
                .title(dto.getTitle())
                .description(dto.getDescription())
                .location(dto.getLocation())
                .startDate(dto.getStartDate())
                .endDate(dto.getEndDate())
                .startTime(dto.getStartTime())
                .endTime(dto.getEndTime())
                .cost(dto.getCost())
                .owner(personRepository.findByUsername(dto.getOwner()))
                .winetype(wineTypeRepository.findByName(dto.getWineType()))
                .build();
        wine.setId(dto.getId());
        return wine;
    }

    @Override
    public WineDto toExternal(Wine model) {
        return new WineDto(model.getId(), model.getTitle(), model.getDescription(),
                model.getLocation(), model.getStartDate(), model.getEndDate(),
                model.getStartTime(), model.getEndTime(), model.getCost(),
                model.getOwner().getUsername(), model.getWinetype().getName());
    }

    public Page<WineDto> toExternalPage(Page<Wine> objectEntityPage) {
        return objectEntityPage.map(model -> new WineDto(model.getId(), model.getTitle(), model.getDescription(),
                model.getLocation(), model.getStartDate(), model.getEndDate(),
                model.getStartTime(), model.getEndTime(), model.getCost(),
                model.getOwner().getUsername(), model.getWinetype().getName()));
    }
}
