package ro.fortech.winewiki.apigateway.dto;

import lombok.*;
import java.io.Serializable;
import java.sql.Date;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode
public class ApiGatewayNewWineDto implements Serializable {
    private String title;
    private String description;
    private String location;
    private Date startDate;
    private Date endDate;
    private Double cost;
    private String owner;
    private String wineType;

    @Override
    public String toString() {
        return "ApiGatewayNewWineDto{" +
                "title='" + title + '\'' +
                "description='" + description + '\'' +
                ", location='" + location + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", cost=" + cost +
                ", owner='" + owner + '\'' +
                ", winetype='" + wineType + '\'' +
                '}';
    }
}
