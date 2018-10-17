package in.ac.iiitkota.iiitk_erp.Utilities;

import android.content.Context;
import android.util.Log;

import java.io.File;
import java.io.IOException;

import okhttp3.Cache;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Server {

    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");
    public  OkHttpClient client = new OkHttpClient();

    public Server(Context context){
        int cacheSize = 10 * 1024 * 1024; // 10 MiB
        File cacheDirectory=context.getCacheDir();
        Cache cache = new Cache(cacheDirectory, cacheSize);

        client = new OkHttpClient.Builder()
                .cache(cache)
                .build();
    }

    public  String post(String url, String json) throws IOException {
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .header("Content-Type","application/x-www-form-urlencoded")
                .post(body)
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    public String post(String url) throws IOException {
        RequestBody body = RequestBody.create(JSON, "");
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .header("Cache-Control","max-stale=3600")
                .build();
        Response response = client.newCall(request).execute();
        Log.d("Server", "response.cacheResponse():" + response.cacheResponse());
        Log.d("Server", "response.networkResponse():" + response.networkResponse());
        return  response.body().string();
    }

    public  String get(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .header("Cache-Control","max-stale=3600")
                .build();
        Response response = client.newCall(request).execute();
        Log.d("Server", "response.cacheResponse():" + response.cacheResponse());
        Log.d("Server", "response.networkResponse():" + response.networkResponse());
        return  response.body().string();
    }

}
