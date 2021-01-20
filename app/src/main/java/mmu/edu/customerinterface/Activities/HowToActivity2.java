package mmu.edu.customerinterface.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import maes.tech.intentanim.CustomIntent;
import mmu.edu.customerinterface.R;

public class HowToActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_how_to2);

        configureNextToPage3Button();
        configureBackToPage2Button();
    }

    private void configureNextToPage3Button() {
        Button NextToPage3 = findViewById(R.id.nextButton2);
        NextToPage3.setOnClickListener(v -> {
            startActivity(new Intent(HowToActivity2.this, HowToActivity3.class));
            CustomIntent.customType(this, "left-to-right");
            finish();
        });
    }

    private void configureBackToPage2Button() {
        Button BackToPage2 = findViewById(R.id.backButton);
        BackToPage2.setOnClickListener(v -> onBackPressed());
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(HowToActivity2.this, HowToActivity1.class));
        CustomIntent.customType(this, "right-to-left");
        finish();
    }
}