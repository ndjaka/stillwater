package com.example.excellekitio.stillwaterscamps.filemanager;

import android.app.ProgressDialog;
import android.content.Context;
import android.icu.text.SimpleDateFormat;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Environment;
import android.os.PowerManager;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by emmanuel on 09/08/2016.
 */
public class DownloadTask extends AsyncTask<String, Integer, String> {


    private final String fileName;
    private Context activity;
    private PowerManager.WakeLock mWakeLock;
    ProgressDialog mProgressDialog;
    int type;

    public DownloadTask(Context activity, ProgressDialog mProgressDialog, String fileName, int type) {
        this.activity = activity;
        this.mProgressDialog = mProgressDialog;
        this.fileName = fileName;
        this.type=type;
    }

    @Override
    protected String doInBackground(String... sUrl) {

        InputStream input = null;
        OutputStream output = null;
        HttpURLConnection connection = null;

        try {
            URL url = new URL(sUrl[0]);
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();

            if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                System.err.println("Server returned HTTP " + connection.getResponseCode()
                        + " " + connection.getResponseMessage()+"\nURL = "+url);
                String erreur = "Erreur: " + connection.getResponseCode()
                        + " " + connection.getResponseMessage();
                ////////////System.out.println(erreur);
                Log.e("e<ERR>","TTTTTTTTTTTTTTTTTTTTTTT"+erreur);
                return erreur;
            }

            // this will be useful to display download percentage
            // might be -1: server did not report the length

            int fileLength = connection.getContentLength();

            // download the file
            input = connection.getInputStream();

            output = new FileOutputStream(getOutputSave(type,fileName));

            byte data[] = new byte[4096];
            long total = 0;
            int count;
            while ((count = input.read(data)) != -1) {
                // allow canceling with back button
                if (isCancelled()) {
                    input.close();
                    return null;
                }
                total += count;
                // publishing the progress....
                if (fileLength > 0) // only if total length is known
                    publishProgress((int) (total * 100 / fileLength));
                output.write(data, 0, count);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return e.toString();
        } finally {
            try {
                if (output != null)
                    output.close();
                if (input != null)
                    input.close();
            } catch (IOException ignored) {
                ignored.printStackTrace();
            }

            if (connection != null)
                connection.disconnect();
        }

        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        // take CPU lock to prevent CPU from going off if the user
        // presses the power button during download
        PowerManager pm = (PowerManager) activity.getSystemService(Context.POWER_SERVICE);
        mWakeLock = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK,
                getClass().getName());
        mWakeLock.acquire();
        mProgressDialog.show();
    }

    @Override
    protected void onProgressUpdate(Integer... progress) {
        super.onProgressUpdate(progress);
        // if we get here, length is known, now set indeterminate to false
        mProgressDialog.setIndeterminate(false);
        mProgressDialog.setMax(100);
        mProgressDialog.setMessage(progress[0]+"%");
        mProgressDialog.setProgress(progress[0]);
    }

    @Override
    protected void onPostExecute(String result) {
        mWakeLock.release();
        mProgressDialog.dismiss();
        if (result != null) {
            Toast.makeText((AppCompatActivity) this.activity, "Erreur de telechargement !\n" + result, Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText((AppCompatActivity) this.activity,"Fichier telecharge !" , Toast.LENGTH_SHORT).show();
        }
    }


    public  static File getOutputSave(int imgType,String fileName)
    {
        // creation du dossier des imgages
        String TYPE="IMG";
        File mediaDirectory =null;
        if(imgType == 1)
        {
            mediaDirectory =   new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),"Still_img");
        } else  if(imgType == 2)
        {
            mediaDirectory =   new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MOVIES),"Still_Video");
            TYPE="VID";
        }
        if (!mediaDirectory.exists()){
            if (! mediaDirectory.mkdirs()){
                Log.d(mediaDirectory.getName(), "failed to create directory");
                return null;
            }
        }

        Long time =Calendar.getInstance().getTimeInMillis();
        File  mediaFile = new File(mediaDirectory.getPath() + File.separator + TYPE+"_"+ time +"_cavani.jpg");
        Log.e(">>DD",mediaDirectory.getPath() + File.separator + TYPE+"_"+ time +fileName);
        return mediaFile;
    }
}
