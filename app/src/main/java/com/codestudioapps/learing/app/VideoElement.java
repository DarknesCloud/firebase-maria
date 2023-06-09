package com.codestudioapps.learing.app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;
public class VideoElement extends AppCompatActivity {
    private VideoView videoView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_element);



        videoView = findViewById(R.id.video_view);
        // Establecer la ruta del video
        String videoPath = "android.resource://" + getPackageName() + "/raw/video_example";
// Configurar el controlador de medios
        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView);
        videoView.setMediaController(mediaController);

        // Establecer la URI del video
        Uri uri = Uri.parse(videoPath);
        videoView.setVideoURI(uri);

        // Iniciar la reproducción del video
        videoView.start();
    }
}