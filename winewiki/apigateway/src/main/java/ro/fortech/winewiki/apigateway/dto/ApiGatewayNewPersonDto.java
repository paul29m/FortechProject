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
public class ApiGatewayNewPersonDto implements Serializable {
    private String username;
    private String password;
    private String location;
    private String email;

    public void setPassword(String password){
        this.password = password;
    }


    @Override
    public String toString() {
        return "ApiGatewayNewPersonDto{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", location='" + location + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}

