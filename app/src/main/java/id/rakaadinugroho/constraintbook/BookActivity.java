package id.rakaadinugroho.constraintbook;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import id.rakaadinugroho.constraintbook.model.Item;
import id.rakaadinugroho.constraintbook.model.ResponseBook;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BookActivity extends AppCompatActivity {
    RecyclerView book_list;
    List<Item> bookData;
    BookAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);
        initViews();
        loadData();
    }

    private void initViews() {
        book_list   = findViewById(R.id.book_list);
        bookData    = new ArrayList<>();
        adapter = new BookAdapter(getApplicationContext(), bookData);
        RecyclerView.LayoutManager layoutManager    = new LinearLayoutManager(getApplicationContext());
        book_list.setLayoutManager(layoutManager);
        book_list.setAdapter(adapter);
    }

    private void loadData() {
        Retrofit retrofit   = new Retrofit.Builder()
                .baseUrl("https://www.googleapis.com/books/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        NetworkServices services    = retrofit.create(NetworkServices.class);
        Call<ResponseBook> mybook   = services.getBook("Programming");
        mybook.enqueue(new Callback<ResponseBook>() {
            @Override
            public void onResponse(Call<ResponseBook> call, Response<ResponseBook> response) {
                if (response.isSuccessful()){
                    bookData.add((Item) response.body().getItems());
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<ResponseBook> call, Throwable t) {
                Toast.makeText(BookActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
