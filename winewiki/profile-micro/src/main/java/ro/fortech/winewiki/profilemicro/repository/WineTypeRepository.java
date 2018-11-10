package ro.fortech.winewiki.profilemicro.repository;

import org.springframework.data.repository.CrudRepository;
import ro.fortech.winewiki.profilemicro.model.WineType;

import java.util.LinkedHashSet;

public interface WineTypeRepository extends CrudRepository<WineType, Long> {

    LinkedHashSet<WineType> findAllByOrderByNameAsc();

    WineType findByName(String name);

}
