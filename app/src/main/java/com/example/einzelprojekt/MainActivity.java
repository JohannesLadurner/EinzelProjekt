package com.example.einzelprojekt;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button sendButton;
    EditText msgForServer;
    TextView msgServer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sendButton = findViewById(R.id.BTNSend);
        msgForServer = findViewById(R.id.InputField);
        msgServer = findViewById(R.id.TVServerRespond);
    }

    public void BTNSend_Click(View v) throws InterruptedException {
        TCPClient client = new TCPClient(msgForServer.getText().toString());
        new Thread(client).start();
        Thread.sleep(100);
        msgServer.setText(client.getServerMessage());
    }

}
