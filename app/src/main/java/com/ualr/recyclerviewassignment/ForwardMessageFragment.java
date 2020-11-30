package com.ualr.recyclerviewassignment;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.List;

public class ForwardMessageFragment extends Fragment {
    private EditText to_et;
    private EditText name_et;
    private EditText text_et;
    private Button send_btn;
    private Inbox item;
    private SharedViewModel model;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.forward_message_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        initComponent(view);

        send_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                send_email();
            }
        });
    }

    private void initComponent(View view) {
        to_et = view.findViewById(R.id.to_et);
        name_et = view.findViewById(R.id.name_et);
        text_et = view.findViewById(R.id.text_et);
        send_btn = view.findViewById(R.id.send_btn);
        model = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        item = model.getItem();

        to_et.setText(item.getEmail());
        name_et.setText(item.getFrom());
        text_et.setText(item.getMessage());
    }

    private void send_email() {
        item.setEmail(to_et.getText().toString());
        item.setFrom(name_et.getText().toString());
        item.setMessage(text_et.getText().toString());
        item.setSelected(false);
        model.setItem(item);
        ((MainActivity)getActivity()).close_fragment();
    }
}
