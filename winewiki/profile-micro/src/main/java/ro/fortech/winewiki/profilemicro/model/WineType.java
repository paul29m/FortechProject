package ro.fortech.winewiki.profilemicro.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name = "wine_type")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class WineType extends BaseEntity<Long>  {
    private String name;
    @OneToMany(mappedBy="winetype")
    private Set<Wine> wines;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WineType wineType = (WineType) o;

        return name.equals(wineType.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
