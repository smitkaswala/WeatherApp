package com.bbot.darkweatherforecast.TwenteenfourDay;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bbot.darkweatherforecast.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class TwentyFourHourAdapter extends RecyclerView.Adapter<TwentyFourHourAdapter.TwentFourViewHolder> {

    private  Context context;
    private List<TwentyFourModel> twentyFourModelList;

    public TwentyFourHourAdapter(Context context, List<TwentyFourModel> twentyFourModelList) {
        this.context = context;
        this.twentyFourModelList = twentyFourModelList;
    }

    @NonNull
    @Override
    public TwentFourViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.twentyfourhours,parent,false);
        return new TwentFourViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TwentFourViewHolder holder, int position) {
        String i = String.valueOf(twentyFourModelList.get(position).getIcon());
        int j = Integer.parseInt(i);
        int iconID = context.getResources().getIdentifier("a"+i,"drawable",context.getPackageName());


        String date = String.valueOf(twentyFourModelList.get(position).getTime());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");

        Date iconID_3 = null;
        try {
            iconID_3 = dateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        dateFormat = new SimpleDateFormat("h a");



        holder.value.setText(String.valueOf(twentyFourModelList.get(position).getValue()+"⁰"));
        holder.icon.setImageResource(iconID);
        holder.time.setText(dateFormat.format(iconID_3));



    }

    @Override
    public int getItemCount() {
        return twentyFourModelList.size();
    }

    public class TwentFourViewHolder extends RecyclerView.ViewHolder {

        TextView time;
//        TextView time2;
        TextView value;
        ImageView icon;
//        ImageView icon2;
        LinearLayout linearLayout;

        public TwentFourViewHolder(@NonNull View itemView) {
            super(itemView);

            time = itemView.findViewById(R.id.tx_25);
            value = itemView.findViewById(R.id.val_1);
            icon = itemView.findViewById(R.id.icn_2);
//            time2 = itemView.findViewById(R.id.tax_9);
            linearLayout = itemView.findViewById(R.id.linear9);
//            icon2 = itemView.findViewById(R.id.image_btn);

        }
    }
}
