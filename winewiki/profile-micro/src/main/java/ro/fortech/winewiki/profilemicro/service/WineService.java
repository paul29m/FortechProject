package ro.fortech.winewiki.profilemicro.service;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import ro.fortech.winewiki.profilemicro.dto.NewWineDto;
import ro.fortech.winewiki.profilemicro.dto.WineDto;
import ro.fortech.winewiki.profilemicro.model.Wine;

import java.util.Set;

public interface WineService {

    NewWineDto create(NewWineDto newWineDto);

    void delete(Long id);

    Set<WineDto> findAll();

    WineDto update(WineDto wineDto);

    WineDto findById(Long id);

    WineDto findByTitle(String name);

    Page<WineDto> findBySearchTerm(String searchTerm);

    Page<WineDto> findAllPages(int size, int page, Specification<Wine> wineSpecification);

}
