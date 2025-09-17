package com.example.pokemontrackerapp_simonds;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {



    EditText inputName, inputHp, inputAttack, inputDefense, inputHeight, inputWeight;
    Spinner inputGender;
    TextView labelName, labelGender, labelHp, labelAttack, labelDefense, labelHeight, labelWeight;
    Button btnSubmit;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_main);

        // Change this line depending on which layout you want to test:
         setContentView(R.layout.linear);
        // setContentView(R.layout.constraint);
        // setContentView(R.layout.table);

        // Hook up views
        inputName = findViewById(R.id.nameInput);
        inputHp = findViewById(R.id.hpInput);
        inputAttack = findViewById(R.id.attackInput);
        inputDefense = findViewById(R.id.defenseInput);
        inputHeight = findViewById(R.id.heightInput);
        inputWeight = findViewById(R.id.weightInput);
        inputGender = findViewById(R.id.genderInput);

        labelName = findViewById(R.id.nameLabel);
        labelGender = findViewById(R.id.genderLabel);
        labelHp = findViewById(R.id.hpLabel);
        labelAttack = findViewById(R.id.attackLabel);
        labelDefense = findViewById(R.id.defenseLabel);
        labelHeight = findViewById(R.id.heightLabel);
        labelWeight = findViewById(R.id.weightLabel);

        btnSubmit = findViewById(R.id.submitButton);

        // Setup Gender spinner
        ArrayAdapter<String> genderAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item,
                new String[]{"Select Gender", "Male", "Female", "Unknown"});
        genderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        inputGender.setAdapter(genderAdapter);

        btnSubmit.setOnClickListener(v -> validateInputs());
    }

    private void validateInputs() {
        boolean allValid = true;
        resetLabelColors(); // Reset from previous attempts

        String name = inputName.getText().toString().trim();
        String gender = inputGender.getSelectedItem().toString();
        String hpStr = inputHp.getText().toString().trim();
        String attackStr = inputAttack.getText().toString().trim();
        String defenseStr = inputDefense.getText().toString().trim();
        String heightStr = inputHeight.getText().toString().trim();
        String weightStr = inputWeight.getText().toString().trim();

        // Name
        if (name.isEmpty() || !name.matches("^[A-Za-z]{3,12}$")) {
            labelName.setTextColor(Color.RED);
            allValid = false;
        }

        // Gender
        if (!(gender.equals("Male") || gender.equals("Female") || gender.equals("Unknown"))) {
            labelGender.setTextColor(Color.RED);
            allValid = false;
        }

        // HP
        if (!isInRange(hpStr, 1, 362)) {
            labelHp.setTextColor(Color.RED);
            allValid = false;
        }

        // Attack
        if (!isInRange(attackStr, 0, 526)) {
            labelAttack.setTextColor(Color.RED);
            allValid = false;
        }

        // Defense
        if (!isInRange(defenseStr, 10, 614)) {
            labelDefense.setTextColor(Color.RED);
            allValid = false;
        }

        // Height
        if (!isInRangeDecimal(heightStr, 0.2, 169.99)) {
            labelHeight.setTextColor(Color.RED);
            allValid = false;
        }

        // Weight
        if (!isInRangeDecimal(weightStr, 0.1, 992.7)) {
            labelWeight.setTextColor(Color.RED);
            allValid = false;
        }

        if (allValid) {
            String summary = "Name: " + name + "\nGender: " + gender + "\nHP: " + hpStr;
            Toast.makeText(this, "Saved:\n" + summary, Toast.LENGTH_LONG).show();

        } else {
            Toast.makeText(this, "Some fields are invalid. Please fix highlighted fields.", Toast.LENGTH_LONG).show();
        }
    }

    private void resetLabelColors() {
        labelName.setTextColor(Color.BLACK);
        labelGender.setTextColor(Color.BLACK);
        labelHp.setTextColor(Color.BLACK);
        labelAttack.setTextColor(Color.BLACK);
        labelDefense.setTextColor(Color.BLACK);
        labelHeight.setTextColor(Color.BLACK);
        labelWeight.setTextColor(Color.BLACK);
    }

    private boolean isInRange(String value, int min, int max) {
        try {
            int num = Integer.parseInt(value);
            return num >= min && num <= max;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean isInRangeDecimal(String value, double min, double max) {
        try {
            double num = Double.parseDouble(value);
            return num >= min && num <= max;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}