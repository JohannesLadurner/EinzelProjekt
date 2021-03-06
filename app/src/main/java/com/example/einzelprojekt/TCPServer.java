package com.example.einzelprojekt;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer implements Runnable{
    String clientSentence;
    ServerSocket welcomeSocket;

    public TCPServer() throws IOException {
        welcomeSocket = new ServerSocket(53212);
    }

    @Override
    public void run() {
        try {
            Socket connectionSocket = welcomeSocket.accept();
            BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
            DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
            clientSentence = inFromClient.readLine();
            outToClient.writeBytes(clientSentence);
            System.out.println("RUN");
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    /*
    public static void main(String[] args) throws IOException {
        String clientSentence;
        ServerSocket welcomeSocket = new ServerSocket(53212);

        while(true){
            Socket connectionSocket = welcomeSocket.accept();
            BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
            DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
            clientSentence = inFromClient.readLine();
            outToClient.writeBytes(clientSentence);
        }
    }

     */

}
