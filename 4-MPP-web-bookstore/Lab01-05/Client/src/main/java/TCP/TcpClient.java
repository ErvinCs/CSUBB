package TCP;

import Service.HandlerService;
import Service.HandlerServiceException;
import Service.Message;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import jdk.internal.util.xml.impl.Input;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.concurrent.ExecutorService;

public class TcpClient
{
    private ExecutorService executorService;
    private String serverHost;
    private int serverPort;

    public TcpClient(ExecutorService executorService, String serverHost, int serverPort)
    {
        this.executorService = executorService;
        this.serverHost = serverHost;
        this.serverPort = serverPort;
    }

    public Message sendAndReceive(Message request)
    {
        try(Socket socket = new Socket(serverHost, serverPort);
            OutputStream os = socket.getOutputStream();
            InputStream is = socket.getInputStream())
        {
            request.writeTo(os);
            System.out.println("Client - sending request: " + request);

            Message response = Message.builder().build();
            response.readFrom(is);
            System.out.println("Client - received response: " + response);

            return response;
        }
        catch (IOException e)
        {
            e.printStackTrace();
            throw new HandlerServiceException(e);
        }
    }
}
