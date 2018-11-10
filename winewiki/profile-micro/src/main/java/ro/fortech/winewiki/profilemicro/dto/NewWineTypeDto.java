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
public class NewWineTypeDto implements Serializable {
    private String name;

    @Override
    public String toString() {
        return "NewWineTypeDto{" +
                "name='" + name + '\'' +
                '}';
    }
}

