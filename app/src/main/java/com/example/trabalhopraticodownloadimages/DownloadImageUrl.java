package com.example.trabalhopraticodownloadimages;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

    // Class responsible for downloading an image from the Web'
    // Bitmap is responsible for returning the image itself rather than the content of a website.
    public class DownloadImageUrl extends AsyncTask<String, Void, Bitmap> {

    ImageView downloadedImg;

    public DownloadImageUrl(ImageView downloadedImg) {
        this.downloadedImg = downloadedImg;
    }

    @Override
    protected Bitmap doInBackground(String... urls) {
        // Logic below: download the image in one go,
        // then convert the data downloaded into a Bitmap image.
        // After, it will be possible to set the Bitmap image to imageView.

        // Create a URL...
        try {
            URL url = new URL(urls[0]);

            // Creates a URL connection...
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Connect to connection
            connection.connect();

            // Download the whole inputStream in one go.
            InputStream inputStream = connection.getInputStream();

            // Finally, convert inputStream to bitmap.
            // It converts the image that's been downloaded...
            // into an image that we can work with.
            Bitmap myBitmap = BitmapFactory.decodeStream(inputStream);

            // Afterall, returns myBitmap
            return myBitmap;

        } catch (MalformedURLException e) {

            e.printStackTrace();

            // IOException = Input/Output exception
            // Occurs when asking for permission to use Internet is forgotten
            // Or if user does not have internet connection
        } catch (IOException e) {

            e.printStackTrace();
        }

        return null;
    }
        @Override
        protected void onPostExecute(Bitmap myBitmap) {
            downloadedImg.setImageBitmap(myBitmap);
        }
}
