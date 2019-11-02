package com.example.sharedpreference;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{

    private EditText usernameEditText, passwordEditText;
    private Button saveButton, loadButton;
    private TextView resultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usernameEditText = findViewById(R.id.userName_id);
        passwordEditText = findViewById(R.id.password_id);
        saveButton = findViewById(R.id.saveButton_id);
        loadButton = findViewById(R.id.loadButton_id);
        resultTextView = findViewById(R.id.result_id);

        saveButton.setOnClickListener(this);
        loadButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.saveButton_id){

            String userName = usernameEditText.getText().toString();
            String password = usernameEditText.getText().toString();

            if (userName.equals("") && password.equals("")){
                Toast.makeText(getApplicationContext(), "Data not found", Toast.LENGTH_SHORT).show();
            }
            else {

                SharedPreferences sharedPreferences = getSharedPreferences("loginDetails", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("usernameData", userName);
                editor.putString("passwordData", password);
                usernameEditText.setText("");
                passwordEditText.setText("");
                editor.commit();
                Toast.makeText(getApplicationContext(), "Data shared successfully", Toast.LENGTH_SHORT).show();
            }
        }
        else if (v.getId() == R.id.loadButton_id){
            SharedPreferences sharedPreferences = getSharedPreferences("loginDetails", Context.MODE_PRIVATE);

            if (sharedPreferences.contains("usernameData") && sharedPreferences.contains("passwordData")){
                String userName1 = sharedPreferences.getString("usernameData", "Username not found");
                String password1 = sharedPreferences.getString("passwordData", "Password not found");
                resultTextView.setText(userName1+password1);
            }

        }

    }
}
