package com.codewithzio.gstcalculator.main.ui.calculator;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.codewithzio.gstcalculator.R;
import com.codewithzio.gstcalculator.databinding.FragmentCalcBinding;
import com.google.android.material.slider.Slider;

public class CalculatorFragment extends Fragment {

    private CalculatorViewModel calculatorViewModel;
    private FragmentCalcBinding binding;

    LinearLayout profit;
    ImageButton interchange;
    Button enableProfit;

    Slider prof, rate;

    TextView field1, field2, label1, label2;
    boolean changed = false;
    float _gst, _profit;
    float _net, _gross;
    EditText amtOutput, amtInput;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        calculatorViewModel =
                new ViewModelProvider(this).get(CalculatorViewModel.class);

        binding = FragmentCalcBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // TODO : removed
        //final TextView textView = binding.textHome;
        calculatorViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                //textView.setText(s);
            }
        });

        enableProfit = root.findViewById(R.id.enableprofit_btn);
        profit = root.findViewById(R.id.profit_layout);
        interchange = root.findViewById(R.id.interchange_btn);
        field1 = root.findViewById(R.id.Field1);
        field2 = root.findViewById(R.id.Field2);
        label1 = root.findViewById(R.id.label1);
        label2 = root.findViewById(R.id.label2);

        rate = root.findViewById(R.id.rate);
        prof = root.findViewById(R.id.prof);

        amtOutput = root.findViewById(R.id.amt_output);
        amtInput = root.findViewById(R.id.amt_input);


        rate.addOnChangeListener(new Slider.OnChangeListener() {
            @Override
            public void onValueChange(@NonNull Slider slider, float value, boolean fromUser) {
                _gst =  value;
                onChange();
            }
        });
        prof.addOnChangeListener(new Slider.OnChangeListener() {
            @Override
            public void onValueChange(@NonNull Slider slider, float value, boolean fromUser) {
                _profit =  value;
                onChange();
            }
        });

        interchange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changed = !changed;
                if (changed) {
                    field1.setText("Gross");
                    label1.setText("Gross Amount");

                    field2.setText("Net");
                    label2.setText("Net Amount");
                } else {
                    field2.setText("Gross");
                    label2.setText("Gross Amount");

                    field1.setText("Net");
                    label1.setText("Net Amount");
                }
            }
        });

        enableProfit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                profit.setVisibility(View.VISIBLE);
                enableProfit.setVisibility(View.GONE);
            }
        });
        return root;
    }

    private void onChange() {
        String val = amtInput.getText().toString();
        if (!val.isEmpty()) {
            if (changed) {
                _gross = Integer.parseInt(val);
                float x =(1 + (_gst / 100) + (_profit / 100));
                _net = _gross / x;
                amtOutput.setText(String.valueOf(_net));
                Log.d("gross", String.valueOf(_gross));
                Log.d("net", String.valueOf(_net));
                Log.d("gst", String.valueOf(x));
            } else {
                _net = Integer.parseInt(val);
                _gross = _net + (_gst * _net / 100) + (_profit * _net / 100);
                amtOutput.setText(String.valueOf(_gross));
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}