package ro.fortech.winewiki.profilemicro.dto.filters;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;
import ro.fortech.winewiki.profilemicro.model.Wine;

import java.util.ArrayList;
import java.util.List;

public class WineFilterBuilder {
    List<Specification<Wine>> specs = new ArrayList<>();

    public WineFilterBuilder() {
        specs = new ArrayList<>();
    }

    public WineFilterBuilder with(WineFilter wineFilter) {
        specs.add(wineFilter);
        return this;
    }

    public Specification<Wine> build() {
        if (specs.size() == 0) {
            return null;
        }

        Specification<Wine> result = specs.get(0);
        for (int i = 1; i < specs.size(); i++) {
            result = Specifications.where(result).and(specs.get(i));
        }
        return result;
    }
}
