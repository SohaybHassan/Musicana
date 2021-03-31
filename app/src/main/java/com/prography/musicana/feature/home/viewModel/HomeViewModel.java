package com.prography.musicana.feature.home.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import com.prography.musicana.feature.home.model.home.HomeModel;
import com.prography.musicana.feature.home.model.search.SearchMolde;
import com.prography.musicana.feature.home.presenter.HomePresenter;

public class HomeViewModel extends AndroidViewModel {
    HomePresenter homePresenter;

    public HomeViewModel(@NonNull Application application) {
        super(application);
        homePresenter = HomePresenter.getInstance();
    }


    public LiveData<HomeModel> getHomeData(String vpage, String cpage) {
        return homePresenter.getHomeData(vpage, cpage);
    }

    public LiveData<SearchMolde> getResule(String q, String nextPage) {
        return homePresenter.getDataSearch(q, nextPage);
    }

    public void removeObserver(LifecycleOwner owner) {
        homePresenter.removeObserver(owner);
    }
}
