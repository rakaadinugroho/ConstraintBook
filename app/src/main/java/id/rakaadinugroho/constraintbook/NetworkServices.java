package id.rakaadinugroho.constraintbook;

import id.rakaadinugroho.constraintbook.model.ResponseBook;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Raka on 1/9/18.
 */

public interface NetworkServices {
    @GET("volumes")
    Call<ResponseBook> getBook(@Query("q") String books);
}
