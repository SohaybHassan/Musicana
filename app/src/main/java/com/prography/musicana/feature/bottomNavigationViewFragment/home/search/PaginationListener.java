package com.prography.musicana.feature.bottomNavigationViewFragment.home.search;

import android.util.Log;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public abstract class PaginationListener extends RecyclerView.OnScrollListener {


    public static final String START_PAGE = "";
    private static final int PAGE_SIZE = 21;

    @NonNull
    private LinearLayoutManager linearLayoutManager;

    public PaginationListener(@NonNull LinearLayoutManager layoutManager) {
        this.linearLayoutManager = layoutManager;
    }


    @Override
    public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        int visibelItemCount = linearLayoutManager.getChildCount();
        Log.d("TAG", "onScrolled:  visibelItemCount  " + visibelItemCount);
        int totalItemCount = linearLayoutManager.getItemCount();
        Log.d("TAG", "onScrolled:  totalItemCount " + totalItemCount);
        int firstVisibleItemPosition = linearLayoutManager.findFirstVisibleItemPosition();
        Log.d("TAG", "onScrolled:  firstVisibleItemPosition  " + firstVisibleItemPosition);
        if (!isLoading() && !isLastPage()) {
            if (visibelItemCount + firstVisibleItemPosition >= totalItemCount && firstVisibleItemPosition >= 0 && totalItemCount >= PAGE_SIZE) {
                loadMoreItems();
            }
        }
    }


    protected abstract void loadMoreItems();

    public abstract boolean isLastPage();

    public abstract boolean isLoading();
}
