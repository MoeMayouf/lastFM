package com.test.lastfm.presentation.albumdetails;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.test.lastfm.R;
import com.test.lastfm.common.Constants;
import com.test.lastfm.data.model.albumDetails.AlbumDetails;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.AndroidInjection;

public class AlbumDetailsActivity extends AppCompatActivity implements AlbumDetailView {

    @Inject
    AlbumDetailsPresenter albumDetailsPresenter;

    @BindView(R.id.progress_bar)
    ProgressBar progressBar;

    @BindView(R.id.tv_album_name)
    TextView tvAlbumName;

    @BindView(R.id.tv_album_artist)
    TextView tvAlbumArtist;

    @BindView(R.id.tv_wiki)
    TextView tvWiki;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album_detail);
        ButterKnife.bind(this);
        try {
            albumDetailsPresenter.attach(this);
            albumDetailsPresenter.getAlbumDetails(getIntent().getStringExtra(Constants.KEY_ALBUM_NAME),
                    getIntent().getStringExtra(Constants.KEY_ALBUM_ARTIST));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void displayProgressbar() {
        progressBar.setVisibility(View.VISIBLE);
        tvAlbumName.setVisibility(View.GONE);
        tvAlbumArtist.setVisibility(View.GONE);
        tvWiki.setVisibility(View.GONE);
    }

    @Override
    public void displayAlbumDetail(AlbumDetails albumDetails) {
        progressBar.setVisibility(View.GONE);
        tvAlbumName.setVisibility(View.VISIBLE);
        tvAlbumArtist.setVisibility(View.VISIBLE);
        tvWiki.setVisibility(View.VISIBLE);

        tvAlbumName.setText(albumDetails.getAlbum().getName());
        tvAlbumArtist.setText(albumDetails.getAlbum().getArtist());
        tvWiki.setText(albumDetails.getAlbum().getWiki().getSummary());

    }

    @Override
    protected void onDestroy() {
        albumDetailsPresenter.detach();
        super.onDestroy();
    }

    @Override
    public void displayError(String errorMessage) {
        progressBar.setVisibility(View.GONE);
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show();
    }
}
