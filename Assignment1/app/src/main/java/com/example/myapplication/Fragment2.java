package com.example.myapplication;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import com.example.myapplication.databinding.Fragment2Binding;

public class Fragment2 extends Fragment {

    private Fragment2Binding binding;
    private OnFragment2Listener listener;

    // Interface للتواصل مع الـ Activity
    public interface OnFragment2Listener {
        void onContinueClicked(String name, String email, String age, String gender);
    }

    public Fragment2() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof OnFragment2Listener) {
            listener = (OnFragment2Listener) context;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = Fragment2Binding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    // هذه الدالة تستدعيها الـ Activity عند الضغط على زرها
    public void sendDataToActivity() {
        String name = binding.editTextName.getText().toString().trim();
        String email = binding.editTextEmail.getText().toString().trim();
        String age = binding.editTextAge.getText().toString().trim();

        // تحديد الجنس
        int selectedId = binding.radioGroupGender.getCheckedRadioButtonId();
        String gender = "";
        if (selectedId != -1) {
            RadioButton selectedRadio = binding.getRoot().findViewById(selectedId);
            gender = selectedRadio.getText().toString();
        }

        // إرسال البيانات للـ Activity
        if (listener != null) {
            listener.onContinueClicked(name, email, age, gender);
        }
    }
}