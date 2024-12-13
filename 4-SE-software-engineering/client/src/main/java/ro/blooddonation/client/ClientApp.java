package ro.blooddonation.client;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.client.RestTemplate;
import ro.blooddonation.client.Console.Console;
import ro.blooddonation.web.Dto.HospitalDto;

public class ClientApp
{
    public static void main(String[] args)
    {
        Console console = new Console();
        console.run();
    }
}
