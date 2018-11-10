package ro.fortech.winewiki.apigateway.dto;

import lombok.*;
import java.util.Set;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode
public class ApiGatewayWineDtoList {
    @Override
    public String toString() {
        return "ApiGatewayWineDtoList{" +
                "apiGatewayWineDtoList=" + apiGatewayWineDtoList +
                '}';
    }

    private Set<ApiGatewayWineDto> apiGatewayWineDtoList;
}
