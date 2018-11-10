package ro.fortech.winewiki.profilemicro.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.fortech.winewiki.profilemicro.dto.NewWineDto;
import ro.fortech.winewiki.profilemicro.dto.WineDto;
import ro.fortech.winewiki.profilemicro.mapper.NewWineMapper;
import ro.fortech.winewiki.profilemicro.mapper.WineMapper;
import ro.fortech.winewiki.profilemicro.model.Person;
import ro.fortech.winewiki.profilemicro.model.Wine;
import ro.fortech.winewiki.profilemicro.repository.PersonRepository;
import ro.fortech.winewiki.profilemicro.repository.WineRepository;

import java.sql.Date;
import java.sql.Time;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Service
public class WineServiceImpl implements WineService {

    private static final String EMPTY_STRING = "";

    @Autowired
    private WineRepository wineRepository;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private WineMapper wineMapper;

    @Autowired
    private NewWineMapper newWineMapper;

    @Override
    public NewWineDto create(NewWineDto newWineDto) {
        if (wineRepository.findByTitle(newWineDto.getTitle()) == null) {
            Wine createdWine = newWineMapper.toInternal(newWineDto);
            return newWineMapper.toExternal(wineRepository.save(createdWine));
        }
        return null;
    }

    @Transactional
    @Override
    public void delete(Long id) {
        Wine deletedWineType = wineRepository.findOne(id);
        wineRepository.delete(deletedWineType);
    }

    @Override
    public Set<WineDto> findAll() {
        Set<WineDto> wineDtos = wineMapper.toExternals(new LinkedHashSet<>((List<Wine>) wineRepository.findAll()));
        return wineDtos;
    }

    @Override
    public WineDto findById(Long id) {
        return wineMapper.toExternal(wineRepository.findOne(id));
    }

    @Override
    public WineDto findByTitle(String name) {
        Wine dto = wineRepository.findByTitle(name);
        return wineMapper.toExternal(dto);
    }

    @Transactional(readOnly = true)
    @Override
    public Page<WineDto> findAllPages(int page, int size, Specification<Wine> wineSpecification) {
        PageRequest pageRequest = (PageRequest) createPageRequest(page, size);
        Page<Wine> resultPage = wineRepository.findAll(Specifications.where(wineSpecification), pageRequest);
        return wineMapper.toExternalPage(resultPage);
    }

    @Transactional(readOnly = true)
    @Override
    public Page<WineDto> findBySearchTerm(String searchTerm) {
        PageRequest pageRequest = (PageRequest) createPageRequest(0, 20);
        Page<Wine> searchResultPage = wineRepository.findByDescriptionContainsOrTitleContainsAllIgnoreCase(searchTerm,
                searchTerm, pageRequest);

        return wineMapper.toExternalPage(searchResultPage);
    }

    private Pageable createPageRequest(int page, int size) {
        return new PageRequest(page,
                size,
                new Sort(Sort.Direction.DESC, "description")
                        .and(new Sort(Sort.Direction.ASC, "title"))
        );
    }

    @Override
    @Transactional
    public WineDto update(WineDto wineDto) {
        Wine wineToUpdate = wineRepository.findOne(wineDto.getId());

        String title = wineDto.getTitle();
        String description = wineDto.getDescription();

        String location = wineDto.getLocation();
        Double cost = wineDto.getCost();
        Date startDate = wineToUpdate.getStartDate();
        Date endDate = wineDto.getEndDate();
        Time startTime = wineDto.getStartTime();
        Time endTime = wineDto.getEndTime();

        String ownerUsername = wineDto.getOwner();
        Person owner = personRepository.findByUsername(ownerUsername);

        if(title != EMPTY_STRING) {
            wineToUpdate.setTitle(wineDto.getTitle());
        }
        if(description != EMPTY_STRING) {
            wineToUpdate.setDescription(description);
        }

        if(location != EMPTY_STRING) {
            wineToUpdate.setLocation(location);
        }
        if(cost != null && cost >= 0) {
            wineToUpdate.setCost(cost);
        }
        if(startDate != null) {
            wineToUpdate.setStartDate(startDate);
        }
        if(endDate != null) {
            wineToUpdate.setEndDate(endDate);
        }
        if(startTime != null) {
            wineToUpdate.setStartTime(startTime);
        }
        if(endTime != null) {
            wineToUpdate.setEndTime(endTime);
        }

        if(owner != null) {
            wineToUpdate.setOwner(owner);
        }

        personRepository.save(owner);
        Wine wineUpdated = wineRepository.save(wineToUpdate);
        return wineMapper.toExternal(wineUpdated);
    }

}