package com.adesoftware.camcorderdirect;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;

public class CamCorderDirectActivity extends AppCompatActivity {

	public static int VIDEO_CAPTURED = 1;
    Uri videoFileUri;
    private Button recVideoButton;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
        
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE);

        setContentView(R.layout.activity_cam_corder_direct);

        recVideoButton = (Button)this.findViewById(R.id.recVideo_Btn);
        recVideoButton.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent recVideoIntent = new Intent(android.provider.MediaStore.ACTION_VIDEO_CAPTURE);
                try{
                    startActivityForResult(recVideoIntent, VIDEO_CAPTURED);
                } catch(ActivityNotFoundException e) {
                    e.printStackTrace();
                }
            }

        });

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == RESULT_OK && requestCode == VIDEO_CAPTURED) {
            videoFileUri = data.getData();
        }
    }

	
}
