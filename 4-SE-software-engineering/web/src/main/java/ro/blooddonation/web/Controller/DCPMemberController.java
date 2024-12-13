package ro.blooddonation.web.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.blooddonation.core.Domain.DCPMember;
import ro.blooddonation.core.Domain.DoningCenter;
import ro.blooddonation.core.Service.DCPMemberService;
import ro.blooddonation.web.Converter.DCPMemberConverter;
import ro.blooddonation.web.Dto.DCPMemberDto;
import ro.blooddonation.web.Dto.DCPMembersDto;
import ro.blooddonation.web.Dto.EmptyJsonResponse;

import java.util.*;

/**
 * 
 */
@RestController
public class DCPMemberController implements IController<DCPMemberDto, DCPMembersDto>
{

    private static final Logger log = LoggerFactory.getLogger(DCPMemberController.class);

    @Autowired
    private DCPMemberService dcpMemberService;

    @Autowired
    private DCPMemberConverter dcpMemberConverter;


    @RequestMapping(value = "/dcpmembers", method = RequestMethod.POST)
    public DCPMemberDto add(@RequestBody final DCPMemberDto dcpDto)
    {
        log.trace("addDCPMember: dcpMemberDtoMap={}", dcpDto);

        DoningCenter doningCenter = new DoningCenter(dcpDto.getAddress());

        DCPMember dcpMember = new DCPMember(dcpDto.getFirstName(), dcpDto.getLastName(), dcpDto.getBDay(), dcpDto.getAddress(),
                dcpDto.getResidence(), dcpDto.getCNP(),null, doningCenter);
        dcpMember.setId(dcpMember.getId());
        dcpMemberService.add(dcpMember);

        DCPMemberDto result = dcpMemberConverter.convertModelToDto(dcpMember);


        log.trace("addDCPMember: result={}", result);

        return result;
    }


    @RequestMapping(value = "dcpmembers/{id}", method = RequestMethod.DELETE)
    public ResponseEntity remove(@PathVariable final Long id)
    {
        log.trace("removeDCPMember: id={}", id);

        dcpMemberService.remove(id);

        log.trace("removeDCPMember - method end");

        return new ResponseEntity(new EmptyJsonResponse(), HttpStatus.OK);
    }


    @RequestMapping(value = "/dcpmembers/{id}", method = RequestMethod.PUT)
    public DCPMemberDto update(@PathVariable final Long id,
                              @RequestBody final DCPMemberDto newDcpDto) {
        log.trace("updateDCPMember: id={}, dcpMemberDtoMap={}", id, newDcpDto);
        DoningCenter doningCenter = new DoningCenter(newDcpDto.getAddress());

        DCPMember d = new DCPMember(newDcpDto.getFirstName(), newDcpDto.getLastName(), newDcpDto.getBDay(), newDcpDto.getAddress(),
                newDcpDto.getResidence(), newDcpDto.getCNP(),null, doningCenter);
        d.setId(id);

        Optional<DCPMember> dcpMember = dcpMemberService.update(id, d);

        Map<String, DCPMemberDto> result = new HashMap<>();
        if (dcpMember.isPresent())
            result.put("DCPMember", dcpMemberConverter.convertModelToDto(dcpMember.get()));
        else
            result.put("DCPMember", dcpMemberConverter.convertModelToDto(new DCPMember()));

        log.trace("updateDCPMember: result={}", result);

        return result.get("DCPMember");
    }


    @RequestMapping(value = "/dcpmembers", method = RequestMethod.GET)
    public DCPMembersDto getAll()
    {
        log.trace("getAllDCPMembers");

        List<DCPMember> dcpMembers = dcpMemberService.findAll();

        log.trace("getAllDCPMembers: DCPMembers={}", dcpMembers);

        return new DCPMembersDto(dcpMemberConverter.convertModelsToDtos(dcpMembers));
    }


}