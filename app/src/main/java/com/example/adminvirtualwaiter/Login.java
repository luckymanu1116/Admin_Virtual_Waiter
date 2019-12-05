package com.example.adminvirtualwaiter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity implements View.OnClickListener {
Button btnLogin;
EditText email,password;
CheckBox checkBox;
    @Override
    protected void onStart() {
        super.onStart();
        getDataFromSharedPreference();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
        btnLogin=findViewById(R.id.btnLogin);
        checkBox=findViewById(R.id.checkbox1);
        btnLogin.setOnClickListener(this);

    }
   private void getDataFromSharedPreference()
   {
       SharedPreferences sharedPreferences=getSharedPreferences("LoginDetails",0);
       String EmailFromFile=sharedPreferences.getString("UserName",null);
       String PasswordFromFile=sharedPreferences.getString("Password",null);
       if (!TextUtils.isEmpty(EmailFromFile) && !TextUtils.isEmpty(PasswordFromFile))
       {
          startActivity(new Intent(getApplicationContext(),Adminhome.class));
       }
   }
    private void storeInSharedPreference()
    {
        SharedPreferences sharedPreferences=getSharedPreferences("LoginDetails",0);
        SharedPreferences.Editor editor=sharedPreferences.edit();

        String UserName=email.getText().toString();
        String Password=password.getText().toString();

        editor.putString("UserName",UserName);
        editor.putString("Password",Password);
        editor.commit();
    }

    public  void removeDataFromSharedPreference()
    {
        SharedPreferences sharedPreferences=getSharedPreferences("LoginDetails",0);
        SharedPreferences.Editor editor=sharedPreferences.edit();

        editor.putString("UserName",null);
        editor.putString("Password",null);
        editor.commit();
    }


    @Override
    public void onClick(View view) {
        if (checkEmail() && checkPassword())
        {
            if (checkCheckBox())
                storeInSharedPreference();
            startActivity(new Intent(getApplicationContext(),Adminhome.class));
        }
    }

    private boolean checkEmail()
    {
        String Email=email.getText().toString();
        if (TextUtils.isEmpty(Email))
        {
            Toast.makeText(this, "Email cannot be empty", Toast.LENGTH_SHORT).show();
            return false;
        }
        else
        {
            if (Email.contentEquals("Manoj"))
                return true;
            else
            {
                Toast.makeText(this, "Wrong Email", Toast.LENGTH_SHORT).show();
                return false;
            }
        }
    }
    private boolean checkPassword()
    {
        String Password=password.getText().toString();
        if (TextUtils.isEmpty(Password))
        {
            Toast.makeText(this, "Password cannot be empty", Toast.LENGTH_SHORT).show();
            return false;
        }
        else
        {
            if (Password.contentEquals("123456"))
                return true;
            else
            {
                Toast.makeText(this, "Password is wrong", Toast.LENGTH_SHORT).show();
                return false;
            }
        }
    }

    private boolean checkCheckBox()
    {
        boolean ischecked=checkBox.isChecked();
        if (ischecked)
        {
            return true;
        }
        else
            return false;
    }

}
