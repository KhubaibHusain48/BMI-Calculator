package com.example.bmicalculator

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
    TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        result=findViewById(R.id.result);
        calculateBMI = findViewById(R.id.button);
        heightInch = findViewById(R.id.heightInch);
        heightFeet = findViewById(R.id.normalHeight);
        weights = findViewById(R.id.weight);

        calculateBMI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int weight = Integer.parseInt(weights.getText().toString());
                int heightF = Integer.parseInt(heightFeet.getText().toString());
                int heightI = Integer.parseInt(heightInch.getText().toString());

                int totalInch = heightF * 12 + heightI;
                double totalCm = totalInch * 2.53;
                double totalM = totalCm / 100;

                double BMI = weight / (totalM * totalM);

                if (BMI > 25) {
                    result.setText("You are unhealthy");
                } else if (BMI < 18) {
                    result.setText("You are underweight");
                } else {
                    result.setText("You are normal weighted");
                }
            }
        });


    }
}
