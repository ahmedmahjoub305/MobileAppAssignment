package mmu.edu.customerinterface.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import mmu.edu.customerinterface.R;

public class HowToActivity1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_how_to1);

        configureNextToPage2Button();
    }

    private void configureNextToPage2Button() {

        Button nextToPage2 = (Button) findViewById(R.id.nextButton);
        nextToPage2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HowToActivity1.this, HowToActivity2.class));
            }
        });
    }
}