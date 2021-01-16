package mmu.edu.customerinterface;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class workorrequestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workorrequest);

        configureRequestButton();
    }

    public void configureRequestButton() {
        Button requestButton = (Button) findViewById(R.id.RequestButton);
        requestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(workorrequestActivity.this,logInActivity.class));
            }
        });
    }
}