
import Service.HandlerService;
import TCP.ClientService;
import TCP.TcpClient;
import UI.ClientConsole;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ClientApp {
    public static void main(String args[])
    {
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        TcpClient tcpClient = new TcpClient(executorService, HandlerService.SERVER_HOST, HandlerService.SERVER_PORT);
        ClientService handlerService = new ClientService(executorService,tcpClient);
        ClientConsole clientConsole = new ClientConsole(handlerService);
        clientConsole.runConsole();

        executorService.shutdown();
    }
}

