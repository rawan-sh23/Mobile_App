package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.myapplication.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity
        implements Fragment2.OnFragment2Listener, Fragment3.OnFragment3Listener {

    private ActivityMainBinding binding;
    private int currentFragment = 1;
    private Fragment2 fragment2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // أول ما يفتح التطبيق يبدأ بـ Fragment1
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, new Fragment1())
                .commit();

        // زر يظهر من البداية
        binding.buttonContinue.setVisibility(View.VISIBLE);
        binding.buttonContinue.setText("Continue");
        binding.buttonContinue.setEnabled(true);

        // ربط زر الـ Activity
        binding.buttonContinue.setOnClickListener(view -> {
            if (currentFragment == 1) {
                fragment2 = new Fragment2();
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, fragment2)
                        .addToBackStack(null)
                        .commit();
                currentFragment = 2;
            } else if (currentFragment == 2) {
                // استدعاء إرسال البيانات من Fragment2
                if (fragment2 != null) {
                    fragment2.sendDataToActivity();
                }
            } else if (currentFragment == 3) {
                // هنا ممكن تنهي التطبيق أو تعرض رسالة
                Toast.makeText(this, "Finished", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // استقبال البيانات من Fragment2
    @Override
    public void onContinueClicked(String name, String email, String age, String gender) {
        Toast.makeText(this,
                "Name: " + name + "\nEmail: " + email + "\nAge: " + age + "\nGender: " + gender,
                Toast.LENGTH_LONG).show();

        Fragment3 fragment3 = Fragment3.newInstance(name);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, fragment3)
                .addToBackStack(null)
                .commit();

        currentFragment = 3;

        // عند الدخول إلى Fragment3 الزر يبدأ معطل
        binding.buttonContinue.setEnabled(false);
        binding.buttonContinue.setText("Continue");
    }

    // متابعة حالة الـ CheckBox من Fragment3
    @Override
    public void onCheckboxStateChanged(boolean isChecked) {
        if (isChecked) {
            binding.buttonContinue.setEnabled(true);
            binding.buttonContinue.setText("Finish");
        } else {
            binding.buttonContinue.setEnabled(false);
            binding.buttonContinue.setText("Continue");
        }
    }
}