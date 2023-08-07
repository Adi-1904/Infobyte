package com.example.infobyte;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import org.w3c.dom.Text;

public class forgetPassword extends AppCompatActivity {
    FirebaseAuth auth;
    EditText email;
    Button forget;
    TextView signin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        auth=FirebaseAuth.getInstance();
        email=findViewById(R.id.forget_email);
        forget=findViewById(R.id.forget);
        signin=findViewById(R.id.login);
        Intent intent=new Intent(forgetPassword.this,MainActivity.class);
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
                startActivity(intent);
            }
        });
        forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txtemail;
                txtemail=email.getText().toString();
                auth.sendPasswordResetEmail(txtemail).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful())
                        {
                            Toast.makeText(forgetPassword.this, "Reset link has been sent to email", Toast.LENGTH_SHORT).show();
                            startActivity(intent);
                        }
                        if(task.isCanceled())
                        {
                            Toast.makeText(forgetPassword.this, "Something Went Wrong", Toast.LENGTH_SHORT).show();
                            startActivity(intent);
                        }
                    }
                });
            }
        });
    }

    @Override
    public void onBackPressed() {

    }
}