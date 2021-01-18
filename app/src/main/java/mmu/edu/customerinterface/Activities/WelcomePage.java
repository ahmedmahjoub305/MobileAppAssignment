package mmu.edu.customerinterface.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import mmu.edu.customerinterface.R;

public class WelcomePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        configureGetStartedButton();
    }

    private void configureGetStartedButton() {

        Button getStartedButton = (Button) findViewById(R.id.GetStartedbutton);
        getStartedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(WelcomePage.this, HowToActivity1.class));
            }
        });
    }
}