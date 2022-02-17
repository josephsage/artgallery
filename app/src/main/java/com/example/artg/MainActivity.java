package com.example.artg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity {

    private Button login, Register;
    private TextView forget;
    EditText email, password;
    FirebaseAuth fAuth;
    FirebaseFirestore fstore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // background animation
        RelativeLayout relativeLayout = findViewById(R.id.main_layout);
        AnimationDrawable animationDrawable = (AnimationDrawable) relativeLayout.getBackground();
        animationDrawable.setEnterFadeDuration(2600);
        animationDrawable.setExitFadeDuration(4000);
        animationDrawable.start();


        forget = (TextView) findViewById(R.id.forget);
        fAuth = FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();
        login = (Button) findViewById(R.id.login_btn);
        Register = (Button) findViewById(R.id.register_btn);
        email = findViewById(R.id.login_mail);
        password = findViewById(R.id.login_password);

        // takes you to the register page
        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                {
                    Intent intent = new Intent(MainActivity.this, com.example.artg.Register.class);
                    startActivity(intent);
                }
                

            }
            private void checkUserAccessLevel(String uid){
                DocumentReference documentReference = fstore.collection("User").document(uid);
                //extract the data
            }

        });

        //Logs in the user
        login.setOnClickListener((v) -> {
            final String mail = email.getText().toString().trim();
            final String pass = password.getText().toString().trim();
            {

                fAuth.signInWithEmailAndPassword(mail, pass).addOnCompleteListener((task) -> {
                    if (task.isSuccessful()) {
                        Intent intent = new Intent(MainActivity.this,  com.example.artg.Home.class);
                        startActivity(intent);
                        Toast.makeText(MainActivity.this, "Logged in.", Toast.LENGTH_SHORT).show();
                    }
                    
                });
            }
        });
    }

}
