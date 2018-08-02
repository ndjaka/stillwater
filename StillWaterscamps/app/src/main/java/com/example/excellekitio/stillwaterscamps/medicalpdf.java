package com.example.excellekitio.stillwaterscamps;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.barteksc.pdfviewer.PDFView;

public class medicalpdf extends AppCompatActivity {
    PDFView pdf;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicalpdf);
        pdf=(PDFView)findViewById(R.id.pdf);
        pdf.fromAsset("release.pdf").load();

    }
}
