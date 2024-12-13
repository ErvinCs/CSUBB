package ro.blooddonation.web.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.blooddonation.core.Domain.Doctor;
import ro.blooddonation.core.Domain.Request;
import ro.blooddonation.core.Service.RequestService;
import ro.blooddonation.web.Converter.DoctorConverter;
import ro.blooddonation.web.Converter.RequestConverter;
import ro.blooddonation.web.Dto.EmptyJsonResponse;
import ro.blooddonation.web.Dto.RequestDto;
import ro.blooddonation.web.Dto.RequestsDto;

import java.util.*;

/**
 * 
 */
@RestController
public class RequestController implements IController<RequestDto, RequestsDto>
{


    private static final Logger log = LoggerFactory.getLogger(RequestController.class);

    @Autowired
    private RequestService requestService;

    @Autowired
    private RequestConverter requestConverter;

    @Autowired
    private DoctorConverter doctorConverter;


    @RequestMapping(value = "/requests", method = RequestMethod.POST)
    public RequestDto add(@RequestBody final RequestDto requestDto)
    {
        log.trace("addRequest: requestDtoMap={}", requestDto);

        Doctor doctor = doctorConverter.convertDtoToModel(requestDto.getDoctor());

        Request request = new Request(requestDto.getBlood(), requestDto.getUrgency(), doctor);
        request.setId(requestDto.getId());
        request.setUrgency(request.getUrgency());

        requestService.add(request);

        RequestDto result = requestConverter.convertModelToDto(request);

        log.trace("addRequest: result={}", result);

        return result;
    }


    @RequestMapping(value = "requests/{id}", method = RequestMethod.DELETE)
    public ResponseEntity remove(@PathVariable final Long id)
    {
        log.trace("removeRequest: id={}", id);

        requestService.remove(id);

        log.trace("removeRequest - method end");

        return new ResponseEntity(new EmptyJsonResponse(), HttpStatus.OK);
    }


    @RequestMapping(value = "/requests/{id}", method = RequestMethod.PUT)
    public RequestDto update(@PathVariable final Long id,
                            @RequestBody final RequestDto newRequestDto) {
        log.trace("updateRequest: id={}, requestDtoMap={}", id, newRequestDto);

        Doctor doctor = doctorConverter.convertDtoToModel(newRequestDto.getDoctor());
        Request r = new Request(newRequestDto.getBlood(), newRequestDto.getUrgency(), doctor);
        r.setUrgency(newRequestDto.getUrgency());
        r.setId(id);

        Optional<Request> request = requestService.update(id, r);

        Map<String, RequestDto> result = new HashMap<>();
        if (request.isPresent())
            result.put("request", requestConverter.convertModelToDto(request.get()));
        else
            result.put("request", requestConverter.convertModelToDto(new Request()));

        log.trace("updateRequest: result={}", result);

        return result.get("request");
    }


    @RequestMapping(value = "/requests", method = RequestMethod.GET)
    public RequestsDto getAll()
    {
        log.trace("getAllRequests");

        List<Request> requests = requestService.findAll();

        log.trace("getAllRequests: requests={}", requests);

        return new RequestsDto(requestConverter.convertModelsToDtos(requests));
    }
}