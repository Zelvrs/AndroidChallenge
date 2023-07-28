package com.example.androidchallenge;

import androidx.appcompat.app.AppCompatActivity;

import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private EditText editTextName, editTextEmail, editTextPassword, editTextRePassword;
    private Button btnPickImage, btnRegister;
    private TextView nameWarning, emailWarning, passwordWarning, rePasswordWarning;
    private Spinner countriesSpinner;
    private RadioGroup rgGender;
    private CheckBox agreementCheck;
    private RelativeLayout parent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        btnPickImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Yet to talk about", Toast.LENGTH_SHORT).show();
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initRegister();
            }
        });
    }
    private void initRegister(){
        Log.d(TAG, "initRegister: Started");

        if(validateData()){
            if(agreementCheck.isChecked()){
                showSnackBar();
            } else {
                Toast.makeText(this, "check the agreement please", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void showSnackBar(){
        Log.d(TAG, "showSnackBar: started");
        nameWarning.setVisibility(View.GONE);
        emailWarning.setVisibility(View.GONE);
        passwordWarning.setVisibility(View.GONE);
        rePasswordWarning.setVisibility(View.GONE);

        String name = editTextName.getText().toString();
        String email = editTextEmail.getText().toString();
        String country = countriesSpinner.getSelectedItem().toString();
        String gender = "";
        if(rgGender.getCheckedRadioButtonId() == R.id.rbMale){
            gender = "male";
        } else if(rgGender.getCheckedRadioButtonId() == R.id.rbFemale){
            gender = "female";
        } else {
            gender = "other";
        }

        String snackText = "Name: " + name + "\n" +
                "Email: " + email + "\n" +
                "Gender: " + gender + "\n" +
                "Country: " + country;
        Log.d(TAG, "showSnackBar:" + snackText);

        Snackbar.make(parent, snackText, Snackbar.LENGTH_SHORT)
                .setAction("Dismiss", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        editTextName.setText("");
                        editTextEmail.setText("");
                        editTextPassword.setText("");
                        editTextRePassword.setText("");
                    }
                }).show();
    }

    private boolean validateData(){

        boolean dataInfo = true;
        Log.d(TAG, "validateData: started");
        if(editTextName.getText().toString().equals("")){
            nameWarning.setVisibility(View.VISIBLE);
            nameWarning.setText("Enter your name!");
            dataInfo = false;
        }
        if(editTextEmail.getText().toString().equals("")){
            emailWarning.setVisibility(View.VISIBLE);
            emailWarning.setText("Enter your email!");
            dataInfo = false;
        }
        if(editTextPassword.getText().toString().equals("")){
            passwordWarning.setVisibility(View.VISIBLE);
            passwordWarning.setText("Enter your password!");
            dataInfo = false;
        }
        if(editTextRePassword.getText().toString().equals("")){
            rePasswordWarning.setVisibility(View.VISIBLE);
            rePasswordWarning.setText("Enter your repeat password!");
            dataInfo = false;
        }
        if(!editTextPassword.getText().toString().equals(editTextRePassword.getText().toString())){
            rePasswordWarning.setVisibility(View.VISIBLE);
            rePasswordWarning.setText("password doesn't match");
            dataInfo = false;
        }


        return dataInfo;

    }

    private void initViews() {
        Log.d(TAG, "initViews: Started");
        editTextName = findViewById(R.id.editTextName);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        editTextRePassword = findViewById(R.id.editTextRePassword);

        btnPickImage = findViewById(R.id.btnPickImage);
        btnRegister = findViewById(R.id.btnRegister);

        nameWarning = findViewById(R.id.nameWarning);
        emailWarning = findViewById(R.id.emailWarning);
        passwordWarning = findViewById(R.id.passwordWarning);
        rePasswordWarning = findViewById(R.id.rePasswordWarning);

        countriesSpinner = findViewById(R.id.spinnerCountry);
        rgGender = findViewById(R.id.rgGender);
        agreementCheck = findViewById(R.id.agreementCheck);
        parent = findViewById(R.id.parent);
    }
}