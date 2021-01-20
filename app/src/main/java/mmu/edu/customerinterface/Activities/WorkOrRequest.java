package mmu.edu.customerinterface.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import mmu.edu.customerinterface.R;

public class WorkOrRequest extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_or_request);

        configureButtons();
    }

    public void configureButtons() {
        Button requestButton = findViewById(R.id.RequestButton);
        requestButton.setOnClickListener(v -> startActivity(new Intent(WorkOrRequest.this, ClientSignIn.class)));

        Button workButton = findViewById(R.id.WorkButton);
        workButton.setOnClickListener(v -> startActivity(new Intent(WorkOrRequest.this, ContractorSignIn.class)));
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(WorkOrRequest.this, HowToActivity3.class));
        finish();
    }
}