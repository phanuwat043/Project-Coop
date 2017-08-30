
package com.java.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONException;
import org.json.JSONObject;

public class Server {

    public static void main(String[] args) throws IOException {
        ServerSocket sockServer = new ServerSocket(8888);
        System.out.println("Socket Server : " + sockServer);
        try {
            while (true) {
                Socket socket = sockServer.accept();
                startHandler(socket);
            }
        } finally {
            sockServer.close();
        }
    }

    private static void startHandler(final Socket socket) throws IOException {
        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    OutputStreamWriter writer = new OutputStreamWriter(socket.getOutputStream(), "UTF-8");
                    //System.out.println("writer: " + writer);
                    BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
                    //System.out.println("reader: " + reader);

                    String readLine = reader.readLine();
                    //System.out.println("readLine : " + readLine);

                    JSONObject json = new JSONObject(readLine);

                    writer.write(json.toString() + "\n");
                    writer.flush();

                    System.out.println("Receive form Client:\n" + json.toString());
                } catch (IOException ex) {
                    Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                } catch (JSONException ex) {
                    Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
                    closeSocket();
                }
            }

            private void closeSocket() {
                try {
                    socket.close();
                } catch (IOException ex) {
                }
            }
        };
        thread.start();
    }
}
