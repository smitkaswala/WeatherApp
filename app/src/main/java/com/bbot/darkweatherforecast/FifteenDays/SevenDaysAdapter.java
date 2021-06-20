package com.bbot.darkweatherforecast.FifteenDays;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bbot.darkweatherforecast.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class SevenDaysAdapter extends RecyclerView.Adapter<SevenDaysAdapter.SevenDaysViewHolder> {

    private Context context_3;
    private List<SevenDaysModel> sevenDaysModelsList;

    public SevenDaysAdapter(Context context_3, List<SevenDaysModel> sevenDaysModelList){
        this.context_3 = context_3;
        this.sevenDaysModelsList = sevenDaysModelList;
    }

    @NonNull
    @Override
    public SevenDaysAdapter.SevenDaysViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view_3 = LayoutInflater.from(parent.getContext()).inflate(R.layout.sevenday,parent,false);
        return new SevenDaysViewHolder(view_3);
    }

    @Override
    public void onBindViewHolder(@NonNull SevenDaysAdapter.SevenDaysViewHolder holder, int position) {

        String date_1 = String.valueOf(sevenDaysModelsList.get(position).getTime3());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        Date iconID_5 = null;
        try {
            iconID_5 = dateFormat.parse(date_1);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        dateFormat = new SimpleDateFormat("E");

        String date_2 = String.valueOf(sevenDaysModelsList.get(position).getDate3());
        SimpleDateFormat dateFormatt = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        Date iconID_6 = null;
        try {
            iconID_6 = dateFormatt.parse(date_2);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        dateFormatt = new SimpleDateFormat("dd/MM");

        String i = String.valueOf(sevenDaysModelsList.get(position).getIcon3());
        int iconID_4 = context_3.getResources().getIdentifier("a"+i,"drawable",context_3.getPackageName());

        holder.time3.setText(dateFormat.format(iconID_5));
        holder.icon3.setImageResource(iconID_4);
        holder.date3.setText(dateFormatt.format(iconID_6));

    }

    @Override
    public int getItemCount() {
        return sevenDaysModelsList.size();
    }

    public static class SevenDaysViewHolder extends RecyclerView.ViewHolder {

        TextView time3;
        ImageView icon3;
        TextView date3;

        public SevenDaysViewHolder(@NonNull View itemView) {
            super(itemView);

            time3 = itemView.findViewById(R.id.seven_1);
            icon3 = itemView.findViewById(R.id.seven_2);
            date3 = itemView.findViewById(R.id.seven);

        }
    }
}
