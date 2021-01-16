package mmu.edu.customerinterface;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class howtoActivity1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_howto2);

        configureNextToPage2Button();
    }

    private void configureNextToPage2Button() {

        Button nextToPage2 = (Button) findViewById(R.id.nextButton);
        nextToPage2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(howtoActivity1.this, howtoActivity3.class));
            }
        });
    }
}