package ro.fortech.winewiki.profilemicro.dto;

import java.sql.Date;
import java.sql.Time;

public class WineDto extends NewWineDto {
    private Long id;
    private Time startTime;
    private Time endTime;

    private WineDto() {
    }

    public WineDto(Long id, String title, String description, String location,
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
        return "WineDto{" +
                "id=" + id +
                ", status=" +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }
}
