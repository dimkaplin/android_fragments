package com.example.lesson6;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewAdapter> {

    private List<String> data;
    public static final String LLOG = "LLOK";

    public ItemAdapter(List<String> data) {
        this.data = data;
    }

    @NonNull
    @NotNull
    @Override
    public ItemViewAdapter onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclingitem, parent, false);
        Log.d(LLOG, "onCreated");
        return new ItemViewAdapter(v);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull @NotNull ItemAdapter.ItemViewAdapter holder, int position) {
      holder.bind(data.get(position));
      if (position == 1) {
          holder.getTv().setBackgroundColor(R.color.teal_200);
      }
      else
      {
          holder.getTv().setBackgroundColor(R.color.white);
      }
      Log.d(LLOG, "onBinded");
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    static class ItemViewAdapter extends RecyclerView.ViewHolder{

        public TextView getTv() {
            return tv;
        }

        private TextView tv;

        public ItemViewAdapter(@NonNull @NotNull View itemView) {
            super(itemView);
            tv = (TextView) itemView;
        }

        void bind(String inText) {
            tv.setText(inText);
        }
    }
}
