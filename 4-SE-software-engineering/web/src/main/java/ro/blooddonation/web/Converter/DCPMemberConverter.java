package ro.blooddonation.web.Converter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import ro.blooddonation.core.Domain.DCPMember;
import ro.blooddonation.web.Dto.DCPMemberDto;

@Component
public final class DCPMemberConverter extends BaseConverter<DCPMember, DCPMemberDto>
{
    private static final Logger log = LoggerFactory.getLogger(DCPMember.class);

    @Override
    public DCPMember convertDtoToModel(DCPMemberDto dto)
    {
        DoningCenterConverter dcConverter = new DoningCenterConverter();
        DCPMember dcpMember = new DCPMember(dto.getFirstName(), dto.getLastName(), dto.getBDay(),
                dto.getAddress(), dto.getResidence(), dto.getCNP(), null,
                dcConverter.convertDtoToModel(dto.getDoningCenter()));
        return dcpMember;
    }

    @Override
    public DCPMemberDto convertModelToDto(DCPMember dcpMember)
    {
        DoningCenterConverter dcConverter = new DoningCenterConverter();

        DCPMemberDto dcpMemberDto = new DCPMemberDto(dcConverter.convertModelToDto(dcpMember.getDoningCenter()));
        dcpMemberDto.setId(dcpMember.getId());
        dcpMemberDto.setFirstName(dcpMember.getFirstName());
        dcpMemberDto.setLastName(dcpMember.getLastName());
        dcpMemberDto.setAddress(dcpMember.getAddress());
        dcpMemberDto.setBDay(dcpMember.getbDay());
        dcpMemberDto.setCNP(dcpMember.getCNP());
        dcpMemberDto.setResidence(dcpMember.getResidence());
        return dcpMemberDto;
    }
}