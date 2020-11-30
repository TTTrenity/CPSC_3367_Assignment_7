package com.ualr.recyclerviewassignment;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SharedViewModel extends ViewModel {
    public MutableLiveData<Inbox> item = new MutableLiveData<>();

    public Inbox getItem() {
        return item.getValue();
    }

    public void setItem(Inbox item) {
        this.item.setValue(item);
    }
}
