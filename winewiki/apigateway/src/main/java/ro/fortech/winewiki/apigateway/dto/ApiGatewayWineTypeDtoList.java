package ro.fortech.winewiki.apigateway.dto;

import lombok.*;

import java.io.Serializable;
import java.util.Set;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode
public class ApiGatewayWineTypeDtoList implements Serializable {
    private Set<ApiGatewayWineTypeDto> apiGatewayWineTypeDtoList;

    @Override
    public String toString() {
        return "ApiGatewayWineTypeDtoList{" +
                "apiGatewayWineTypeDtoList=" + apiGatewayWineTypeDtoList +
                '}';
    }
}
