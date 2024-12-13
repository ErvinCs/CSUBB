package ro.blooddonation.client.Console;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ro.blooddonation.core.Domain.*;
import ro.blooddonation.core.Service.HospitalService;
import ro.blooddonation.web.Converter.HospitalConverter;
import ro.blooddonation.web.Dto.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

@Component
public class Console
{
    private String httpString = "http://localhost:8080/api/";

    AnnotationConfigApplicationContext context =
            new AnnotationConfigApplicationContext("ro.blooddonation.client.Config");

    RestTemplate restTemplate = context.getBean(RestTemplate.class);

    public void run()
    {
//        PatientDto pDto = restTemplate.postForObject("http://localhost:8080/api/patients",
//                new PatientDto(), PatientDto.class);
//        System.out.println("PostPatient:\n" + pDto.toString());
//
//        PatientsDto pDtos = restTemplate.getForObject("http://localhost:8080/api/patients", PatientsDto.class);
//        System.out.println("GetPatients:");
//        pDtos.getPatients()
//                .forEach(p -> System.out.println(p.toString()));

        List<Doctor> doctorList = new ArrayList<>();
//        doctorList.add(new Doctor());
//        Hospital h = new Hospital("Address", doctorList);
//        System.out.println("Hospital=" + h.toString());

//        HospitalConverter hospitalConverter = new HospitalConverter();
//        HospitalDto hDto = hospitalConverter.convertModelToDto(h);
//        System.out.println("HospitalDto=" + hDto.toString());
//        HospitalService hospitalService = (HospitalService)context.getBean("hospitalService");
//        hospitalService.add(new Hospital("Address", doctorList));
//        System.out.println(hospitalService.findAll().toString());
        HospitalDto hDto = restTemplate.postForObject("http://localhost:8080/api/hospitals",
                 new HospitalDto("Address", new DoctorsDto()), HospitalDto.class);
        System.out.println("PostHospital: " + hDto.toString());

        HospitalsDto hDtos = restTemplate.getForObject("http://localhost:8080/api/hospitals", HospitalsDto.class);
        //System.out.println("GetHospitals:");
        //hDtos.getHospitals()
        //        .forEach(h -> System.out.println(h.toString()));


//        DoningCenterDto dcDto = restTemplate.postForObject("http://localhost:8080/api/doningCenters",
//                new DoningCenterDto("TestAddress2"), DoningCenterDto.class);
//        System.out.println("PostDC:" + dcDto.toString());
//
//        DoningCentersDto dcsDto = restTemplate.getForObject("http://localhost:8080/api/doningCenters", DoningCentersDto.class);
//        System.out.println("GetDCs:");
//        dcsDto.getDoningCenters()
//                .forEach(System.out::println);

//        Long cnp = Long.valueOf("19711120000000");
//        LocalDate bday = LocalDate.of(2005, 10, 10);
//        DoctorDto doctorDto = restTemplate.postForObject("http://localhost:8080/api/doctors",
//                new DoctorDto(hDtos.getHospitals().iterator().next(), pDtos),
//                DoctorDto.class);
//        DoctorsDto doctorsDto = restTemplate.getForObject("http://localhost:8080/api/doctors", DoctorsDto.class);
//        doctorsDto.getDoctors()
//                .forEach(System.out::println);

    }
}
