package com.example.searchbot.Pagination;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.ItemKeyedDataSource;
import androidx.paging.PagedList;

import com.example.searchbot.Classes.ImageData;

public class ItemViewModel extends ViewModel {
    LiveData<PagedList<ImageData.items>> itemPagedList;
    LiveData<ItemKeyedDataSource<Integer, ImageData.items>> liveDataSource;

    public ItemViewModel() {
        ItemDataSourceFactory itemDataSourceFactory = new ItemDataSourceFactory();

        liveDataSource = itemDataSourceFactory.getItemLiveDataSource();

        PagedList.Config pagedListConfig =
                (new PagedList.Config.Builder())
                .setEnablePlaceholders(false)
                .setPageSize(ItemDataSource.PAGE_SIZE).build();

        itemPagedList = (new LivePagedListBuilder(itemDataSourceFactory, pagedListConfig))
                .build();
    }
}
