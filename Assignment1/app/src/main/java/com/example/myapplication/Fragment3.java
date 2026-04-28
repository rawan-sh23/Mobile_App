package com.example.myapplication;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.databinding.Fragment3Binding;

public class Fragment3 extends Fragment {

    private Fragment3Binding binding;
    private OnFragment3Listener listener;

    // Interface للتواصل مع الـ Activity
    public interface OnFragment3Listener {
        void onCheckboxStateChanged(boolean isChecked);
    }

    public Fragment3() {
        // Required empty public constructor
    }

    // Factory method لاستقبال البيانات من الـ Activity
    public static Fragment3 newInstance(String name) {
        Fragment3 fragment = new Fragment3();
        Bundle args = new Bundle();
        args.putString("name", name);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof OnFragment3Listener) {
            listener = (OnFragment3Listener) context;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = Fragment3Binding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // عرض الاسم فقط
        if (getArguments() != null) {
            String name = getArguments().getString("name");
            binding.textViewName.setText("Welcome " + name);
        }

        // التعامل مع حالة الـ CheckBox
        binding.checkBoxConfirm.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (listener != null) {
                listener.onCheckboxStateChanged(isChecked);
            }
        });
    }
}