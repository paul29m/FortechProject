package ro.fortech.winewiki.profilemicro.dto;

import lombok.*;
import java.io.Serializable;
import java.util.Set;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode
public class WineTypeDtoList implements Serializable {
    private Set<WineTypeDto> wineTypeDtos;

    @Override
    public String toString() {
        return "WineTypeDtoList{" +
                "wineTypeDtos=" + wineTypeDtos +
                '}';
    }
}
