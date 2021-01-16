package mmu.edu.customerinterface;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
//import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;



public class userssignupActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView banner, registerUser;
    private EditText editTextFirstName, editTextLastName, editTextAge, editTextGender, editTextEmail, editTextPassword;
    private ProgressBar progressBar;

    private FirebaseAuth mAuth;
//    private FirebaseAnalytics mFirebaseAnalytics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userssignup);

        mAuth = FirebaseAuth.getInstance();
//        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        //banner = (TextView) findViewById(R.id.banner);
        //banner.setOnClickListener(this);

        registerUser = (Button) findViewById(R.id.userSignUpButton);
        registerUser.setOnClickListener(this);

        editTextFirstName = (EditText) findViewById(R.id.txtFirstNameUserSignUp);
        editTextLastName = (EditText) findViewById(R.id.txtLastNameUserSignUp);
        editTextAge = (EditText) findViewById(R.id.txtAgeUserSignUp);
        editTextGender = (EditText) findViewById(R.id.txtGenderUserSignUp);
        editTextEmail = (EditText) findViewById(R.id.txtEmailUserSignUp);
        editTextPassword = (EditText) findViewById(R.id.txtPasswordIserSignUp);


        progressBar = (ProgressBar) findViewById(R.id.progressBar2);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.userSignUpButton:
                registerUser();
                break;

        }
    }

    private void registerUser() {
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        String firstName = editTextFirstName.getText().toString().trim();
        String lastName = editTextLastName.getText().toString().trim();
        String age = editTextAge.getText().toString().trim();
        String gender = editTextGender.getText().toString().trim();

        if (firstName.isEmpty()) {
            editTextFirstName.setError("First Name is required");
            editTextFirstName.requestFocus();
            return;
        }
        if (lastName.isEmpty()) {
            editTextLastName.setError("Last Name is required");
            editTextLastName.requestFocus();
            return;
        }
        if (age.isEmpty()) {
            editTextAge.setError("Age is required");
            editTextAge.requestFocus();
            return;
        }
        if (gender.isEmpty()) {
            editTextGender.setError("Gender is required");
            editTextGender.requestFocus();
            return;
        }
        if (email.isEmpty()) {
            editTextEmail.setError("Email is required");
            editTextEmail.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextEmail.setError("Please provide a valid email");
            editTextEmail.requestFocus();
            return;
        }
        if (password.isEmpty()) {
            editTextPassword.setError("Password is required");
            editTextPassword.requestFocus();
            return;
        }

        if (password.length() < 6) {
            editTextPassword.setError("Password must consist of more than 6 digits");
            editTextPassword.requestFocus();
            return;
        }
        progressBar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            User user = new User(firstName,  age,  email);

                            FirebaseDatabase.getInstance().getReference("Users" )
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid()) // This will return us the id for the register user and set it to
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        Toast.makeText(userssignupActivity.this, "User has been registered successfully!", Toast.LENGTH_LONG).show();
                                        progressBar.setVisibility(View.GONE);

                                        //redirect to login layout
                                    }else {
                                        Toast.makeText(userssignupActivity.this, "Failed to register! Try again 1!", Toast.LENGTH_LONG).show();
                                        progressBar.setVisibility(View.GONE);
                                    }
                                }
                            });
                        }else {
                            Toast.makeText(userssignupActivity.this, "Failed to register! Try again 111", Toast.LENGTH_LONG).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });
        }

    }
