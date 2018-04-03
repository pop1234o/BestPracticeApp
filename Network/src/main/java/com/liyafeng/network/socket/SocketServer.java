package com.liyafeng.network.socket;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by liyafeng on 2018/4/3.
 */

public class SocketServer {

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(8899);
            // wait connect one device
            Socket accept = serverSocket.accept();
            BufferedReader reader = new BufferedReader(new InputStreamReader(accept.getInputStream()));
            System.out.println("connect device!" + accept.getInetAddress().getHostName());
            while (true) {
//                byte[] bytes = new byte[1024];
//                int length = 0;
                String s = reader.readLine();
                if ("bye".equals(s)) {
                    System.out.println("exit");
                    break;
                } else {
                    System.out.println("echo: " + s);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
