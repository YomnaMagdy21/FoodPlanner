package com.example.foodplanner.MealDetails.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.foodplanner.R;

import java.util.List;

public class DaysAdapter extends ArrayAdapter<String> {

    private List<String> options;

    public DaysAdapter(@NonNull Context context, int resource, @NonNull List<String> objects) {
        super(context, resource, objects);
        this.options = objects;
    }

    @Override
    public int getCount() {
        return options.size();
    }

    @Nullable
    @Override
    public String getItem(int position) {
        return options.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.day_item, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.optionTextView = convertView.findViewById(R.id.dropdown_item_text);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        String option = getItem(position);
        if (option != null) {
            viewHolder.optionTextView.setText(option);
        }

        return convertView;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return getView(position, convertView, parent);
    }

    private static class ViewHolder {
        TextView optionTextView;
    }
}
