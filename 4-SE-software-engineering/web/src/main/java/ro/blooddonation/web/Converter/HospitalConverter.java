package ro.blooddonation.web.Converter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import ro.blooddonation.core.Domain.Doctor;
import ro.blooddonation.core.Domain.Hospital;
import ro.blooddonation.web.Dto.DoctorDto;
import ro.blooddonation.web.Dto.DoctorsDto;
import ro.blooddonation.web.Dto.HospitalDto;

import java.util.ArrayList;
import java.util.List;

@Component
public class HospitalConverter extends BaseConverter<Hospital, HospitalDto>
{
    private static final Logger log = LoggerFactory.getLogger(HospitalConverter.class);

    private static final DoctorConverter doctorConverter = new DoctorConverter();

    @Override
    public Hospital convertDtoToModel(HospitalDto dto)
    {
        DoctorsDto doctorDtos = dto.getDoctors();
        List<Doctor> doctors = new ArrayList<>();
        doctorDtos.getDoctors().forEach(doc -> doctors.add(doctorConverter.convertDtoToModel(doc)));

        Hospital hospital = new Hospital(dto.getAddress(), doctors);
        hospital.setId(dto.getId());
        return hospital;
    }

    @Override
    public HospitalDto convertModelToDto(Hospital hospital) {

        List<Doctor> doctors = hospital.getDoctors();
        DoctorsDto doctorDtos = new DoctorsDto();
        doctors.forEach(doc -> doctorDtos.getDoctors().add(doctorConverter.convertModelToDto(doc)));

        HospitalDto hospitalDto = new HospitalDto(hospital.getAddress(), doctorDtos);
        hospitalDto.setId(hospital.getId());
        return hospitalDto;
    }
}
