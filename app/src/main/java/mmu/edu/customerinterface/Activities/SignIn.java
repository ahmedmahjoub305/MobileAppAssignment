package mmu.edu.customerinterface.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Objects;

import mmu.edu.customerinterface.R;

import static mmu.edu.customerinterface.Activities.Register.SHARED_PREFS;

public class SignIn extends AppCompatActivity {

    Context context = this;
    EditText editTextTextPersonName, editTextTextPassword;
    private FirebaseAuth mAuth;
    public static final String TAG = "TAG";

    ProgressBar progressBar;

    public void LogIn(View view) {
        String email = editTextTextPersonName.getText().toString().trim();
        String password = editTextTextPassword.getText().toString().trim();


        if (TextUtils.isEmpty(email)) {
            editTextTextPersonName.setError("Email is Required.");
            return;
        }

        if (TextUtils.isEmpty(password)) {
            editTextTextPassword.setError("Password is Required.");
            return;
        }

        if (password.length() < 6) {
            editTextTextPassword.setError("Password Must be >= 6 Characters");
            return;
        }

        progressBar.setVisibility(View.VISIBLE);

        // authenticate the user

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                saveId();
            } else {
                Toast.makeText(context, "Error ! " + Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.GONE);
            }
        });
    }

    public void Register(View view) {
        startActivity(new Intent(SignIn.this, Register.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        mAuth = FirebaseAuth.getInstance();

        editTextTextPersonName = findViewById(R.id.editTextTextPersonName);
        editTextTextPassword = findViewById(R.id.editTextTextPassword);
        progressBar = findViewById(R.id.progressBar);
    }

    public void saveId() {
        String userID = Objects.requireNonNull(mAuth.getCurrentUser()).getUid();
        FirebaseFirestore.getInstance().collection("users").document(userID).addSnapshotListener(this, (documentSnapshot, e) -> {
            if (e != null) {
                Toast.makeText(context, "Error while loading!", Toast.LENGTH_SHORT).show();
                Log.d(TAG, e.toString());
                FirebaseAuth.getInstance().signOut();//logout
                progressBar.setVisibility(View.GONE);
                return;
            }

            assert documentSnapshot != null;
            if (documentSnapshot.exists()) {
                String Id = documentSnapshot.getString("Id");
                saveData(Id, "Id");
                Toast.makeText(context, "Logged in Successfully", Toast.LENGTH_SHORT).show();
//                startActivity(new Intent(getApplicationContext(), UserDashboard.class));
//                finish();
            }
        });
    }

    public void saveData(String data, String name) {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(name, data);
        editor.apply();
    }
}