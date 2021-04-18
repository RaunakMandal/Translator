package me.raunakmandal.translator.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import me.raunakmandal.translator.R;
import me.raunakmandal.translator.data.Languages;

public class SpinnerAdapter extends ArrayAdapter<Languages> {

    public SpinnerAdapter(Context context, List<Languages> objects) {
        super(context, 0, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    private View initView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.lang_list, parent, false);
        }
        TextView lang = (TextView) convertView.findViewById(R.id.lang);
        Languages langAtPos = getItem(position);

        lang.setText(langAtPos.getLangName());

        return convertView;
    }
}
