package com.example.bmicalculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText heightInch, heightFeet, weights;
    Button calculateBMI;
    TextView result, BMIValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        result = findViewById(R.id.result);
        calculateBMI = findViewById(R.id.button);
        heightInch = findViewById(R.id.heightInch);
        heightFeet = findViewById(R.id.normalHeight);
        weights = findViewById(R.id.weight);
        BMIValue = findViewById(R.id.BMIValue);
        calculateBMI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    int weight = Integer.parseInt(weights.getText().toString().trim());
                    int heightF = Integer.parseInt(heightFeet.getText().toString().trim());
                    int heightI = Integer.parseInt(heightInch.getText().toString().trim());

                    int totalInch = heightF * 12 + heightI;
                    double totalCm = totalInch * 2.54;
                    double totalM = totalCm / 100;

                    double BMI = weight / (totalM * totalM);
                    String formattedBMI = String.format("%.2f", BMI);
                    BMIValue.setText("Your BMI is " + formattedBMI);


                    double minNormalWeight = 18.5 * totalM * totalM;
                    double maxNormalWeight = 24.9 * totalM * totalM;

                    if (BMI < 16) {
                        result.setText("You are Severely Underweight. You should gain at least "
                                + String.format("%.2f", (minNormalWeight - weight)) + " kg to reach normal weight.");
                    } else if (BMI >= 16 && BMI <= 16.9) {
                        result.setText("You are Moderately Underweight. You should gain at least "
                                + String.format("%.2f", (minNormalWeight - weight)) + " kg to reach normal weight.");
                    } else if (BMI >= 17 && BMI <= 18.4) {
                        result.setText("You are Mildly Underweight. You should gain at least "
                                + String.format("%.2f", (minNormalWeight - weight)) + " kg to reach normal weight.");
                    } else if (BMI >= 18.5 && BMI <= 24.9) {
                        result.setText("You are Normal Weighted. Keep up the good work!");
                    } else if (BMI >= 25 && BMI <= 29.9) {
                        result.setText("You are Overweighted. You should lose at least "
                                + String.format("%.2f", (weight - maxNormalWeight)) + " kg to reach normal weight.");
                    } else if (BMI >= 30 && BMI <= 34.9) {
                        result.setText("You are Obese (Class I). You should lose at least "
                                + String.format("%.2f", (weight - maxNormalWeight)) + " kg to reach normal weight.");
                    } else if (BMI >= 35 && BMI <= 39.9) {
                        result.setText("You are Obese (Class II). You should lose at least "
                                + String.format("%.2f", (weight - maxNormalWeight)) + " kg to reach normal weight.");
                    } else if (BMI >= 40) {
                        result.setText("You are Severely Obese (Class III) and must lose weight. Aim to lose at least "
                                + String.format("%.2f", (weight - maxNormalWeight)) + " kg.");
                    }

                } catch (NumberFormatException e) {
                    result.setText("Please enter all values correctly.");
                    BMIValue.setText("");
                }
            }
        });

    }
}
