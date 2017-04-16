package com.example.baby.graduationdesignone;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Feedback extends AppCompatActivity implements View.OnClickListener {
    Button send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        IntentView();
    }

    private void IntentView() {
        send = (Button) findViewById(R.id.send);
        send.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.send:
                Intent send = new Intent(Feedback.this, MainActivity.class);
                startActivity(send);
                this.finish();
                break;
        }
    }
}
