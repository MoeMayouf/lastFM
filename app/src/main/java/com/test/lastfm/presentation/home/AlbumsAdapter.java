package com.test.lastfm.presentation.home;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.test.lastfm.R;
import com.test.lastfm.data.model.albumResults.Album;
import com.test.lastfm.data.model.albumResults.Image;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AlbumsAdapter extends RecyclerView.Adapter<AlbumsAdapter.AlbumViewHolder> {

    private final List<Album> albums;

    private static OnAlbumSelectedListener listener = null;

    public AlbumsAdapter(OnAlbumSelectedListener listener) {
        this.albums = new ArrayList<>();
        AlbumsAdapter.listener = listener;
    }

    public void setData(List<Album> albums) {
        this.albums.clear();
        this.albums.addAll(albums);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public AlbumViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        return new AlbumViewHolder(layoutInflater.inflate(R.layout.row,viewGroup,false), listener);
    }

    @Override
    public void onBindViewHolder(@NonNull AlbumViewHolder albumViewHolder, int position) {
        albumViewHolder.bind(albums.get(position));
    }

    @Override
    public int getItemCount() {
        return albums.size();
    }

    public void addAll(List<Album> albums) {
        this.albums.addAll(albums);
        notifyItemRangeInserted(this.albums.size()-albums.size(),albums.size());
    }

    public static class AlbumViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tvAlbum)
        TextView tvAlbum;
        @BindView(R.id.tvArtist)
        TextView tvArtist;
        OnAlbumSelectedListener onAlbumSelectedListener;
        @BindView(R.id.ivAlbumArt)
        ImageView ivAlbumArt;

        AlbumViewHolder(View itemView, OnAlbumSelectedListener onAlbumSelectedListener) {
            super(itemView);
            this.onAlbumSelectedListener = onAlbumSelectedListener;
            ButterKnife.bind(this,itemView);
        }

        void bind(Album album) {
            tvAlbum.setText(album.getName());
            tvArtist.setText(album.getArtist());
            itemView.setOnClickListener(v -> onAlbumSelectedListener.onAlbumSelected(album));
            Image largeImage = album.getImage().get(album.getImage().size() - 1);
            if (largeImage != null && !largeImage.getText().isEmpty()) {
                Picasso.get()
                        .load(largeImage.getText())
                        .error(R.mipmap.ic_launcher_round)
                        .into(ivAlbumArt);
            } else {
                Picasso.get().load(R.mipmap.ic_launcher_round).into(ivAlbumArt);
            }
        }


        public static void getAlbumArt(ImageView imageView, List<Image> imageList) {
            Image largeImage = imageList.get(imageList.size() - 1);
            if (largeImage != null && !largeImage.getText().isEmpty()) {
                Picasso.get()
                        .load(largeImage.getText())
                        .error(R.mipmap.ic_launcher_round)
                        .into(imageView);
            } else {
                Picasso.get().load(R.mipmap.ic_launcher_round).into(imageView);
            }
        }
    }
}
