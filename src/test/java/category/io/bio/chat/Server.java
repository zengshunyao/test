package category.io.bio.chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    final static int PORT = 8765;


    public static void main(String[] args) {
        ServerSocket server = null;
        BufferedReader in = null;
        PrintWriter out = null;
        try {

            server = new ServerSocket(PORT);

            System.out.println("server start，listen " + PORT);

            Socket socket = null;

            HandlerExecutorPool executorPool = new HandlerExecutorPool(50, 1000);

            while (true) {

                socket = server.accept();

                executorPool.execute(new ServerHandler(socket));

            }

        } catch (Exception e) {

            e.printStackTrace();

        } finally {

            if (in != null) {

                try {

                    in.close();

                } catch (Exception e1) {

                    e1.printStackTrace();

                }

            }

            if (out != null) {

                try {

                    out.close();

                } catch (Exception e2) {

                    e2.printStackTrace();

                }

            }

            if (server != null) {

                try {

                    server.close();

                } catch (Exception e3) {

                    e3.printStackTrace();

                }

            }

            server = null;

        }

    }
}


class ServerHandler implements Runnable {

    private Socket socket;


    public ServerHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        BufferedReader in = null;
        PrintWriter out = null;
        try {
            in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            out = new PrintWriter(this.socket.getOutputStream(), true);
            String body = null;
            while (true) {
                body = in.readLine();
                if (body == null)
                    break;
                System.out.println(System.currentTimeMillis() +Thread.currentThread().getName()+ " Server get from client:" + body);
                out.println("服务器端回送响的应数据." + body);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (out != null) {
                try {
                    out.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            socket = null;
        }
    }
}