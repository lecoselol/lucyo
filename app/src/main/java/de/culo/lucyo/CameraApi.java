package de.culo.lucyo;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;

public final class CameraApi {

    private CameraApi() {
        // hide this
    }

    private static final String IP = "http://192.168.0.121:88/";

    private static final String USERNAME = "user2";

    private static final String PASSWORD = "media2";

    private static final String PREFIX = "/cgi-bin/CGIProxy.fcgi?";

    public interface SnapPicture {
        @GET("/CGIProxy.fcgi?cmd=snapPicture&usr=user2&pwd=media2")
        Call<String> contributors(
                @Body() String body
        );
    }

    public static void takeAPicture() throws IOException {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.0.121:88/cgi-bin/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Create an instance of our GitHub API interface.
        SnapPicture picture = retrofit.create(SnapPicture.class);

        // Create a call instance for looking up Retrofit contributors.
        Call<String> call = picture.contributors("body");

        // Fetch and print a list of the contributors to the library.
        String contributors = call.execute().body();
        System.out.println(contributors);
    }
}
