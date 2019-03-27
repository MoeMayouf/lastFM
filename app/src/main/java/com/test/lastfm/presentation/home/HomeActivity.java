package com.test.lastfm.presentation.home;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.test.lastfm.R;
import com.test.lastfm.common.Constants;
import com.test.lastfm.common.PaginationListener;
import com.test.lastfm.data.model.albumResults.Album;
import com.test.lastfm.presentation.albumdetails.AlbumDetailsActivity;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dagger.android.AndroidInjection;

public class HomeActivity extends AppCompatActivity implements OnAlbumSelectedListener,
        PaginationListener.PaginationStateListener, HomeView {

    @Inject
    HomePresenter homePresenter;
    private String albumName;

    AlbumsAdapter albumsAdapter;

    @BindView(R.id.rvAlbums)
    RecyclerView recyclerView;

    @BindView(R.id.tv_message)
    TextView tvMessage;
    @BindView(R.id.btn_retry)
    Button btnRetry;
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;
    @BindView(R.id.ll_message_container)
    LinearLayout llMessageContainer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        albumsAdapter = new AlbumsAdapter(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(albumsAdapter);
        recyclerView.addOnScrollListener(new PaginationListener(linearLayoutManager, this));
        homePresenter.attach(this);
    }
    @OnClick(R.id.btn_retry)
    public void retry(){
        homePresenter.getAlbums(albumName,true);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (intent.getAction() != null && intent.getAction().equals(Intent.ACTION_SEARCH)) {
            try {
                albumName = intent.getStringExtra(SearchManager.QUERY);
                homePresenter.getFreshData(albumName);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);
        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        if (searchManager != null) {
            searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        }
        return true;
    }

    @Override
    public boolean isLoading() {
        return homePresenter.isLoading();
    }

    @Override
    public boolean isLastPage() {
        return homePresenter.isLastPage();
    }

    @Override
    public void loadMoreItems()  {
        homePresenter.loadMoreAlbums();

    }

    @Override
    public void onAlbumSelected(Album album) {
        Intent intent = new Intent(this,
                AlbumDetailsActivity.class);
        intent.putExtra(Constants.KEY_ALBUM_NAME, album.getName());
        intent.putExtra(Constants.KEY_ALBUM_ARTIST, album.getArtist());
        startActivity(intent);
    }

    @Override
    public void displayAlbums(List<Album> albums) {
        llMessageContainer.setVisibility(View.GONE);
        progressBar.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
        albumsAdapter.addAll(albums);

    }

    @Override
    public void displayError(String errorMessage) {
        llMessageContainer.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
        progressBar.setVisibility(View.GONE);
        tvMessage.setText(errorMessage);
    }

    @Override
    protected void onDestroy() {
        homePresenter.detach();
        super.onDestroy();
    }

    @Override
    public void displayProgressbar() {
        recyclerView.setVisibility(View.GONE);
        llMessageContainer.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void displayToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
