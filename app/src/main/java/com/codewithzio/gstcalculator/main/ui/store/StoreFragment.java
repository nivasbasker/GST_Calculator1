package com.codewithzio.gstcalculator.main.ui.store;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.codewithzio.gstcalculator.AdapterProducts;
import com.codewithzio.gstcalculator.ModelProducts;
import com.codewithzio.gstcalculator.ProductDetails;
import com.codewithzio.gstcalculator.R;
import com.codewithzio.gstcalculator.StoreDB;
import com.codewithzio.gstcalculator.databinding.FragmentStoreBinding;

import java.util.List;

public class StoreFragment extends Fragment {

    private StoreViewModel storeViewModel;
    private FragmentStoreBinding binding;

    private RecyclerView recyclerView;

    String TABLE_NAME = "Products";
    StoreDB database;
    List<ModelProducts> prod_list;

    ImageButton add, edit;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        storeViewModel =
                new ViewModelProvider(this).get(StoreViewModel.class);

        binding = FragmentStoreBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        recyclerView = root.findViewById(R.id.product_list);
        add = root.findViewById(R.id.add_prod);
        edit = root.findViewById(R.id.edit_btn);


        database = new StoreDB(getActivity(), "StoreDB", TABLE_NAME);
        prod_list = database.getAllProducts();

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new AdapterProducts(getContext(), prod_list));

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "Feature coming soon...", Toast.LENGTH_LONG).show();
                //Intent intent = new Intent(getContext(), ProductDetails.class);
                //intent.putExtra("type", type);
                //startActivity(intent);
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), ProductDetails.class);
                intent.putExtra("type", false);
                startActivity(intent);
            }
        });


        //final TextView textView = binding.textNotifications;
        storeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
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