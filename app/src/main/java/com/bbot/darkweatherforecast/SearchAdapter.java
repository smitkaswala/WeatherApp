package com.bbot.darkweatherforecast;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Collections;
import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.Holder>{

    List<AddLocationClass> addLocationClass;
    public Activity context_9;
    PrefManager prefManager;


    public SearchAdapter(List<AddLocationClass> addLocationClass , Activity context_9){
        this.addLocationClass = addLocationClass;
        this.context_9 = context_9;
        Collections.reverse(addLocationClass);
        prefManager = new PrefManager(context_9);

    }

    @NonNull
    @Override
    public SearchAdapter.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context_9).inflate(R.layout.list_item_locationitem,parent,false);
        return new Holder(view);
    }


    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull SearchAdapter.Holder holder, int position)
    {
        AddLocationClass list9 = addLocationClass.get(position);


            holder.tvlocationName.setText(list9.city);
            holder.tvarea.setText(list9.stateloca + "," + list9.country);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (position == 0) {
//                    Utils.saveUnitToDefaults(context_9, Constant.IS_DEAFAULT_LOCATION, 0);
//                } else {
//                    Utils.saveUnitToDefaults(context_9, Constant.IS_DEAFAULT_LOCATION, 1);
//                }

                Intent intent = new Intent();
                intent.putExtra("key" , addLocationClass.get(position).getKey());
                intent.putExtra("name" , list9.getCity() + "," + list9.getCountry());
                prefManager.getKey( addLocationClass.get(position).getKey());
                context_9.setResult(11,intent);
                context_9.finish();

            }

        });

    }

    @Override
    public int getItemCount() {
        return addLocationClass.size();
    }

    public static class Holder extends RecyclerView.ViewHolder {

        TextView tvlocationName , tvarea ;

        public Holder(@NonNull View itemView) {
            super(itemView);

            tvlocationName = itemView.findViewById(R.id.tvlocationName);
            tvarea = itemView.findViewById(R.id.tvarea);

        }
    }

}
