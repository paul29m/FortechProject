package ro.fortech.winewiki.apigateway.dto;

import lombok.*;

import java.io.Serializable;
import java.util.Set;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode
public class ApiGatewayPersonDtoList implements Serializable {
    private Set<ApiGatewayPersonDto> personDtoList;

    @Override
    public String toString() {
        return "PersonDtoList{" +
                "personDtoList=" + personDtoList +
                '}';
    }
}
