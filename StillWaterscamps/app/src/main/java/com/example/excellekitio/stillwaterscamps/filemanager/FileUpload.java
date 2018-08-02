package com.example.excellekitio.stillwaterscamps.filemanager;

import android.app.ProgressDialog;
import android.util.Log;

import com.example.excellekitio.stillwaterscamps.webserviceinvoker.CallWebService;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestHandle;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;

import java.io.File;



/**
 * Created by emmanuel on 02/08/2016.
 */
public class FileUpload {

    public void uploadFile(String selectedFile, final ProgressDialog mProgressDialog) {


        String url = CallWebService.URL_UPLOAD_SERVLET;
        AsyncHttpClient httpClient = new AsyncHttpClient();
        String resultat = "false";

        //////////System.out.println("Début d'upload du fichier// : " + selectedFile + " ou encore " + fileName);

        RequestParams param = new RequestParams();
        try {
            param.put("file", new File(selectedFile));
            param.put("fileName", new File(selectedFile).getName());

            httpClient.setTimeout(CallWebService.TIME_OUT);
            RequestHandle rh = httpClient.post(url, param, new AsyncHttpResponseHandler() {

                        @Override
                        public void onStart() {
                            super.onStart();
                        }

                        public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                            super.onSuccess(statusCode, headers, responseBody);
                            Log.i("ck", "success>" + statusCode);
                            mProgressDialog.cancel();
                        }

                        public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                            error.printStackTrace();
                            mProgressDialog.cancel();

                        }
                    }
            );

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
