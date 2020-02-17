package com.example.searchbot.Pagination;

import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import androidx.paging.ItemKeyedDataSource;

import com.example.searchbot.Classes.ImageData;

public class ItemDataSourceFactory extends DataSource.Factory {

    private MutableLiveData<ItemKeyedDataSource<Integer, ImageData.items>> itemLiveDataSource = new MutableLiveData<>();

    @Override
    public DataSource<Integer, ImageData.items> create() {
        ItemDataSource itemDataSource = new ItemDataSource();

        itemLiveDataSource.postValue(itemDataSource);

        return itemDataSource;
    }

    public MutableLiveData<ItemKeyedDataSource<Integer, ImageData.items>> getItemLiveDataSource() {
        return itemLiveDataSource;
    }

}
