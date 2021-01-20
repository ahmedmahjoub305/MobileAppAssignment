package mmu.edu.customerinterface.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import maes.tech.intentanim.CustomIntent;
import mmu.edu.customerinterface.R;

public class HowToActivity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_how_to3);

        configureBackToPage3Button();
        configureNextToPage4Button();

    }

    public void configureBackToPage3Button() {
        Button backToPage2 = findViewById(R.id.backButton1);
        backToPage2.setOnClickListener(v -> onBackPressed());
    }

    public void configureNextToPage4Button() {
        Button nextToChoosePage = findViewById(R.id.nextButton3);
        nextToChoosePage.setOnClickListener(v -> {
            startActivity(new Intent(HowToActivity3.this, WorkOrRequest.class));
            finish();
        });
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(HowToActivity3.this, HowToActivity2.class));
        CustomIntent.customType(this, "right-to-left");
        finish();
    }
}