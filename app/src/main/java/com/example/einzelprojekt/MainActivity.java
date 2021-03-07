package com.example.einzelprojekt;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText msgForServer;
    TextView msgServer;
    TextView msgPrimes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        msgForServer = findViewById(R.id.InputField);
        msgServer = findViewById(R.id.TVServerRespond);
        msgPrimes = findViewById(R.id.TVPrime);
    }

    /**
     * This method will be called when the button on the app got pressed.
     * @param v
     * @throws InterruptedException
     */
    public void BTNSend_Click(View v) throws InterruptedException {
        String matrikelnr = msgForServer.getText().toString();
        TCPClient client = new TCPClient(matrikelnr);
        client.start(); //Start the Thread (run() will be executed)
        client.join(); //Wait for server to finish
        msgServer.setVisibility(View.VISIBLE);
        msgServer.setText(client.getServerMessage()); //Get respond from server, print it on the app
    }

    /**
     * This method gets called when Prime Button gets called
     * @param v
     */
    public void BTNPrime_Click(View v) {
        String matrikelnr = msgForServer.getText().toString();
        printAllPrimeDigits(matrikelnr);
    }

    /**
     * This method gets every character from the string number, and checks if it is prime.
     * In case it is, it gets added to a new String, which will be printed on the app
     * @param number
     */
    public void printAllPrimeDigits(String number)
    {
        String msg = "";
        for (int i = 0; i < number.length(); i++) {
            int digit = number.charAt(i) - '0'; //Get current digit
            if(digit > 1 && isPrime(digit)) //Numbers < 2 are not prime, check if it is a prime number
            {
                msg += digit; //Add it to the result string
            }
        }
        msgPrimes.setVisibility(View.VISIBLE);
        msgPrimes.setText("Primzahlen: " + msg); //Set text for the app
    }

    /**
     * This method checks if a number is prime.
     * It can only be prime, if no number from 2 to number/2 can divide it with no remainder
     * @param number
     * @return
     */
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
