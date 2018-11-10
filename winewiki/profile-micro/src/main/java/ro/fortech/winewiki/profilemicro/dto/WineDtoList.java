package ro.fortech.winewiki.profilemicro.dto;

import lombok.*;
import java.util.Set;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode
public class WineDtoList {
    @Override
    public String toString() {
        return "WineDtoList{" +
                "wineDtoList=" + wineDtoList +
                '}';
    }

    private Set<WineDto> wineDtoList;
}
