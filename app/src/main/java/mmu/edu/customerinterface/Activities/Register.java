package mmu.edu.customerinterface.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import mmu.edu.customerinterface.R;


public class Register extends AppCompatActivity {

    Context context = this;
    long maxId = 1;
    //    private TextView banner;
    private EditText editTextFirstName, editTextLastName, editTextAge, editTextGender, editTextEmail, editTextPassword;
    private ProgressBar progressBar;
    private FirebaseAuth mAuth;
    private DatabaseReference ref;
    private FirebaseFirestore fStore;
    private DocumentReference noteRef;
    boolean successful = false;
    public static final String TAG = "TAG";
    public static final String SHARED_PREFS = "sharedPrefs";
    //private FirebaseAnalytics mFirebaseAnalytics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        ref = FirebaseDatabase.getInstance().getReference().child("Member");

        FirebaseDatabase.getInstance().getReference().child("Member").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                maxId = dataSnapshot.getChildrenCount();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        //mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        //banner = (TextView) findViewById(R.id.banner);
        //banner.setOnClickListener(this);

        editTextFirstName = findViewById(R.id.txtFirstNameUserSignUp);
        editTextLastName = findViewById(R.id.txtLastNameUserSignUp);
        editTextAge = findViewById(R.id.txtAgeUserSignUp);
        editTextGender = findViewById(R.id.txtGenderUserSignUp);
        editTextEmail = findViewById(R.id.txtEmailUserSignUp);
        editTextPassword = findViewById(R.id.txtPasswordIserSignUp);

        progressBar = findViewById(R.id.progressBar2);
    }


    public void registerUser(View view) {
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


        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Toast.makeText(context, "User Created.", Toast.LENGTH_SHORT).show();
                String userID = Objects.requireNonNull(mAuth.getCurrentUser()).getUid();
                DocumentReference documentReference = fStore.collection("users").document(userID);
                Map<String, Object> user = new HashMap<>();
                ref.child(String.valueOf(maxId + 1)).child("firstName").setValue(firstName);
                ref.child(String.valueOf(maxId + 1)).child("lastName").setValue(lastName);
                ref.child(String.valueOf(maxId + 1)).child("age").setValue(age);
                ref.child(String.valueOf(maxId + 1)).child("gender").setValue(gender);
                ref.child(String.valueOf(maxId + 1)).child("email").setValue(email);

                user.put("Id", Long.toString(maxId + 1));
                saveData(Long.toString(maxId + 1), "Id");

                documentReference.set(user).addOnSuccessListener(aVoid -> {
                    successful = true;
                    Log.d(TAG, "onSuccess: user Profile is created for " + userID);
                }).addOnFailureListener(e -> {
                    Log.d(TAG, "onFailure: " + e.toString());
                    progressBar.setVisibility(View.GONE);
                    successful = false;
                });
                if (successful) {
                    Toast.makeText(context,"User created successfully",Toast.LENGTH_LONG).show();
//                    startActivity(new Intent(getApplicationContext(), UserDashboard.class));
//                    finish();
                }
            } else {
                Toast.makeText(context, "Error ! " + Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


//        mAuth.createUserWithEmailAndPassword(email, password)
//                .addOnCompleteListener(task -> {
//                    if (task.isSuccessful()) {
//                        User user = new User(firstName, age, email);
//
//                        FirebaseDatabase.getInstance().getReference("Users")
//                                .child(FirebaseAuth.getInstance().getCurrentUser().getUid()) // This will return us the id for the register user and set it to
//                                .setValue(user).addOnCompleteListener(task1 -> {
//                            if (task1.isSuccessful()) {
//                                Toast.makeText(userssignupActivity.this, "User has been registered successfully!", Toast.LENGTH_LONG).show();
//                            } else {
//                                Toast.makeText(userssignupActivity.this, "Failed to register! Try again 1!", Toast.LENGTH_LONG).show();
//                            }
//                            progressBar.setVisibility(View.GONE);
//                        });
//                    } else {
//                        Toast.makeText(userssignupActivity.this, "Failed to register! Try again 111", Toast.LENGTH_LONG).show();
//                        progressBar.setVisibility(View.GONE);
//                    }
//                });
    }

    public void saveData(String data, String name) {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(name, data);
        editor.apply();
    }
}
