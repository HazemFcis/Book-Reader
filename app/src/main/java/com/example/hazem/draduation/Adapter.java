package com.example.hazem.draduation;

import android.support.v7.widget.RecyclerView;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import java.util.ArrayList;

/**
 * Created by Hazem on 8/3/2018.
 */

public class Adapter extends RecyclerView.Adapter<Adapter.MovieHolder> {
    Context context;
    ArrayList<Model> BookList = new ArrayList<Model>();
    public static String WidgetInfo;

    public Adapter(Context context, ArrayList<Model> bookList) {
        this.context = context;
        BookList = bookList;
    }

    public static class MovieHolder extends RecyclerView.ViewHolder {
        ImageView poster;
        TextView bookName;
        CardView cardView;
        public MovieHolder(View itemView) {
            super(itemView);
            poster = (ImageView) itemView.findViewById(R.id.home_img);
            bookName=(TextView)itemView.findViewById(R.id.name);
            cardView=(CardView)itemView.findViewById(R.id.carr);
        }
    }

    @Override
    public MovieHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.book_item, parent, false);
        return new MovieHolder(view);
    }

    @Override
    public void onBindViewHolder(MovieHolder holder, final int position) {
        Picasso.with(context).load(BookList.get(position).getImage1()).into(holder.poster);
        holder.bookName.setText(BookList.get(position).getTitle());
        holder.poster.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WidgetInfo="Title: "+BookList.get(position).getTitle()+"\n"+
                        "Date: "+BookList.get(position).getYear()+"\n"+
                "Description: "+BookList.get(position).getDescription();

                Intent intent = new Intent(context, BookDetail.class);
                intent.putExtra("id", BookList.get(position).getID());
                intent.putExtra("title", BookList.get(position).getTitle());
                intent.putExtra("date", BookList.get(position).getYear());
                intent.putExtra("Description", BookList.get(position).getDescription());
                intent.putExtra("img", BookList.get(position).getImage1());
                intent.putExtra("publisher",BookList.get(position).getPublisher());
                intent.putExtra("rate",BookList.get(position).getRate());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);    }  });
    }

    @Override
    public int getItemCount() {
        return BookList.size();
    }

}
