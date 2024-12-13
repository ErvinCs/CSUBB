package Service;

import java.io.*;

public class Message implements Serializable {

    public static final String OK = "ok";
    public static final String ERROR = "error";

    private static final String LINE_SEPARATOR = System.getProperty("line.separator");

    private String header;
    private String body;

    public Message(String header, String body)
    {
        this.header = header;
        this.body = body;
    }

    public static MessageBuilder builder() {return new MessageBuilder();}

    public String getHeader() {return header;}

    public String getBody() {return body;}

    public void writeTo(OutputStream os) throws IOException
    {
        os.write((header + LINE_SEPARATOR + body + LINE_SEPARATOR).getBytes());
    }

    public void readFrom(InputStream is) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        header = br.readLine();
        body = br.readLine();
    }

    @Override
    public String toString() {
        return "Message {" + "header = '" + header + '\'' + ", body = '" + body + '\'' + '}';
    }

    public static class MessageBuilder
    {
        private String header;
        private String body;

        public MessageBuilder header(String header)
        {
            this.header = header;
            return this;
        }

        public MessageBuilder body(String body)
        {
            this.body = body;
            return this;
        }

        public Message build() {return new Message(header, body);}
    }
}
