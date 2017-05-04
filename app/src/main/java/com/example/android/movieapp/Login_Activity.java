package com.example.android.movieapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login_Activity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private Button login_btn;
    private EditText email_text;
    private EditText pass_text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_);

        mAuth = FirebaseAuth.getInstance();


        email_text=(EditText)findViewById(R.id.email_log_id);
        pass_text=(EditText)findViewById(R.id.pass_log_id);
        login_btn=(Button)findViewById(R.id.log_btn);


        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String Email=email_text.getText().toString();
                String passWord=pass_text.getText().toString();

                if(!TextUtils.isEmpty(Email) && !TextUtils.isEmpty(passWord))
                {
                    Login(Email,passWord);
                } else {
                  email_text.setError("Required");
                  pass_text.setError("Required");
                }

            }
        });
    }



    public void Login(String Email,String passWord)
        {

            mAuth.signInWithEmailAndPassword(Email, passWord)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (task.isSuccessful()) {
                                Toast.makeText(Login_Activity.this, "Done",
                                        Toast.LENGTH_SHORT).show();
                                Intent i=new Intent(Login_Activity.this,MainActivity.class);
                                startActivity(i);
                            } else {
                                Toast.makeText(Login_Activity.this, "sign Failed.",
                                        Toast.LENGTH_SHORT).show();
                                email_text.setError("Invalid");
                                pass_text.setError("Invalid");
                            }
                        }
                    });
         }

}
