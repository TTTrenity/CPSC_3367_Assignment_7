package com.ualr.recyclerviewassignment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private FloatingActionButton mFAB;
    private InboxListFragment mInboxFrag;
    private SharedViewModel model;
    private Inbox contact;
    private Fragment fragment;
    private Snackbar snackbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initComponent();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.delete_button:
                mInboxFrag.deleteItem();
                snackbar.show();
                return true;
            case R.id.forward_button:
                contact = mInboxFrag.getItem();
                model.setItem(contact);
                open_fragment();
                return true;
            default: return super.onOptionsItemSelected(item);
        }
    }

    private void initComponent() {
        mInboxFrag = (InboxListFragment) getSupportFragmentManager().findFragmentById(R.id.inbox_list_fragment);
        model = new ViewModelProvider(this).get(SharedViewModel.class);
        CoordinatorLayout parentView = findViewById(R.id.lyt_parent);
        String msg = getResources().getString(R.string.snackbar_message);
        int duration = Snackbar.LENGTH_LONG;
        snackbar = Snackbar.make(parentView, msg, duration);
        mFAB = findViewById(R.id.fab);
        mFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mInboxFrag.addNewItem();
                mInboxFrag.scrollToPosition(0);
            }
        });
    }

    public void open_fragment() {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        fragment = new ForwardMessageFragment();
        ft.replace(R.id.forward_fragment_holder, fragment);
        ft.commit();
    }

    public void close_fragment() {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.remove(fragment);
        ft.commit();
        model = new ViewModelProvider(this).get(SharedViewModel.class);
        contact = model.getItem();
        mInboxFrag.addNewItem(contact);
        mInboxFrag.refreshRecycler();
        mInboxFrag.scrollToPosition(0);
    }

}