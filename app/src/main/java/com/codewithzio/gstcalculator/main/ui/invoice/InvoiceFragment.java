package com.codewithzio.gstcalculator.main.ui.invoice;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.codewithzio.gstcalculator.AdapterInvoice;
import com.codewithzio.gstcalculator.InvoiceDetails;
import com.codewithzio.gstcalculator.ModelInvoice;
import com.codewithzio.gstcalculator.R;
import com.codewithzio.gstcalculator.StoreDB;
import com.codewithzio.gstcalculator.databinding.FragmentInvoicesBinding;

import java.util.List;

public class InvoiceFragment extends Fragment {

    private InvoiceViewModel invoiceViewModel;
    private FragmentInvoicesBinding binding;

    private RecyclerView recyclerView;

    String TABLE_NAME = "Invoices";
    StoreDB database;
    List<ModelInvoice> Inv_list;

    Button add;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        invoiceViewModel =
                new ViewModelProvider(this).get(InvoiceViewModel.class);

        binding = FragmentInvoicesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        recyclerView = root.findViewById(R.id.inv_list);
        add = root.findViewById(R.id.add_inv);
        database = new StoreDB(getActivity(), "StoreDB", TABLE_NAME);
        Inv_list = database.getAllInv();

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new AdapterInvoice(getContext(), Inv_list));


        //final TextView textView = binding.textDashboard;
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), InvoiceDetails.class);
                //intent.putExtra("type", type);
                startActivity(intent);
            }
        });

        invoiceViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                //textView.setText(s);
            }
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}