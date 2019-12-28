package com.pasc.pulzion19;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Success  extends AppCompatActivity {
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success);
        Intent i=getIntent();
        String s=i.getStringExtra("Response");
        textView=findViewById(R.id.textView);
        textView.setText(s);

    }
}
