package com.example.aya.pleces_rev;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.Observable;
import org.apache.http.client.HttpClient;

import retrofit2.Call;
import retrofit2.Callback;

public class Register extends AppCompatActivity {
    private TextView name;
    private TextView email;
    private TextView password;
    private Button registerBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        name = (TextView) findViewById(R.id.name);
        email = (TextView) findViewById(R.id.email);
        password = (TextView) findViewById(R.id.password);
        registerBtn = (Button) findViewById(R.id.register_btn);

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Request request = new Request(String.valueOf(name.getText()), String.valueOf(email.getText()), String.valueOf(password.getText()));
                ServiceHttp.Creator.getService(false).addUser(request)
                .enqueue(new Callback<Response>() {
                    @Override
                    public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                        if (response.code() == 201){
                            System.out.println("Aya khaled: "+response.body().token);
                            PreferenceManager.getDefaultSharedPreferences(Register.this).edit().putString("TOKEN", response.body().token).commit();
                            PreferenceManager.getDefaultSharedPreferences(Register.this).edit().putBoolean("LOGGEDIN", true).commit();
                            PreferenceManager.getDefaultSharedPreferences(Register.this).edit().putString("NAME", String.valueOf(name.getText())).commit();
                            Intent intent = new Intent(Register.this, MainActivity.class);
                            startActivity(intent);
                        }
                        else {
                            Toast.makeText(Register.this, "This email is already registered try another one", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Response> call, Throwable t) {
                        System.out.println("aya khaled: Failed"+t.getMessage());
                    }
                });
            }
        });

    }
}
