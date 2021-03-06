package com.example.googlebook;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

public class NetworkUtils {
    private static final String Log_TAG = "LOG";

    private NetworkUtils(){

    }

    public static ArrayList<Book> fetchBooksData(String queryString){
        // Create URL object
        URL url = createUrl(queryString);

        // Perform HTTP request to the URL and receive a JSON response back
        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            Log.e(Log_TAG, "Problem making the HTTP request.", e);
        }
        // Extract relevant fields from the JSON response and create a list of {@link Earthquake}s
        ArrayList<Book> books = extractFeatureFromJson(jsonResponse);

        // Return the list of {@link Earthquake}s
        return books;
    }

    private static URL createUrl( String query){
        URL url= null;
        if(query != null){
            try{
                url = new URL(query);
            }catch (MalformedURLException e){
                Log.e(Log_TAG, "Problem making url ",e);
            }
        }
        return url;
    }


    /**
     * Make an HTTP request to the given URL and return a String as the response.
     */
    private static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";

        // If the URL is null, then return early.
        if (url == null) {
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // If the request was successful (response code 200),
            // then read the input stream and parse the response.
            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e(Log_TAG, "Error response code: " + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e(Log_TAG, "Problem retrieving the earthquake JSON results.", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                // Closing the input stream could throw an IOException, which is why
                // the makeHttpRequest(URL url) method signature specifies than an IOException
                // could be thrown.
                inputStream.close();
            }
        }
        return jsonResponse;
    }

    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }

    /**
     * Return a list of {@link Book} objects that has been built up from
     * parsing the given JSON response.
     */
    private static ArrayList<Book> extractFeatureFromJson(String bookJSON) {
        // If the JSON string is empty or null, then return early.
        if (TextUtils.isEmpty(bookJSON)) {
            return null;
        }

        // Create an empty ArrayList that we can start adding earthquakes to
        ArrayList<Book> books = new ArrayList<>();

        // Try to parse the JSON response string. If there's a problem with the way the JSON
        // is formatted, a JSONException exception object will be thrown.
        // Catch the exception so the app doesn't crash, and print the error message to the logs.
        try {

            // Create a JSONObject from the JSON response string
            JSONObject baseJsonResponse = new JSONObject(bookJSON);

            // Extract the JSONArray associated with the key called "features",
            // which represents a list of features (or earthquakes).
            if(baseJsonResponse.getJSONArray("items") == null){
                baseJsonResponse = null;
            }
            JSONArray bookArray = baseJsonResponse.getJSONArray("items");


            // For each earthquake in the earthquakeArray, create an {@link Earthquake} object
            for (int i = 0; i < bookArray.length(); i++) {

                // Get a single earthquake at position i within the list of earthquakes
                JSONObject currentBook = bookArray.getJSONObject(i);

                // For a given earthquake, extract the JSONObject associated with the
                // key called "properties", which represents a list of all properties
                // for that earthquake.
                JSONObject volumeInfo = currentBook.getJSONObject("volumeInfo");

                // Extract the value for the key called "mag"
                String title = volumeInfo.getString("title");

                // Extract the value for the key called "place"

                String description = volumeInfo.getString("description");
                String author="" ;
                if(volumeInfo.getJSONArray("authors") == null){
                    author= null;
                }
                for(int j=0; j<volumeInfo.getJSONArray("authors").length(); j++){
                    author= volumeInfo.getJSONArray("authors").getString(j) +", " + author  ;
                }
                String imgLink = volumeInfo.getJSONObject("imageLinks").getString("thumbnail");


                int pageCount = volumeInfo.getInt("pageCount");
                float avrRating = (float) volumeInfo.getDouble("averageRating");
                int ratingCount = volumeInfo.getInt("ratingsCount");



                // Create a new {@link Earthquake} object with the magnitude, location, time,
                // and url from the JSON response.
                Book book = new Book(title, author, description, imgLink, pageCount, avrRating, ratingCount);

                // Add the new {@link Earthquake} to the list of earthquakes.
                books.add(book);
            }

        } catch (JSONException e) {
            // If an error is thrown when executing any of the above statements in the "try" block,
            // catch the exception here, so the app doesn't crash. Print a log message
            // with the message from the exception.
            Log.e("QueryUtils", "Problem parsing the book JSON results", e);
        }

        return books;
    }
}
