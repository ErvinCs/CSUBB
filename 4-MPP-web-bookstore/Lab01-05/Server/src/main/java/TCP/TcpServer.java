package TCP;

import Domain.Book;
import Domain.Client;
import Domain.Rental;
import Service.HandlerServiceException;
import Service.Message;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.function.UnaryOperator;

public class TcpServer {

    private ExecutorService executorService;
    private String serverHost;
    private int serverPort;

    private ServerService service;

    public TcpServer(ExecutorService executorService, ServerService service, String serverHost, int serverPort)
    {
        this.executorService = executorService;
        this.serverHost = serverHost;
        this.serverPort = serverPort;
        this.service = service;
    }

    public void startServer()
    {
        try{
            ServerSocket serverSocket = new ServerSocket(serverPort);
            System.out.println("Server started - waiting for clients");

            while(true)
            {
                Socket client = serverSocket.accept();
                System.out.println("Client connected");

                executorService.submit(new ClientHandler(client));
            }
        }catch (IOException e)
        {
            e.printStackTrace();
            throw new HandlerServiceException(e);
        }
    }

    private class ClientHandler implements  Runnable
    {
        private Socket socket;

        ClientHandler(Socket socket) throws IOException
        {
            this.socket = socket;
        }

        @Override
        public void run() {
            try(OutputStream os = socket.getOutputStream();
                InputStream is = socket.getInputStream())
            {
                Message request = Message.builder().build();
                request.readFrom(is);
                System.out.println("Server - received request: " + request);
                
                Message response = service.getHandler(request.getHeader()).apply(request);
                System.out.println("Server - computed response: " + response);

                response.writeTo(os);
            }catch (IOException e)
            {
                e.printStackTrace();
                throw new HandlerServiceException(e);
            }finally {
                if(socket != null)
                    try{
                    socket.close();
                    }catch (IOException e)
                    {
                        e.printStackTrace();
                    }
            }
        }
    }

}
