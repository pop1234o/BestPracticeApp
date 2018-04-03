package com.liyafeng.network.socket;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by liyafeng on 2018/4/3.
 */

public class SocketClient {

    /**
     * http://wiki.jikexueyuan.com/project/java-socket/tcp.html
     *
     * @param args
     */
    public static void main(String[] args) {
        try {
            Socket client = new Socket("127.0.0.1", 8899);
            client.setSoTimeout(10000);
            // auto flush
            PrintStream printStream = new PrintStream(client.getOutputStream());
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("begin!");
            while (true) {
                String s = reader.readLine();
                if ("bye".equals(s)) {
                    System.out.println("exit");
                    break;
                } else {
                    System.out.println("input:" + s);
                    printStream.println(s);
                }
            }
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
