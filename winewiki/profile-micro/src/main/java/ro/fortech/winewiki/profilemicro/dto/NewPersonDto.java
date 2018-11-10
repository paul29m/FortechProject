package ro.fortech.winewiki.profilemicro.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.io.Serializable;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class NewPersonDto implements Serializable {
    private String username;
    private String password;
    private String location;
    private String email = "";

    @Override
    public String toString() {
        return "NewPersonDto{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", location='" + location + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}

