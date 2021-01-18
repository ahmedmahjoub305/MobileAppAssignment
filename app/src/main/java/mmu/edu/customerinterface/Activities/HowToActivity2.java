package mmu.edu.customerinterface.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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
        Button NextToPage3 = (Button) findViewById(R.id.nextButton2);
        NextToPage3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HowToActivity2.this, HowToActivity3.class));
            }
        });
    }

    private void configureBackToPage2Button() {
        Button BackToPage2 = (Button) findViewById(R.id.backButton);
        BackToPage2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HowToActivity2.this, HowToActivity1.class));
            }
        });
    }
}