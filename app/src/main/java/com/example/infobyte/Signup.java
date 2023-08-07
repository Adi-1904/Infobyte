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
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Signup extends AppCompatActivity {
FirebaseAuth firebaseAuth;
TextView signup;
EditText email,password,confirmpassword;
Button signin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        firebaseAuth=FirebaseAuth.getInstance();
        signin=findViewById(R.id.signup_btn);
        signup=findViewById(R.id.signin);
        email=findViewById(R.id.signup_email);
        password=findViewById(R.id.signup_password);
        confirmpassword=findViewById(R.id.confirmPassword);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Signup.this, MainActivity.class);
                startActivity(intent);
            }
        });
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txtemail,txtpassword,txtconfirmpassword;
                txtemail=email.getText().toString();
                txtpassword=password.getText().toString();
                txtconfirmpassword=confirmpassword.getText().toString();
                if(txtconfirmpassword.equals(txtpassword))
                {
                    createaccount(txtemail,txtpassword);
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
    }

    private void createaccount(String txtemail, String txtpassword) {
        firebaseAuth.createUserWithEmailAndPassword(txtemail,txtpassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    FirebaseUser user=firebaseAuth.getCurrentUser();
                    user.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            if(task.isSuccessful())
                            {
                                Toast.makeText(Signup.this, "A Verification Mail Has Been Sent", Toast.LENGTH_SHORT).show();
                                Intent intent=new Intent(Signup.this,MainActivity.class);
                                startActivity(intent);
                            }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(Signup.this, "there is some error sorry for the incovenience", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }
}