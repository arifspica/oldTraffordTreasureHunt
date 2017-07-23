package tech.itik.magpie;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

/**
 * Created by aliakbars on 23/07/17.
 */

public interface MagpieAPI {

    @GET("get_question")
    Call<Question> getQuestion(@Query("username") String username,
                               @Query("themeId") int themeId);

    @Multipart
    @POST("answer")
    Call<Answer> submitAnswer(@Part("image") MultipartBody.Part image,
                              @Part("username") RequestBody username,
                              @Part("themeId") RequestBody themeId);
}
