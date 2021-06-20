package com.bbot.darkweatherforecast.FifteenDays;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bbot.darkweatherforecast.R;

import java.util.ArrayList;
import java.util.List;

public class TemperatureAdapter extends RecyclerView.Adapter<TemperatureAdapter.TemperatureViewHolder> {

    private final Context context_5;
    private List<TemperatureModel> temperatureModelList;


    public TemperatureAdapter(Context context_5, List<TemperatureModel> temperatureModelList) {
        this.context_5 = context_5;
        this.temperatureModelList = temperatureModelList;

//        Collections.reverse(Collections.singletonList(temperatureModelList));
    }

    @NonNull
    @Override
    public TemperatureAdapter.TemperatureViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view_5 = LayoutInflater.from(parent.getContext()).inflate(R.layout.temperature, parent, false);
        return new TemperatureViewHolder(view_5);

    }

    @Override
    public void onBindViewHolder(@NonNull TemperatureAdapter.TemperatureViewHolder holder, int position) {

        String i = String.valueOf(temperatureModelList.get(position).getIcon6());
        int j = Integer.parseInt(i);
        int iconID1 = context_5.getResources().getIdentifier("b" + i, "drawable", context_5.getPackageName());

        if (j == 6 || j == 7 || j == 8 || j == 12 || j == 13 || j == 14 || j == 15 || j == 16 || j == 17 || j == 18 || j == 19 || j == 20 || j == 21 ||
                j == 38 || j == 39 || j == 40 || j == 41) {
//            holder.linearLayout3.setBackgroundResource(R.drawable.bg2);
            holder.linearLayout3.setBackgroundResource(R.drawable.bg5);
        } else if (j == 11 || j == 22 || j == 23 || j == 24 || j == 25 || j == 26 || j == 29 || j == 32 || j == 42 || j == 43 || j == 44) {
//            holder.linearLayout3.setBackgroundResource(R.drawable.bg3);
            holder.linearLayout3.setBackgroundResource(R.drawable.bg5);
        } else {
//            holder.linearLayout3.setBackgroundResource(R.drawable.bg4);
            holder.linearLayout3.setBackgroundResource(R.drawable.bg5);
        }


        int[] minarray = new int[7];
        int[] maxarray = new int[7];
//

            for (int i1 = 1; i1 < 8; i1++) {
                temperatureModelList.add(temperatureModelList.get(i1));
                minarray[i1 - 1] = Math.round((float) temperatureModelList.get(i1).getVal5());
                maxarray[i1 - 1] = Math.round((float) temperatureModelList.get(i1).getTemp5(0));
            }


            int maxtemp = getMaxValue(maxarray);
            int mintemp = getMinValue(minarray);

            int heighttext = (temperatureModelList.get(position).temp5) + (temperatureModelList.get(position).val5);
            int height = (context_5.getResources().getDimensionPixelSize(R.dimen.loutbar)) - (heighttext);

            int maindiff = (maxtemp) - (mintemp);
            int gap = Math.round(height / maindiff);
            int eachDiff = Math.round(temperatureModelList.get(position).getTemp5(0) - temperatureModelList.get(position).getVal5());
            int newheight = (eachDiff * gap);



            holder.icon5.getLayoutParams().height = newheight;
//        holder.icon6.setImageResource(iconID1);
            Log.d("progress", "progress: " + newheight);
            holder.icon6.setImageResource(iconID1);
            holder.temp5.setText(String.valueOf(temperatureModelList.get(position).getTemp5(0) + "⁰"));
            holder.val5.setText(String.valueOf(temperatureModelList.get(position).getVal5() + "⁰"));


//        }else {
//
//        }


    }


    @Override
    public int getItemCount() {
        return temperatureModelList.size();
    }

    public static int getMinValue(int[] numbers) {
        int minValue = numbers[0];
        for (int i = 1; i < numbers.length; i++) {
            if (numbers[i] < minValue) {
                minValue = numbers[i];
            }
        }
        return minValue;
    }

    public static int getMaxValue(int[] numbers) {
        int maxValue = numbers[0];
        for (int i = 1; i < numbers.length; i++) {
            if (numbers[i] > maxValue) {
                maxValue = numbers[i];
            }
        }
        return maxValue;
    }

    class TemperatureViewHolder extends RecyclerView.ViewHolder {

        TextView temp5;
        TextView val5;
        //         LinearLayout linearLayout;
        ImageView icon5;
        ImageView icon6;
        LinearLayout linearLayout3;

        ArrayList<TemperatureModel> nextDayDataArrayList;


        public TemperatureViewHolder(@NonNull View itemView) {
            super(itemView);

            temp5 = itemView.findViewById(R.id.tex5);
            val5 = itemView.findViewById(R.id.tex6);
            icon5 = itemView.findViewById(R.id.linear);
            icon6 = itemView.findViewById(R.id.linear7);
            linearLayout3 = itemView.findViewById(R.id.linear9);


        }

    }

}






