package com.example.formation12.firstappandroid;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {


    Button info;
    private TextView textView;
    private boolean remiseAzero = true;
    private String number1 = "", number2 = "", operation = "";
    private boolean isOperationClicked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d("formation", "Création de MainActivity");

        View.OnClickListener onDigitClicked = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addDigitToCurrentValue(v);
            }
        };

        View.OnClickListener onOperationClicked = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button clickedButton = (Button) v;
                chooseOperator(clickedButton.getText().toString());
            }
        };
        View.OnClickListener onEqualClicked = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateTotal();
            }
        };

        findViewById(R.id.button_zero).setOnClickListener(onDigitClicked);
        findViewById(R.id.button_one).setOnClickListener(onDigitClicked);
        findViewById(R.id.button_two).setOnClickListener(onDigitClicked);
        findViewById(R.id.button_three).setOnClickListener(onDigitClicked);
        findViewById(R.id.button_four).setOnClickListener(onDigitClicked);
        findViewById(R.id.button_five).setOnClickListener(onDigitClicked);
        findViewById(R.id.button_six).setOnClickListener(onDigitClicked);
        findViewById(R.id.button_seven).setOnClickListener(onDigitClicked);
        findViewById(R.id.button_height).setOnClickListener(onDigitClicked);
        findViewById(R.id.button_nine).setOnClickListener(onDigitClicked);
        findViewById(R.id.button_add).setOnClickListener(onOperationClicked);
        findViewById(R.id.button_sub).setOnClickListener(onOperationClicked);
        findViewById(R.id.button_div).setOnClickListener(onOperationClicked);
        findViewById(R.id.button_mult).setOnClickListener(onOperationClicked);
        findViewById(R.id.button_dot).setOnClickListener(onDigitClicked);
        findViewById(R.id.button_equal).setOnClickListener(onEqualClicked);
        textView = findViewById(R.id.textView);
        info = (Button)findViewById(R.id.button_info);

        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, InfoActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });


    }

    private void calculateTotal() {

        double res = 0;
        switch (operation) {
            case "+":   res =  addition();
                        break;
            case "/":   res =  division();
                        break;
            case "*":   res =  multiplication();
                        break;
            case "-":   res =  substraction();
                        break;
        }
        number1 = String.format(Locale.getDefault(),"%.3f",res);
        textView.setText(number1);
        number2 = "";
        operation = "";
        isOperationClicked = false;
    }

    private void chooseOperator(String operator) {
        if (isOperationClicked) {
            switch (operation) {
                case "+":   executeOperation(addition());
                            break;
                case "/":   executeOperation(division());
                            break;
                case "*":   executeOperation(multiplication());
                            break;
                case "-":   executeOperation(substraction());
                            break;
            }
            operation = "";
            isOperationClicked = false;
        }
        operation = operator;
        isOperationClicked = true;
        textView.setText(textView.getText() + operation);
        cancelButtonOperation(false);
    }


    public void addDigitToCurrentValue(View v) {
        Button tappedButton = (Button) v;
        String contentTapped = tappedButton.getText().toString();
        cancelButtonOperation(true);
        if (remiseAzero) {
            number1 = contentTapped;
            textView.setText(number1);
            remiseAzero = false;
        } else {
            if (isOperationClicked) {
                number2 += contentTapped;
                textView.setText(number1 + operation + number2);
            } else {
                number1 += contentTapped;
                textView.setText(number1);
            }
        }
    }

    private void executeOperation(double res) {

        number2 = "";
        isOperationClicked = false;
        number1 = String.format(Locale.getDefault(),"%.3f",res);

        textView.setText(number1);
    }

    private double addition() {
        double numberA = Double.parseDouble(number1);
        double numberB = Double.parseDouble(number2);
        return numberA + numberB;
    }

    private double substraction() {
        double numberA = Double.parseDouble(number1);
        double numberB = Double.parseDouble(number2);
        return numberA - numberB;
    }

    private double division() {
        double numberA = Double.parseDouble(number1);
        double numberB = Double.parseDouble(number2);
        return numberA / numberB;
    }

    private double multiplication() {
        double numberA = Double.parseDouble(number1);
        double numberB = Double.parseDouble(number2);
        return numberA * numberB;
    }

    private void cancelButtonOperation(boolean bool) {
        findViewById(R.id.button_add).setEnabled(bool);
        findViewById(R.id.button_sub).setEnabled(bool);
        findViewById(R.id.button_mult).setEnabled(bool);
        findViewById(R.id.button_div).setEnabled(bool);
        findViewById(R.id.button_dot).setEnabled(bool);
        findViewById(R.id.button_equal).setEnabled(bool);
    }


}

