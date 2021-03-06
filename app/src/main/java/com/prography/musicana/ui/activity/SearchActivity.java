package com.prography.musicana.ui.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;
import android.widget.Toast;

import com.prography.musicana.adapter.SearchAdapter;
import com.prography.musicana.data.search.SearchData;
import com.prography.musicana.utils.SharedPreferencesHelper;
import com.prography.musicana.databinding.ActivitySearchBinding;
import com.prography.musicana.data.search.Result;
import com.prography.musicana.data.search.SearchMolde;
import com.prography.musicana.viewmodel.HomeViewModel;
import com.prography.musicana.viewmodel.StatusViewModel;

import java.util.ArrayList;


public class SearchActivity extends AppCompatActivity {


    private ActivitySearchBinding binding;
    private SharedPreferencesHelper sharedPreferencesHelper;
    private StatusViewModel statusViewModel;
    private static final String TAG = SearchActivity.class.getSimpleName();
    private HomeViewModel homeViewModel;
    private SearchAdapter searchAdapter;
    private LinearLayoutManager linearLayoutManager;
    private ArrayList<Result> items = new ArrayList<>();

    private boolean isLastPage = false;

    private String nextpage = "";
    private String oldpage = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySearchBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        init();

        linearLayoutManager = new LinearLayoutManager(this);
        binding.recyclerViewSearch.setLayoutManager(linearLayoutManager);
        searchAdapter = new SearchAdapter(items, this);
        binding.recyclerViewSearch.setAdapter(searchAdapter);

        editorActionListener();
        onScroll();
    }

    public void editorActionListener() {
        binding.edSearch.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                binding.progressBar.setVisibility(View.VISIBLE);
                search(binding.edSearch.getText().toString());
                return true;
            }
            return false;
        });
    }

    public void onScroll() {
        binding.recyclerViewSearch.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                int visibleItemCount = linearLayoutManager.getChildCount();
                int totalItemCount = linearLayoutManager.getItemCount();
                int firstVisibleItemPosition = linearLayoutManager.findFirstVisibleItemPosition();

                if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount && firstVisibleItemPosition >= 0) {
                    if (oldpage.equals(nextpage)) {
                        Log.d(TAG, "onScrolled nextpage addOnScrollListener: " + nextpage);
                        Log.d(TAG, "onScrolled oldpage addOnScrollListener: " + oldpage);
                        Toast.makeText(SearchActivity.this, " coulde not do request", Toast.LENGTH_SHORT).show();
                        int cont = 1;
                        Log.d(TAG, "onScrolled:  coulde not do request" + cont++);
                    } else {
                        Log.d(TAG, "onScrolled nextpage addOnScrollListener: " + nextpage);
                        Log.d(TAG, "onScrolled oldpage addOnScrollListener: " + oldpage);
                        isLastPage = true;
                        items.clear();
                        search(binding.edSearch.getText().toString());
                    }


                }
            }
        });
    }

    public void init() {
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        sharedPreferencesHelper = new SharedPreferencesHelper();
        statusViewModel = new ViewModelProvider(this).get(StatusViewModel.class);
    }

    public void search(String text) {
        homeViewModel.getResule(text, nextpage).observe(SearchActivity.this, searchMolde -> {
            if (searchMolde != null) {

                Log.d(TAG, "onScrolled getNextPage search: " + searchMolde.getNextPage());

                Log.d(TAG, "onScrolled nextpage search: " + nextpage);
                Log.d(TAG, "onScrolled oldpage search: " + oldpage);

                oldpage = nextpage;
                nextpage = null;
                nextpage = searchMolde.getNextPage();
                searchMolde.setNextPage(null);
                Log.d(TAG, "onScrolled getNextPage search: " + searchMolde.getNextPage());
                Log.d(TAG, "onScrolled nextpage search: " + nextpage);
                Log.d(TAG, "onScrolled oldpage search: " + oldpage);

                binding.progressBar.setVisibility(View.GONE);
                for (int i = 0; i < searchMolde.getResults().size(); i++) {
                    items.add(searchMolde.getResults().get(i));
                }
                Toast.makeText(SearchActivity.this, "size: " + items.size(), Toast.LENGTH_SHORT).show();
                searchAdapter.notifyDataSetChanged();
            } else {

                Log.d(TAG, "search: no data");
                binding.progressBar.setVisibility(View.GONE);

            }
        });


    }


}
