package com.example.einzelprojekt;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class TCPClient extends Thread implements Runnable{

    String message;
    String returnValue;
    public TCPClient(String message) {
        this.message = message;
    }

    @Override
    public void run() {
        try {
            Socket clientSocket = new Socket("se2-isys.aau.at", 53212); //Connection with socket
            DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
            BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            outToServer.writeBytes(message + '\n'); //Send message to server
            returnValue = inFromServer.readLine(); //Get response from server
            clientSocket.close(); //Close the socket
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public String getServerMessage()
    {
        return this.returnValue;
    }
}



