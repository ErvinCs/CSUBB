package ro.blooddonation.web.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.blooddonation.core.Domain.DoningCenter;
import ro.blooddonation.core.Service.DoningCenterService;
import ro.blooddonation.web.Converter.DoningCenterConverter;
import ro.blooddonation.web.Dto.DoningCenterDto;
import ro.blooddonation.web.Dto.DoningCentersDto;
import ro.blooddonation.web.Dto.EmptyJsonResponse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class DoningCenterController implements IController<DoningCenterDto, DoningCentersDto>
{
    private static final Logger log = LoggerFactory.getLogger(DoningCenterController.class);

    @Autowired
    private DoningCenterService doningCenterService;

    @Autowired
    private DoningCenterConverter doningCenterConverter;


    @RequestMapping(value = "/doningCenters", method = RequestMethod.POST)
    public DoningCenterDto add(@RequestBody final DoningCenterDto dcDto)
    {
        log.trace("addDoningCenter: doningCenterDtoMap={}", dcDto);

        DoningCenter dc = new DoningCenter(dcDto.getAddress());
        dc.setId(dcDto.getId());
        doningCenterService.add(dc);

        DoningCenterDto result = doningCenterConverter.convertModelToDto(dc);

        log.trace("addDoningCenter: result={}", result);

        return result;
    }


    @RequestMapping(value = "doningCenters/{id}", method = RequestMethod.DELETE)
    public ResponseEntity remove(@PathVariable final Long id)
    {
        log.trace("removeDoningCenter: id={}", id);

        doningCenterService.remove(id);

        log.trace("removeDoningCenter - method end");

        return new ResponseEntity(new EmptyJsonResponse(), HttpStatus.OK);
    }


    @RequestMapping(value = "/doningCenters/{id}", method = RequestMethod.PUT)
    public DoningCenterDto update(@PathVariable final Long id,
                                  @RequestBody final DoningCenterDto newDcDto) {
        log.trace("updateDoningCenter: id={}, doningCenterDtoMap={}", id, newDcDto);

        DoningCenter dc = new DoningCenter(newDcDto.getAddress());
        dc.setId(id);

        Optional<DoningCenter> doningCenter = doningCenterService.update(id, dc);

        Map<String, DoningCenterDto> result = new HashMap<>();
        if (doningCenter.isPresent())
            result.put("doningCenter", doningCenterConverter.convertModelToDto(doningCenter.get()));
        else
            result.put("doningCenter", doningCenterConverter.convertModelToDto(new DoningCenter()));

        log.trace("updateDoningCenter: result={}", result);

        return result.get("doningCenter");
    }


    @RequestMapping(value = "/doningCenters", method = RequestMethod.GET)
    public DoningCentersDto getAll()
    {
        log.trace("getAllDoningCenters");

        List<DoningCenter> dcs = doningCenterService.findAll();

        log.trace("getAllDoningCenters: doningCenters={}", dcs);

        return new DoningCentersDto(doningCenterConverter.convertModelsToDtos(dcs));
    }
}
