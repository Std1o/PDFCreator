package com.stdio.pdfcreator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.gkemon.XMLtoPDF.PdfGenerator;
import com.gkemon.XMLtoPDF.PdfGeneratorListener;
import com.gkemon.XMLtoPDF.model.FailureResponse;
import com.gkemon.XMLtoPDF.model.SuccessResponse;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View v) {
        createPdf();
    }

    private void createPdf() {
        PdfGenerator.getBuilder()
                .setContext(this)
                .fromLayoutXMLSource()
                .fromLayoutXML(R.layout.activity_main)//your xml
                .setPageSize(PdfGenerator.PageSize.A4)
                .setFileName("Test-PDF")
                .setFolderName(getFilesDir().getAbsolutePath())
                .openPDFafterGeneration(true)
                /* It true then the generated pdf will be shown after generated. */
                .build(new PdfGeneratorListener() {
                    @Override
                    public void onFailure(FailureResponse failureResponse) {
                        super.onFailure(failureResponse);
                    }
                    @Override
                    public void onStartPDFGeneration() {
                        /*When PDF generation begins to start*/
                    }

                    @Override
                    public void onFinishPDFGeneration() {
                        /*When PDF generation is finished*/
                    }

                    @Override
                    public void showLog(String log) {
                        super.showLog(log);
                        /*It shows logs of events inside the pdf generation process*/
                    }

                    @Override
                    public void onSuccess(SuccessResponse response) {
                        super.onSuccess(response);
                        Toast.makeText(MainActivity.this, response.getPath(), Toast.LENGTH_LONG).show();
                    }
                });
    }
}
