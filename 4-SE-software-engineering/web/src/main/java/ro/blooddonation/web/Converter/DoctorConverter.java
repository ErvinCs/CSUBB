package ro.blooddonation.web.Converter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import ro.blooddonation.core.Domain.Doctor;
import ro.blooddonation.core.Domain.Hospital;
import ro.blooddonation.core.Domain.Patient;
import ro.blooddonation.web.Dto.DoctorDto;
import ro.blooddonation.web.Dto.PatientDto;
import ro.blooddonation.web.Dto.PatientsDto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class DoctorConverter extends BaseConverter<Doctor, DoctorDto>
{
    private static final Logger log = LoggerFactory.getLogger(Doctor.class);

    @Override
    public Doctor convertDtoToModel(DoctorDto dto)
    {
        HospitalConverter hospitalConverter = new HospitalConverter();
        PatientConverter patientConverter = new PatientConverter();

        Hospital hospital = hospitalConverter.convertDtoToModel(dto.getHospital());

        List<Patient> patients = new ArrayList<>();
        Set<PatientDto> patientDtos = dto.getPatients().getPatients();
        //if (patientDtos.size() > 0 && patientDtos != null)
            patientDtos.forEach(p -> {
                patients.add(patientConverter.convertDtoToModel(p));
            });

        Doctor doctor = new Doctor(dto.getFirstName(), dto.getLastName(), dto.getBDay(), dto.getAddress(), dto.getResidence(),
                dto.getCNP(), null, hospital, patients);
        doctor.setId(dto.getId());

        return doctor;
    }

    @Override
    public DoctorDto convertModelToDto(Doctor doctor)
    {
        HospitalConverter hospitalConverter = new HospitalConverter();
        PatientConverter patientConverter = new PatientConverter();

        Set<PatientDto> patientDtos = new HashSet<>();
        List<Patient> patients = doctor.getPatients();
        //if (patients.size() > 0 && patients != null)
            patients.forEach(patient -> {
                patientDtos.add(patientConverter.convertModelToDto(patient));
            });

        PatientsDto patientsDto = new PatientsDto(patientDtos);
        DoctorDto doctorDto = new DoctorDto(hospitalConverter.convertModelToDto(doctor.getHospital()), patientsDto);
        doctorDto.setId(doctor.getId());
        doctorDto.setFirstName(doctor.getFirstName());
        doctorDto.setLastName(doctor.getLastName());
        doctorDto.setAddress(doctor.getAddress());
        doctorDto.setBDay(doctor.getbDay());
        doctorDto.setCNP(doctor.getCNP());
        doctorDto.setResidence(doctor.getResidence());
        return doctorDto;
    }
}
