package com.example.android.movieapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class Reset_password extends AppCompatActivity {

    EditText email_text;
    Button send_btn;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

         auth = FirebaseAuth.getInstance();

        email_text=(EditText)findViewById(R.id.email_reset_id);
        send_btn=(Button)findViewById(R.id.send_id);


        send_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Email=email_text.getText().toString();
                resetPassWord(Email);
            }
        });




    }


    public void resetPassWord(String email)
    {
        auth.sendPasswordResetEmail(email)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getApplication(),"Reset password successful",Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(Reset_password.this,Login_Activity.class);
                            startActivity(intent);
                        }
                        else
                        {
                            email_text.setError("Invalid Email");
                            Toast.makeText(getApplication(),"invalid Email",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }


}
