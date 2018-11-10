package ro.fortech.winewiki.apigateway.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.io.Serializable;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ApiGatewayNewWineTypeDto implements Serializable {
    private String name;

    @Override
    public String toString() {
        return "ApiGatewayNewWineTypeDto{" +
                "name='" + name + '\'' +
                '}';
    }
}

