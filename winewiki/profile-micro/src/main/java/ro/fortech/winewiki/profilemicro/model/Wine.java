package ro.fortech.winewiki.profilemicro.model;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;
import java.util.Objects;

@Entity
@Table(name = "wine")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Wine extends BaseEntity<Long> {

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description")
    private String description;


    @Column(name = "location", nullable = false)
    private String location;

    @Column(name = "startdate", nullable = false)
    private Date startDate;

    @Column(name = "enddate", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDate;

    @Column(name = "starttime")
    private Time startTime;

    @Column(name = "endtime")
    private Time endTime;

    @Column(name = "cost", nullable = false)
    private Double cost;


    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private Person owner;

    @ManyToOne
    @JoinColumn(name = "wine_type_id", nullable = false)
    private WineType winetype;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Wine wine = (Wine) o;
        return
                Objects.equals(title, wine.title) &&
                Objects.equals(description, wine.description) &&
                Objects.equals(location, wine.location) &&
                Objects.equals(startDate, wine.startDate) &&
                Objects.equals(endDate, wine.endDate) &&
                Objects.equals(startTime, wine.startTime) &&
                Objects.equals(endTime, wine.endTime) &&
                Objects.equals(cost, wine.cost);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, description, location, startDate, endDate, startTime, endTime, cost);
    }
}
