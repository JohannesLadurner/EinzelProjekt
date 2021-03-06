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
    TextView msgPrimes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sendButton = findViewById(R.id.BTNSend);
        msgForServer = findViewById(R.id.InputField);
        msgServer = findViewById(R.id.TVServerRespond);
        msgPrimes = findViewById(R.id.TVPrime);
    }

    public void BTNSend_Click(View v) throws InterruptedException {
        TCPClient client = new TCPClient(msgForServer.getText().toString());
        new Thread(client).start();
        Thread.sleep(100);
        msgServer.setText(client.getServerMessage());
        printAllPrimeDigits(msgForServer.getText().toString());
    }

    public void printAllPrimeDigits(String number)
    {
        String msg = "";
        for (int i = 0; i < number.length(); i++) {
            int digit = number.charAt(i) - '0';
            if(digit > 1 && isPrime(digit))
            {
                msg += digit;
            }
        }
        msgPrimes.setText("Prime Numbers: " + msg);
    }
    public boolean isPrime(int number)
    {
        for (int i = 2; i <= number/2; i++) {
            if(number % i == 0)
            {
                return false;
            }
        }
        return true;
    }

}
