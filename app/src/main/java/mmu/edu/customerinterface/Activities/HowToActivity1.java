package mmu.edu.customerinterface.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.Toast;

import maes.tech.intentanim.CustomIntent;
import mmu.edu.customerinterface.R;

import static android.widget.Toast.LENGTH_SHORT;

public class HowToActivity1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_how_to1);

        configureNextToPage2Button();
    }

    private void configureNextToPage2Button() {

        Button nextToPage2 = findViewById(R.id.nextButton);
        nextToPage2.setOnClickListener(v -> {
            startActivity(new Intent(HowToActivity1.this, HowToActivity2.class));
            CustomIntent.customType(this, "left-to-right");
            finish();
        });
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(HowToActivity1.this, WelcomePage.class));
        finish();
    }
}