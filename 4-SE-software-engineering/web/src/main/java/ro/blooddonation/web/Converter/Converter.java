package ro.blooddonation.web.Converter;

import ro.blooddonation.core.Domain.BaseEntity;
import ro.blooddonation.web.Dto.BaseDto;


public interface Converter<Model extends BaseEntity<Long>, Dto extends BaseDto> {

    Model convertDtoToModel(Dto dto);

    Dto convertModelToDto(Model model);

}

