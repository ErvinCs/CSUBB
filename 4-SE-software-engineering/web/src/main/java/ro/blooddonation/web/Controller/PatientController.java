package ro.blooddonation.web.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.blooddonation.core.Domain.Doctor;
import ro.blooddonation.core.Domain.Patient;
import ro.blooddonation.core.Service.PatientService;
import ro.blooddonation.web.Converter.DoctorConverter;
import ro.blooddonation.web.Converter.PatientConverter;
import ro.blooddonation.web.Dto.EmptyJsonResponse;
import ro.blooddonation.web.Dto.PatientDto;
import ro.blooddonation.web.Dto.PatientsDto;

import java.util.*;

/**
 * 
 */
@RestController
public class PatientController implements IController<PatientDto, PatientsDto>
{


    private static final Logger log = LoggerFactory.getLogger(PatientController.class);

    @Autowired
    private PatientService patientService;

    @Autowired
    private PatientConverter patientConverter;

    @Autowired
    private DoctorConverter doctorConverter;


    @RequestMapping(value = "/patients", method = RequestMethod.POST)
    public PatientDto add(@RequestBody final PatientDto patientDto)
    {
        log.trace("addPatient: patientDtoMap={}", patientDto);

        Doctor doctor = doctorConverter.convertDtoToModel(patientDto.getDoctor());

        Patient patient = new Patient(
                patientDto.getFirstName(), patientDto.getLastName(), patientDto.getBDay(),
                patientDto.getAddress(), patientDto.getResidence(), patientDto.getCNP(),
                null, doctor);
        patient.setId(patientDto.getId());
        patient.setBlood(patientDto.getBlood());
        patient.setRequestedBloodQuantity(patientDto.getRequestedBloodQuantity());
        patient.setUrgency(patientDto.getUrgency());

        patientService.add(patient);

        PatientDto result = patientConverter.convertModelToDto(patient);

        log.trace("addPatient: result={}", result);

        return result;
    }


    @RequestMapping(value = "patients/{id}", method = RequestMethod.DELETE)
    public ResponseEntity remove(@PathVariable final Long id)
    {
        log.trace("removePatient: id={}", id);

        patientService.remove(id);

        log.trace("removePatient - method end");

        return new ResponseEntity(new EmptyJsonResponse(), HttpStatus.OK);
    }


    @RequestMapping(value = "/patients/{id}", method = RequestMethod.PUT)
    public PatientDto update(@PathVariable final Long id,
                            @RequestBody final PatientDto newPatientDto) {
        log.trace("updatePatient: id={}, patientDtoMap={}", id, newPatientDto);

        Doctor doctor = doctorConverter.convertDtoToModel(newPatientDto.getDoctor());

        Patient p = new Patient(
                newPatientDto.getFirstName(), newPatientDto.getLastName(), newPatientDto.getBDay(),
                newPatientDto.getAddress(), newPatientDto.getResidence(), newPatientDto.getCNP(),
                null, doctor );
        p.setId(id);
        p.setBlood(newPatientDto.getBlood());
        p.setRequestedBloodQuantity(newPatientDto.getRequestedBloodQuantity());
        p.setUrgency(newPatientDto.getUrgency());

        Optional<Patient> patient = patientService.update(id, p);

        Map<String, PatientDto> result = new HashMap<>();
        if (patient.isPresent())
            result.put("patient", patientConverter.convertModelToDto(patient.get()));
        else
            result.put("patient", patientConverter.convertModelToDto(new Patient()));

        log.trace("updatePatient: result={}", result);

        return result.get("patient");
    }


    @RequestMapping(value = "/patients", method = RequestMethod.GET)
    public PatientsDto getAll()
    {
        log.trace("getAllPatients");

        List<Patient> patients = patientService.findAll();

        log.trace("getAllPatient: patientS={}", patients);

        return new PatientsDto(patientConverter.convertModelsToDtos(patients));
    }


}