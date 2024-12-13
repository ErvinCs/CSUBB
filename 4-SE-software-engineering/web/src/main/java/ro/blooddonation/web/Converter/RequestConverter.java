package ro.blooddonation.web.Converter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import ro.blooddonation.core.Domain.Request;
import ro.blooddonation.web.Dto.RequestDto;

@Component
public class RequestConverter extends BaseConverter<Request, RequestDto>
{
    private static final Logger log = LoggerFactory.getLogger(Request.class);

    @Override
    public Request convertDtoToModel(RequestDto dto)
    {
        DoctorConverter doctorConverter = new DoctorConverter();

        Request r = new Request(dto.getBlood(), dto.getUrgency(), doctorConverter.convertDtoToModel(dto.getDoctor()));
        r.setStatus(dto.getStatus());
        r.setId(dto.getId());

        return r;
    }

    @Override
    public RequestDto convertModelToDto(Request request)
    {
        DoctorConverter doctorConverter = new DoctorConverter();

        RequestDto requestDto = new RequestDto(request.getBlood(), request.getUrgency(), doctorConverter.convertModelToDto(request.getDoctor()),
                request.getStatus());
        requestDto.setId(request.getId());
        return requestDto;
    }
}
