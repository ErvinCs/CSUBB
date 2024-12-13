package Service;


import java.util.concurrent.Future;

public interface HandlerService<T>
{
    String SERVER_HOST = "localhost";
    int SERVER_PORT = 1234;

    Future<String> processMessage(String body);

}
