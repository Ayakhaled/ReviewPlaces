package com.example.aya.pleces_rev;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;

public class Landing extends AppCompatActivity {
    private TextView email;
    private TextView password;
    private Button login;
    private Button createAcc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);

        email = (TextView) findViewById(R.id.email);
        password = (TextView) findViewById(R.id.password);
        login = (Button) findViewById(R.id.login_btn);
        createAcc = (Button) findViewById(R.id.register_btn);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Request request = new Request(String.valueOf(email.getText()), String.valueOf(password.getText()));
                ServiceHttp.Creator.getService(false).login(request)
                        .enqueue(new Callback<Response>() {
                            @Override
                            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                                System.out.println("Aya khaled: "+response.code());
                                if (response.code() == 200){
                                    System.out.println("Aya khaled: "+response.body().token);
                                    PreferenceManager.getDefaultSharedPreferences(Landing.this).edit().putString("TOKEN", response.body().token).apply();
                                    PreferenceManager.getDefaultSharedPreferences(Landing.this).edit().putBoolean("LOGGEDIN", true).apply();
                                    Intent intent = new Intent(Landing.this, MainActivity.class);
                                    startActivity(intent);
                                }
                                else {
                                    Toast.makeText(Landing.this, "Invalid Email or Password, please try again", Toast.LENGTH_LONG).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<Response> call, Throwable t) {
                                System.out.println("aya khaled: Failed"+t.getMessage());
                            }
                        });
            }
        });

        createAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Landing.this, Register.class);
                startActivity(intent);
            }
        });

    }

}
