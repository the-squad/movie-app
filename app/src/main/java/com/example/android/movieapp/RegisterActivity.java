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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {

    private DatabaseReference db;
    private FirebaseAuth mAuth;
    EditText nameTextedite;
    EditText emailTextedite;
    EditText passwordTextedite;
    EditText repasswordTextedite;
    String type;
    Button signupButton;
    FirebaseUser user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth = FirebaseAuth.getInstance();
        db =  FirebaseDatabase.getInstance().getReference().child("users");

        //initiate section
        Intent intent=getIntent();
        type=intent.getStringExtra("type");




        nameTextedite = (EditText) findViewById(R.id.nameSignup);
        emailTextedite = (EditText) findViewById(R.id.emailSignup);
        passwordTextedite = (EditText) findViewById(R.id.passwordSignup);
        repasswordTextedite = (EditText) findViewById(R.id.repasswordSignup);
        signupButton = (Button) findViewById(R.id.signup);


        if(type.equalsIgnoreCase("profile")){
            user = FirebaseAuth.getInstance().getCurrentUser();
            emailTextedite.setText(user.getEmail());
            emailTextedite.setEnabled(false);
            //nameTextedite.setText(db);
            signupButton.setText("update");
        }


        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailTextedite.getText().toString();
                String password = passwordTextedite.getText().toString();
                String name = nameTextedite.getText().toString();
                String repassword = repasswordTextedite.getText().toString();
                if (!TextUtils.isEmpty(email))
                {
                    if (!TextUtils.isEmpty(password))
                    {
                        if (!TextUtils.isEmpty(name))
                        {
                            if (!TextUtils.isEmpty(repassword))
                            {
                                if (repassword.equals(password))
                                {
                                    if(type.equalsIgnoreCase("register")){
                                        SignUp(email,password,name);}
                                    else {
                                        updateProfile(password,name);
                                    }
                                }
                                else
                                {
                                    repasswordTextedite.setError("Doesn't match Password");
                                }
                            }
                            else
                                repasswordTextedite.setError("reEnter your password");
                        }
                        else
                            nameTextedite.setError("Enter name");
                    }
                    else
                        passwordTextedite.setError("Enter Password");
                }
                else
                    emailTextedite.setError("Enter email");

            }
        });

    }



    public void SignUp(String email, String pass, final String name)
    {
        //String name="mostafa@mmm.com";
        //String pass="123456789";
        mAuth.createUserWithEmailAndPassword(email, pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(RegisterActivity.this, "Done",
                                    Toast.LENGTH_SHORT).show();
                            db.child(task.getResult().getUser().getUid()).child("name").setValue(name);
                            Intent i = new Intent(RegisterActivity.this, MainActivity.class);
                            startActivity(i);
                        } else {
                            Toast.makeText(RegisterActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    public void updateProfile(String pass, final String name)
    {
        user.updatePassword(pass)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            db.child(user.getUid()).child("name").setValue(name);
                            Intent i = new Intent(RegisterActivity.this, MainActivity.class);
                            startActivity(i);
                        }
                    }
                });
    }

}