package com.codewithzio.gstcalculator;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdapterProducts extends RecyclerView.Adapter<AdapterProducts.ViewHolder> {

    final Context context;
    final List<ModelProducts> prodList;


    public AdapterProducts(Context context, List<ModelProducts> prodList) {
        this.context = context;
        this.prodList = prodList;
    }

    @NonNull
    @Override
    public AdapterProducts.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.layout_products, parent, false);
        return new AdapterProducts.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        ModelProducts model = prodList.get(position);

        holder.t1.setText(model.getName());
        holder.t2.setText("₹ " + model.getGross() + " /unit");

        holder.t3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ProductDetails.class);
                intent.putExtra("type", true);
                intent.putExtra("name", model.getName());
                intent.putExtra("desc", model.getDesc());
                intent.putExtra("net", model.getNet());
                intent.putExtra("gst", model.getGst());
                intent.putExtra("gross", model.getGross());
                context.startActivity(intent);
            }
        });

    }


    @Override
    public int getItemCount() {
        return prodList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {


        public TextView t1, t2;
        LinearLayout t3;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            t1 = itemView.findViewById(R.id.item_name);
            t2 = itemView.findViewById(R.id.item_gross);
            t3 = itemView.findViewById(R.id.product_laout);

        }
    }
}
