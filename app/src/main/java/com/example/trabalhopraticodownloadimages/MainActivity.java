package com.example.trabalhopraticodownloadimages;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText editUrl;
    Button btSubmit;
    ImageView downloadedImage;
    ProgressBar pgbProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editUrl = findViewById(R.id.editUrl);
        btSubmit = findViewById(R.id.btSubmit);
        downloadedImage = findViewById(R.id.downloadedImage);
        pgbProgress = findViewById(R.id.pgbProgress);

        btSubmit.setOnClickListener(this);
    }

    public void onClick(View view){
        String urlLink  = editUrl.getText().toString();
        if (urlLink.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Please enter url!",
                    Toast.LENGTH_SHORT).show();
        } else {
            pgbProgress.setVisibility(View.VISIBLE);
            downloading();
        }
    }
    public void downloading(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                SystemClock.sleep(1000);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        String urlLink  = editUrl.getText().toString();
                        pgbProgress.setVisibility(View.INVISIBLE);
                        DownloadImageUrl downloadImageUrl = new DownloadImageUrl(downloadedImage);
                        downloadImageUrl.execute(urlLink);
                    }
                });
            }
        }).start();
    }

}