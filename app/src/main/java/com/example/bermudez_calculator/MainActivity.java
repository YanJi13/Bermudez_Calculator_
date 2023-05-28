package com.example.bermudez_calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView textView;
    private StringBuilder equationBuilder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        equationBuilder = new StringBuilder();

        setNumberButtonListeners();
        setFunctionButtonListeners();
    }

    private void setNumberButtonListeners() {
        int[] numberButtonIds = {
                R.id.numButton_0, R.id.numButton_1, R.id.numButton_2,
                R.id.numButton_3, R.id.numButton_4, R.id.numButton_5,
                R.id.numButton_6, R.id.numButton_7, R.id.numButton_8,
                R.id.numButton_9, R.id.numButton_dot
        };

        for (int buttonId : numberButtonIds) {
            findViewById(buttonId).setOnClickListener(this);
        }
    }

    private void setFunctionButtonListeners() {
        int[] functionButtonIds = {
                R.id.funcButton_Add, R.id.funcButton_Min,
                R.id.funcButton_Multiply, R.id.funcButton_Division,
                R.id.funcButton_Equals, R.id.funcButton_Clear,
                R.id.funcButton_Back
        };

        for (int buttonId : functionButtonIds) {
            findViewById(buttonId).setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        Button button = (Button) v;
        String buttonText = button.getText().toString();

        switch (v.getId()) {
            case R.id.funcButton_Equals:
                calculateResult();
                break;
            case R.id.funcButton_Clear:
                clearEquation();
                break;
            case R.id.funcButton_Back:
                removeLastCharacter();
                break;
            default:
                appendToEquation(buttonText);
                break;
        }
    }

    private void appendToEquation(String text) {
        equationBuilder.append(text);
        textView.setText(equationBuilder.toString());
    }

    private void removeLastCharacter() {
        if (equationBuilder.length() > 0) {
            equationBuilder.deleteCharAt(equationBuilder.length() - 1);
            textView.setText(equationBuilder.toString());
        }
    }

    private void clearEquation() {
        equationBuilder.setLength(0);
        textView.setText("");
    }

    private void calculateResult() {
        String equation = equationBuilder.toString().trim();

        try {
            double result = 0;

            // Find the operator and split the equation into operands
            if (equation.contains("+")) {
                String[] operands = equation.split("\\+");
                double operand1 = Double.parseDouble(operands[0]);
                double operand2 = Double.parseDouble(operands[1]);
                result = operand1 + operand2;
            } else if (equation.contains("-")) {
                String[] operands = equation.split("-");
                double operand1 = Double.parseDouble(operands[0]);
                double operand2 = Double.parseDouble(operands[1]);
                result = operand1 - operand2;
            } else if (equation.contains("X")) {
                String[] operands = equation.split("X");
                double operand1 = Double.parseDouble(operands[0]);
                double operand2 = Double.parseDouble(operands[1]);
                result = operand1 * operand2;
            } else if (equation.contains("÷")) {
                String[] operands = equation.split("÷");
                double operand1 = Double.parseDouble(operands[0]);
                double operand2 = Double.parseDouble(operands[1]);
                result = operand1 / operand2;
            }

            textView.setText(Double.toString(result));
        } catch (Exception e) {
            textView.setText("Error");
        }
    }
}