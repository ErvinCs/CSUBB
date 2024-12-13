package TCP;

import Service.Message;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;

public class ClientService {

    private ExecutorService executorService;
    private TcpClient tcpClient;

    public ClientService(ExecutorService executorService, TcpClient tcpClient)
    {
        this.executorService = executorService;
        this.tcpClient = tcpClient;
    }

    public CompletableFuture<Message> processMessage(Message request)
    {
        return CompletableFuture.supplyAsync(()-> {
            Message response = tcpClient.sendAndReceive(request);
            return response;
        });
    }
}
