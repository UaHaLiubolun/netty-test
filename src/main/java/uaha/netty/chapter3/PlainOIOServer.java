package uaha.netty.chapter3;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;

public class PlainOIOServer {

    public void service(int port) throws Exception {
        final ServerSocket socket = new ServerSocket(port);
        try {
            for (;;) {
                final Socket s = socket.accept();
                System.out.println("Accepted connection from " + s);
                new Thread(() -> {
                    OutputStream out;
                    try {
                        out = s.getOutputStream();
                        out.write("Hi!".getBytes(Charset.forName("UTF-8")));
                        out.flush();
                        s.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            s.close();
                        } catch (IOException e) {}
                    }
                }).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
