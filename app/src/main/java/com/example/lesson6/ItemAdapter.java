package com.example.lesson6;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewAdapter> {

    //private List<String> data;
    private ImplLikeSource likeSource;
    public static final String LLOG = "LLOK";
    private ItemClickListerner listener;

     public interface ItemClickListerner {
        void onItemClick(int position);
    }

    public void setListener(ItemClickListerner listener) {
        this.listener = listener;
    }

    /*public ItemAdapter(List<String> data) {
        this.data = data;
    }*/

    public ItemAdapter(ImplLikeSource data) {
        this.likeSource = data;
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
      //holder.bind(data.get(position));
        holder.bind(likeSource.getDataLike(position));
      /*if (position == 1) {
          holder.getTv().setBackgroundColor(R.color.teal_200);
      }
      else
      {
          holder.getTv().setBackgroundColor(R.color.white);
      }*/
      Log.d(LLOG, "onBinded");
    }

    @Override
    public int getItemCount() {
        return likeSource.size();
    }

     class ItemViewAdapter extends RecyclerView.ViewHolder{

        /*private TextView tv;
        public TextView getTv() {
            return tv;
        }*/
         private TextView dictionary;
         private TextView description;
         private ImageView picture;
         private CheckBox likeCB;

        public ItemViewAdapter(@NonNull @NotNull View itemView) {
            super(itemView);
            //tv = (TextView) itemView;
            dictionary = itemView.findViewById(R.id.text_like);
            description = itemView.findViewById(R.id.description);
            picture = itemView.findViewById(R.id.image_item_rec);
            likeCB = itemView.findViewById(R.id.like_cb);
        }

        void bind(DataLike data) {
            //tv.setText(inText);
            dictionary.setText(data.getCaption());
            description.setText(data.getDescription());
            picture.setImageResource(data.getImage());
            likeCB.setChecked(data.isLike());
            //tv.setOnClickListener(new View.OnClickListener() {
            picture.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        listener.onItemClick(getAdapterPosition());
                    }
                }
            });
        }

        /*void bind(String inText) {
            tv.setText(inText);
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        listener.onItemClick(getAdapterPosition());
                    }
                }
            });
        }*/
    }


}
