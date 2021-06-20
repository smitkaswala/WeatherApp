package com.bbot.darkweatherforecast;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.bbot.darkweatherforecast.SearchLocation.ResponseLite;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;

public class AutoSuggestAdapter extends ArrayAdapter<ResponseLite> implements Filterable {

    private ArrayList<ResponseLite> mlistData, tempItems, suggestions;
    Context context;

    public AutoSuggestAdapter(Context context, int resource, int textViewResourceId, ArrayList<ResponseLite> items) {
        super(context, resource, textViewResourceId, items);
        this.context = context;
        mlistData = items;
        mlistData=new ArrayList<>();
        suggestions = new ArrayList<ResponseLite>();

    }

    public void setData(ArrayList<ResponseLite> list) {
        mlistData.clear();
        mlistData.addAll(list);
    }

    @Override
    public int getCount() {
        return mlistData.size();
    }

    @Nullable
    @Override
    public ResponseLite getItem(int position) {
        return mlistData.get(position);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.list_item_search_auto, parent, false);
        }
        ResponseLite people = mlistData.get(position);
        if (people != null) {
            TextView lblName = (TextView) view.findViewById(R.id.text1);
            if (lblName != null)
                lblName.setText(people.getLocalizedName() + "," + people.getCountry().getLocalizedName());
        }
        return view;
    }

    @Override
    public Filter getFilter() {
        return nameFilter;
    }


    Filter nameFilter = new Filter() {
        @Override
        public CharSequence convertResultToString(Object resultValue) {
            String str = ((ResponseLite) resultValue).getLocalizedName();
            return str;
        }

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            if (constraint != null) {
                suggestions.clear();
                for (ResponseLite people : tempItems) {
                    String name = people.getLocalizedName();
                    if (name.toLowerCase().contains(constraint.toString().toLowerCase())) {
                        suggestions.add(people);
                    }
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = suggestions;
                filterResults.count = suggestions.size();
                return filterResults;
            } else {
                return new FilterResults();
            }
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {

            try {
                ArrayList<ResponseLite> filterList = (ArrayList<ResponseLite>) results.values;
                if (results != null && results.count > 0) {
                    clear();
                    for (ResponseLite people : filterList) {
                        add(people);
                        notifyDataSetChanged();
                    }
                }

            } catch (ConcurrentModificationException e) {
            }

        }

    };

}
