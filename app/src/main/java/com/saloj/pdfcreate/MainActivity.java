package com.saloj.pdfcreate;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    String[] informationArray = new String[]{"Name", "Company Name", "Address", "Phone", "Email"};
    Button button;
Bitmap bmp ,scaledBitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button);
       // bmp = BitmapFactory.decodeResource(getResources(),R.drawable.image);
// scaledBitmap = Bitmap.createScaledBitmap(bmp,200,50,false);

        ActivityCompat.requestPermissions(this, new String[]{
                Manifest.permission.WRITE_EXTERNAL_STORAGE}, PackageManager.PERMISSION_GRANTED);
        createPdf();

    }

    private void createPdf() {

        button.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View view) {

                PdfDocument myPDFDocument = new PdfDocument();
                Paint myPaint = new Paint();

                PdfDocument.PageInfo myPageInfo1 = new PdfDocument.PageInfo.Builder(250, 400, 1).create();
                PdfDocument.Page myPage1 = myPDFDocument.startPage(myPageInfo1);
                Canvas canvas = myPage1.getCanvas();
     /*   canvas.drawText("Welcome To  PDF Creation Page No. 01",40,50,myPaint);
        myPDFDocument.finishPage(myPage1);


        PdfDocument.PageInfo myPageInfo2 = new PdfDocument.PageInfo.Builder(400,600,1).create();
        PdfDocument.Page myPage2 = myPDFDocument.startPage(myPageInfo2);
        Canvas canvas2 = myPage2.getCanvas();
        canvas2.drawText("Welcome To  PDF Creation Page No. 02",40,50,myPaint);
        myPDFDocument.finishPage(myPage2);*/

     //Image Add in PDF

             //   canvas.drawBitmap(scaledBitmap,40,50,myPaint);

     //Table Formate Code
                myPaint.setTextAlign(Paint.Align.CENTER);
                myPaint.setTextSize(12.0f);
                canvas.drawText("HR Enterprises", myPageInfo1.getPageWidth() / 2, 30, myPaint);


                myPaint.setTextSize(6.0f);
                myPaint.setTextScaleX(1.5f);
                myPaint.setColor(Color.rgb(122, 119, 119));
                canvas.drawText("Ring Road No. 01, Mahadev Ghat Overbridge, Raipur", myPageInfo1.getPageWidth() / 2, 40, myPaint);
                myPaint.setTextScaleX(1f);

                myPaint.setTextAlign(Paint.Align.LEFT);
                myPaint.setTextSize(9.0f);
                myPaint.setColor(Color.rgb(122, 119, 119));
                canvas.drawText("Customer Information", 10, 70, myPaint);

                myPaint.setTextAlign(Paint.Align.LEFT);
                myPaint.setTextSize(8.0f);
                myPaint.setColor(Color.BLACK);

                int startXPosition = 10;
                int endXPosition = myPageInfo1.getPageWidth() - 10;
                int startYPosition = 100;

                for (int i = 0; i < 5; i++) {
                    canvas.drawText(informationArray[i], startXPosition, startYPosition, myPaint);
                    canvas.drawLine(startXPosition, startYPosition + 3, endXPosition, startYPosition + 3, myPaint);
                    startYPosition += 20;
                }

                canvas.drawLine(80, 92, 80, 190, myPaint);
                myPaint.setStyle(Paint.Style.STROKE);
                myPaint.setStrokeWidth(2);
                canvas.drawRect(10, 200, myPageInfo1.getPageWidth() - 10, 300, myPaint);
                canvas.drawLine(85, 200, 85, 300, myPaint);
                canvas.drawLine(163, 200, 163, 300, myPaint);
                myPaint.setStrokeWidth(0);
                myPaint.setStyle(Paint.Style.FILL);

                canvas.drawText("Photo", 35, 250, myPaint);
                canvas.drawText("Photo", 110, 250, myPaint);
                canvas.drawText("Photo", 190, 250, myPaint);

                canvas.drawText("Note", 10, 320, myPaint);
                canvas.drawLine(35, 325, myPageInfo1.getPageWidth() - 10, 325, myPaint);
                canvas.drawLine(10, 345, myPageInfo1.getPageWidth() - 10, 345, myPaint);
                canvas.drawLine(10, 365, myPageInfo1.getPageWidth() - 10, 365, myPaint);



                myPDFDocument.finishPage(myPage1);

               // File file = new File(Environment.getExternalStorageDirectory(), "/imgaddPDF.pdf");
                String storepath = Environment.getExternalStorageDirectory().getPath() + "/Download/imgaddPDF.pdf";
                try {

                    myPDFDocument.writeTo(new FileOutputStream(storepath));

                    Toast.makeText(MainActivity.this, "Done", Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                myPDFDocument.close();

            }
        });

    }
}