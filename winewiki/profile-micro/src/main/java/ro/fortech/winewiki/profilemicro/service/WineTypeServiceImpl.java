package ro.fortech.winewiki.profilemicro.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.fortech.winewiki.profilemicro.dto.NewWineTypeDto;
import ro.fortech.winewiki.profilemicro.dto.WineTypeDto;
import ro.fortech.winewiki.profilemicro.mapper.NewWineTypeMapper;
import ro.fortech.winewiki.profilemicro.mapper.WineTypeMapper;
import ro.fortech.winewiki.profilemicro.model.WineType;
import ro.fortech.winewiki.profilemicro.repository.WineTypeRepository;
import java.util.Set;

@Service
public class WineTypeServiceImpl implements WineTypeService {

    @Autowired
    private WineTypeRepository wineTypeRepository;

    @Autowired
    private WineTypeMapper wineTypeMapper;

    @Autowired
    private NewWineTypeMapper newWineTypeMapper;

    @Override
    public NewWineTypeDto create(NewWineTypeDto newWineTypeDto) {
        if (wineTypeRepository.findByName(newWineTypeDto.getName()) == null) {
            WineType createdWineType = newWineTypeMapper.toInternal(newWineTypeDto);
            return newWineTypeMapper.toExternal(wineTypeRepository.save(createdWineType));
        }
        return null;
    }

    @Transactional
    @Override
    public void delete(Long id) {
        WineType deletedWineType = wineTypeRepository.findOne(id);
        wineTypeRepository.delete(deletedWineType);
    }

    @Override
    public Set<WineTypeDto> findAll() {
        Set<WineTypeDto> wineTypeDtos = wineTypeMapper.toExternals(wineTypeRepository.findAllByOrderByNameAsc());
        return wineTypeDtos;
    }

    @Override
    public WineTypeDto findById(Long id) {
        return wineTypeMapper.toExternal(wineTypeRepository.findOne(id));
    }

    @Override
    public WineTypeDto findByName(String name) {
        WineType dto = wineTypeRepository.findByName(name);
        return wineTypeMapper.toExternal(dto);
    }

    @Override
    @Transactional
    public WineTypeDto update(WineTypeDto wineTypeDto) {
        WineType wineTypeToUpdate = wineTypeRepository.findOne(wineTypeDto.getId());
        WineType wineTypeUpdated = wineTypeRepository.save(wineTypeToUpdate);
        return wineTypeMapper.toExternal(wineTypeUpdated);
    }
}
