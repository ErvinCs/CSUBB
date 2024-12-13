package ro.blooddonation.web.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.blooddonation.core.Domain.Doctor;
import ro.blooddonation.core.Domain.Hospital;
import ro.blooddonation.core.Domain.Patient;
import ro.blooddonation.core.Service.DoctorService;
import ro.blooddonation.web.Converter.DoctorConverter;
import ro.blooddonation.web.Converter.PatientConverter;
import ro.blooddonation.web.Dto.DoctorDto;
import ro.blooddonation.web.Dto.DoctorsDto;
import ro.blooddonation.web.Dto.EmptyJsonResponse;

import java.util.*;

/**
 * 
 */
@RestController
public class DoctorController implements IController<DoctorDto, DoctorsDto>
{

    private static final Logger log = LoggerFactory.getLogger(DoctorController.class);

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private DoctorConverter doctorConverter;

    @Autowired
    private PatientConverter patientConverter;


    @RequestMapping(value = "/doctors", method = RequestMethod.POST)
    public DoctorDto add(@RequestBody final DoctorDto doctorDto)
    {
        log.trace("addDoctor: doctorDtoMap={}", doctorDto);

        Hospital hospital = new Hospital(doctorDto.getHospital().getAddress());
        hospital.setId(doctorDto.getHospital().getId());

        List<Patient> patients = new ArrayList<>();
        doctorDto.getPatients().getPatients().forEach(p -> {
            patients.add(patientConverter.convertDtoToModel(p));
        });

        Doctor doctor = new Doctor(
                doctorDto.getFirstName(), doctorDto.getLastName(), doctorDto.getBDay(),
                doctorDto.getAddress(), doctorDto.getResidence(), doctorDto.getCNP(),
                null, hospital, patients
        );
        doctor.setId(doctorDto.getId());
        doctorService.add(doctor);

        DoctorDto result = doctorConverter.convertModelToDto(doctor);

        log.trace("addDoctor: result={}", result);

        return result;
    }


    @RequestMapping(value = "doctors/{id}", method = RequestMethod.DELETE)
    public ResponseEntity remove(@PathVariable final Long id)
    {
        log.trace("removeDoctor: id={}", id);

        doctorService.remove(id);

        log.trace("removeDoctor - method end");

        return new ResponseEntity(new EmptyJsonResponse(), HttpStatus.OK);
    }


    @RequestMapping(value = "/doctors/{id}", method = RequestMethod.PUT)
    public DoctorDto update(@PathVariable final Long id,
                              @RequestBody final DoctorDto newDoctorDto) {
        log.trace("updateDoctor: id={}, doctorDtoMap={}", id, newDoctorDto);

        Hospital hospital = new Hospital(newDoctorDto.getHospital().getAddress());
        hospital.setId(newDoctorDto.getHospital().getId());

        List<Patient> patients = new ArrayList<>();
        newDoctorDto.getPatients().getPatients().forEach(p -> {
            patients.add(patientConverter.convertDtoToModel(p));
        });

        Doctor d = new Doctor(
                newDoctorDto.getFirstName(), newDoctorDto.getLastName(), newDoctorDto.getBDay(),
                newDoctorDto.getAddress(), newDoctorDto.getResidence(), newDoctorDto.getCNP(),
               null, hospital, patients
        );
        d.setId(id);

        Optional<Doctor> doctor = doctorService.update(id, d);

        Map<String, DoctorDto> result = new HashMap<>();
        if (doctor.isPresent())
            result.put("doctor", doctorConverter.convertModelToDto(doctor.get()));
        else
            result.put("doctor", doctorConverter.convertModelToDto(new Doctor()));

        log.trace("updateDoctor: result={}", result);

        return result.get("doctor");
    }


    @RequestMapping(value = "/doctors", method = RequestMethod.GET)
    public DoctorsDto getAll()
    {
        log.trace("getAllDoctors");

        List<Doctor> doctors = doctorService.findAll();

        log.trace("getAllDoctors: doctors={}", doctors);

        return new DoctorsDto(doctorConverter.convertModelsToDtos(doctors));
    }
}