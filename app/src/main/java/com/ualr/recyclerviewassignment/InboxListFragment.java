package com.ualr.recyclerviewassignment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class InboxListFragment extends Fragment {

    private RecyclerView recyclerView;
    private AdapterListInbox mAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.inbox_list_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        initComponents(view);
    }

    private void initComponents(View view) {
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setHasFixedSize(true);
        List<Inbox> items = DataGenerator.getInboxData(view.getContext());
        mAdapter = new AdapterListInbox(view.getContext(), items);
        recyclerView.setAdapter(mAdapter);
    }

    public void deleteItem() {
        mAdapter.deleteItem();
    }

    public void addNewItem() {
        mAdapter.addNewItem();
    }

    public void addNewItem(Inbox item) {
        mAdapter.addNewItem(item);
    }

    public void refreshRecycler() {
        mAdapter.refreshRecycler();
    }

    public Inbox getItem() {
        return mAdapter.getItem();
    }

    public void scrollToPosition(int position) {
        recyclerView.scrollToPosition(position);
    }
}
