package ro.fortech.winewiki.profilemicro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import ro.fortech.winewiki.profilemicro.dto.NewWineDto;
import ro.fortech.winewiki.profilemicro.dto.WineDto;
import ro.fortech.winewiki.profilemicro.dto.WineDtoList;
import ro.fortech.winewiki.profilemicro.dto.filters.SearchCriteria;
import ro.fortech.winewiki.profilemicro.dto.filters.WineFilter;
import ro.fortech.winewiki.profilemicro.dto.filters.WineFilterBuilder;
import ro.fortech.winewiki.profilemicro.mapper.PersonMapper;
import ro.fortech.winewiki.profilemicro.repository.PersonRepository;
import ro.fortech.winewiki.profilemicro.repository.WineRepository;
import ro.fortech.winewiki.profilemicro.service.PersonService;
import ro.fortech.winewiki.profilemicro.service.WineService;

import java.util.LinkedHashSet;

@RequestMapping("/profilemicro/wine/")
@RestController
public class WineController {


    @Autowired
    private WineService wineService;

    @Autowired
    private PersonService personService;

    @Autowired
    private WineRepository wineRepository;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PersonMapper personMapper;

    @RequestMapping(value = "getall", method = RequestMethod.GET)
    public WineDtoList getAll() {
        WineDtoList wineDtoList = new WineDtoList(wineService.findAll());
        return wineDtoList;
    }


    @RequestMapping(value = "pages/getall", params = {"page", "size"}, method = RequestMethod.GET)
    @ResponseBody
    public Page<WineDto> findPaginated(
            @RequestParam("page") int page,
            @RequestParam("size") int size,
            @RequestParam(value = "type", required = false) String type,
            @RequestParam(value = "location", required = false) String location,
            @RequestParam(value = "start_time", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) String startTime,
            @RequestParam(value = "end_time", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) String endTime,
            @RequestParam(value = "available_until", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") String availableUntil) {

        WineFilterBuilder builder = new WineFilterBuilder();

        if (type != null && !type.isEmpty() && !type.equalsIgnoreCase("All")) {
            WineFilter typeFilter = new WineFilter(new SearchCriteria("winetype", ":", type));
            builder.with(typeFilter);
        }

        if (location != null && !location.isEmpty()) {
            WineFilter locationFilter = new WineFilter(new SearchCriteria("location", ":", location));
            builder.with(locationFilter);
        }

        if (startTime != null && !startTime.isEmpty() && endTime != null && !endTime.isEmpty()) {

            builder.with(new WineFilter(new SearchCriteria("startTime", ">", startTime)));

            builder.with(new WineFilter(new SearchCriteria("endTime", "<", endTime)));
        }

        if (availableUntil != null && !availableUntil.isEmpty()) {
            WineFilter availableUntilFilter = new WineFilter(new SearchCriteria("endDate", "<", availableUntil));
            builder.with(availableUntilFilter);
        }
        Page<WineDto> resultPage = wineService.findAllPages(page, size, builder.build());
        return resultPage;
    }

    @RequestMapping(value = "search", method = RequestMethod.GET)
    public WineDtoList findBySearchTerm(@RequestParam("searchTerm") String searchTerm) {
        return new WineDtoList(new LinkedHashSet<>(wineService.findBySearchTerm(searchTerm).getContent()));
    }

    @RequestMapping(value = "getbyid", method = RequestMethod.POST)
    public WineDto getById(@RequestBody Long id) {
        return wineService.findById(id);
    }

    @RequestMapping(value = "getbyid/{id}", method = RequestMethod.GET)
    public WineDto getByIdFromUrl(@PathVariable Long id) {
        return wineService.findById(id);
    }

    @RequestMapping(value = "create", method = RequestMethod.POST)
    public NewWineDto createWine(@RequestBody NewWineDto newWineDto) {
        return wineService.create(newWineDto);
    }

    @RequestMapping(value = "update", method = RequestMethod.PUT)
    public WineDto updateWine(@RequestBody WineDto wineDto) {
        return wineService.update(wineDto);
    }

    @RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE)
    public void deleteWine(@PathVariable Long id) {
        wineService.delete(id);
    }

    @RequestMapping(value = "getbytitle", method = RequestMethod.POST)
    public WineDto getWineByTitle(@RequestBody String title) {
        return wineService.findByTitle(title);
    }

    @RequestMapping(value = "getbytitle/{title}", method = RequestMethod.GET)
    public WineDto getWineByTitleFromUrl(@PathVariable String title) {
        return wineService.findByTitle(title);
    }

}
