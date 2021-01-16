package mmu.edu.customerinterface;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class howtoActivity4 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_howto4);

        configureBackToPage3Button();
        configureNextToPage4Button();

    }

    public void configureBackToPage3Button() {
        Button backToPage2 = (Button) findViewById(R.id.backButton1);
        backToPage2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(howtoActivity4.this,howtoActivity3.class));
            }
        });
    }
    public void configureNextToPage4Button() {
        Button nextToChoosePage = (Button) findViewById(R.id.nextButton3);
        nextToChoosePage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(howtoActivity4.this,workorrequestActivity.class));
            }
        });
    }
}