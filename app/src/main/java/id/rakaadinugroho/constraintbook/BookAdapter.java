package id.rakaadinugroho.constraintbook;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import id.rakaadinugroho.constraintbook.model.Item;

/**
 * Created by Raka on 1/9/18.
 */

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookHolder> {
    Context context;
    List<Item> bookData;
    public BookAdapter(Context context, List<Item> bookData) {
        this.context = context;
        this.bookData = bookData;
    }
    @Override
    public BookHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view   = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_book, parent, false);
        return new BookHolder(view);
    }
    @Override
    public void onBindViewHolder(BookHolder holder, int position) {
        Item book   = bookData.get(position);
        Picasso.with(context).load(book.getVolumeInfo().getImageLinks().getThumbnail()).into(holder.thumbnail);
        holder.title.setText(book.getVolumeInfo().getTitle());
        holder.author.setText(book.getVolumeInfo().getAuthors().get(0));
        //holder.description.setText(book.getVolumeInfo().getDescription().substring(0, 30));
    }
    @Override
    public int getItemCount() {
        return bookData.size();
    }
    class BookHolder extends RecyclerView.ViewHolder{
        TextView title, author, description;
        ImageView thumbnail;
        public BookHolder(View itemView) {
            super(itemView);
            title   = itemView.findViewById(R.id.book_title);
            author  = itemView.findViewById(R.id.book_author);
            description = itemView.findViewById(R.id.book_desc);
            thumbnail   = itemView.findViewById(R.id.book_image);
        }
    }
}
