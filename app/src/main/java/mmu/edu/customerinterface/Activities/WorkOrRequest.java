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

        configureRequestButton();
    }

    public void configureRequestButton() {
        Button requestButton = (Button) findViewById(R.id.RequestButton);
        requestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WorkOrRequest.this, SignIn.class));
            }
        });
    }
}