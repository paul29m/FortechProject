package ro.fortech.winewiki.profilemicro.dto.filters;

import org.springframework.data.jpa.domain.Specification;
import ro.fortech.winewiki.profilemicro.model.Wine;
import ro.fortech.winewiki.profilemicro.model.WineType;

import javax.persistence.criteria.*;
import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class WineFilter implements Specification<Wine> {
    private SearchCriteria criteria;

    public WineFilter(SearchCriteria criteria) {
        this.criteria = criteria;
    }

    @Override
    public Predicate toPredicate
            (Root<Wine> root, CriteriaQuery<?> query, CriteriaBuilder builder) {

        if (criteria.getKey().equalsIgnoreCase("winetype")) {
            final Path<WineType> wineTypePath = root.<WineType>get("winetype");
            return builder.like(builder.lower(wineTypePath.get("name")), criteria.getValue().toString() != null ? criteria.getValue().toString().toLowerCase() : null);
        } else {
            if (criteria.getOperation().equalsIgnoreCase(">")) {
                if (criteria.getKey().equalsIgnoreCase("startTime")) {
                    try {
                        Time startTime = new Time(new SimpleDateFormat("HH:mm:ss").parse(criteria.getValue().toString()).getTime());
                        return builder.greaterThanOrEqualTo(
                                root.get(criteria.getKey()), startTime);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
                return builder.greaterThanOrEqualTo(
                        root.get(criteria.getKey()), criteria.getValue().toString());
            } else if (criteria.getOperation().equalsIgnoreCase("<")) {
                if (criteria.getKey().equalsIgnoreCase("endTime")) {
                    try {
                        Time endTime = new Time(new SimpleDateFormat("HH:mm:ss").parse(criteria.getValue().toString()).getTime());
                        return builder.lessThanOrEqualTo(
                                root.get(criteria.getKey()), endTime);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
                if (criteria.getKey().equalsIgnoreCase("endDate")) {
                    try {
                        return builder.lessThanOrEqualTo(
                                root.get(criteria.getKey()), new Date(new SimpleDateFormat("yyyy-MM-dd").parse(criteria.getValue().toString()).getTime()));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
                return builder.lessThanOrEqualTo(
                        root.get(criteria.getKey()), criteria.getValue().toString());
            } else if (criteria.getOperation().equalsIgnoreCase(":")) {
                if (root.get(criteria.getKey()).getJavaType() == String.class) {
                    return builder.like(
                            root.get(criteria.getKey()), "%" + criteria.getValue() + "%");
                } else {
                    return builder.equal(root.get(criteria.getKey()), criteria.getValue());
                }
            }
            return null;
        }
    }
}
