package ro.blooddonation.web.Converter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import ro.blooddonation.core.Domain.Doctor;
import ro.blooddonation.core.Domain.Patient;
import ro.blooddonation.web.Dto.PatientDto;

@Component
public class PatientConverter extends BaseConverter<Patient, PatientDto>
{
    private static final Logger log = LoggerFactory.getLogger(Patient.class);

    @Override
    public Patient convertDtoToModel(PatientDto dto) {
        DoctorConverter doctorConverter = new DoctorConverter();

        Patient p = new Patient(dto.getFirstName(), dto.getLastName(), dto.getBDay(), dto.getAddress(), dto.getResidence(),
                dto.getCNP(), null, doctorConverter.convertDtoToModel(dto.getDoctor()));
        p.setBlood(dto.getBlood());
        p.setRequestedBloodQuantity(dto.getRequestedBloodQuantity());
        p.setUrgency(dto.getUrgency());
        p.setId(dto.getId());

        return p;
    }

    @Override
    public PatientDto convertModelToDto(Patient patient) {
        DoctorConverter doctorConverter = new DoctorConverter() ;

        PatientDto patientDto = new PatientDto(doctorConverter.convertModelToDto(patient.getDoctor()),
                patient.getBlood(), patient.getRequestedBloodQuantity(), patient.getUrgency());
        patientDto.setId(patient.getId());
        patientDto.setFirstName(patient.getFirstName());
        patientDto.setLastName(patient.getLastName());
        patientDto.setAddress(patient.getAddress());
        patientDto.setResidence(patient.getResidence());
        patientDto.setBDay(patient.getbDay());
        patientDto.setCNP(patient.getCNP());
        //patientDto.setAccount(patient.getAccount());

        return patientDto;
    }
}
