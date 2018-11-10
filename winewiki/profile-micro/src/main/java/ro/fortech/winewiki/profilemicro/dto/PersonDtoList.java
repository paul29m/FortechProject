package ro.fortech.winewiki.profilemicro.dto;

import lombok.*;
import java.io.Serializable;
import java.util.Set;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode
public class PersonDtoList implements Serializable {
    private Set<PersonDto> personDtoList;

    @Override
    public String toString() {
        return "PersonDtoList{" +
                "personDtoList=" + personDtoList +
                '}';
    }
}
