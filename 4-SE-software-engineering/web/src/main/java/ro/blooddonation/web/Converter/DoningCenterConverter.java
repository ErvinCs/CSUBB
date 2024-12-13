package ro.blooddonation.web.Converter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import ro.blooddonation.core.Domain.DoningCenter;
import ro.blooddonation.web.Dto.DoningCenterDto;

@Component
public class DoningCenterConverter extends BaseConverter<DoningCenter, DoningCenterDto>
{
    private static final Logger log = LoggerFactory.getLogger(HospitalConverter.class);

    @Override
    public DoningCenter convertDtoToModel(DoningCenterDto dto)
    {
        DoningCenter dc = new DoningCenter(dto.getAddress());
        dc.setId(dto.getId());
        return dc;
    }

    @Override
    public DoningCenterDto convertModelToDto(DoningCenter dc)
    {
        DoningCenterDto dcDto = new DoningCenterDto(dc.getAddress());
        dcDto.setId(dc.getId());
        return dcDto;
    }
}
