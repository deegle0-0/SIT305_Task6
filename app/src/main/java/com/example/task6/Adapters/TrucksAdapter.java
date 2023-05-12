package com.example.task6.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.AsyncDifferConfig;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.task6.R;
import com.example.task6.UpdateActivity;
import com.example.task6.data.Trucks;

public class TrucksAdapter extends ListAdapter<Trucks,TrucksAdapter.MyViewHolder > {

    Context context;

    TrucksViewModel trucksViewModel;


    public TrucksAdapter(@NonNull DiffUtil.ItemCallback<Trucks> diffCallback, Context context,TrucksViewModel trucksViewModel) {
        super(diffCallback);
        this.context = context;
        this.trucksViewModel = trucksViewModel;
    }

    public TrucksAdapter(@NonNull AsyncDifferConfig<Trucks> config) {
        super(config);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.trucks, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Trucks current = getItem(position);
        holder.model.setText(current.getModel());
        holder.id.setText(String.valueOf(current.getId()));
        holder.dates.setText((CharSequence) current.getDates());

        holder.updateImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, UpdateActivity.class);

                intent.putExtra("model", current);
                ((Activity) context).startActivityForResult(intent,2);
            }
        });

        holder.deleteImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                trucksViewModel.delete(current);
            }
        });
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView id,model,dates;
        ImageView updateImage, deleteImage;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            model = itemView.findViewById(R.id.textViewName);
            id = itemView.findViewById(R.id.textViewID);
            dates = itemView.findViewById(R.id.textViewDescription);
            updateImage = itemView.findViewById(R.id.updateImage);
            deleteImage = itemView.findViewById(R.id.deleteImage);

        }
    }
    public static class TruckDiff extends DiffUtil.ItemCallback<Trucks>{

        @Override
        public boolean areItemsTheSame(@NonNull Trucks oldItem, @NonNull Trucks newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Trucks oldItem, @NonNull Trucks newItem) {
            return oldItem.getModel().equals(newItem.getModel());
        }
    }
}
