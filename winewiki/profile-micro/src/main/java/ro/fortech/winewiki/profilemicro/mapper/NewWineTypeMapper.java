package ro.fortech.winewiki.profilemicro.mapper;

import org.springframework.stereotype.Service;
import ro.fortech.winewiki.profilemicro.dto.NewWineTypeDto;
import ro.fortech.winewiki.profilemicro.model.WineType;

import java.util.LinkedHashSet;

@Service
public class NewWineTypeMapper extends AbstractMapper<WineType, NewWineTypeDto> {

    @Override
    public WineType toInternal(NewWineTypeDto dto) {
        return WineType.builder()
                .name(dto.getName())
                .wines(new LinkedHashSet<>())
                .build();
    }

    @Override
    public NewWineTypeDto toExternal(WineType model) {
        return NewWineTypeDto.builder()
                .name(model.getName())
                .build();
    }
}
