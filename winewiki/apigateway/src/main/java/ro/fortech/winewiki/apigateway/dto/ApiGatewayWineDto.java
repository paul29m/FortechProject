package ro.fortech.winewiki.apigateway.dto;

import java.sql.Date;
import java.sql.Time;

public class ApiGatewayWineDto extends ApiGatewayNewWineDto {
    private Long id;
    private Time startTime;
    private Time endTime;

    private ApiGatewayWineDto() {
    }

    public ApiGatewayWineDto(Long id, String title, String description, String location,
                             Date startDate, Date endDate,
                             Time startTime, Time endTime, Double cost,
                             String owner, String wineType) {
        super(title, description, location,  startDate, endDate, cost,  owner, wineType);
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Long getId() {
        return id;
    }


    public Time getStartTime() {
        return startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    @Override
    public String toString() {
        return "ApiGatewayWineDto{" +
                "id=" + id +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }
}
