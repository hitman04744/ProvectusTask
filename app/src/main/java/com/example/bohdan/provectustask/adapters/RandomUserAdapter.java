package com.example.bohdan.provectustask.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.bohdan.provectustask.R;
import com.example.bohdan.provectustask.data.Results;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


public class RandomUserAdapter extends RecyclerView.Adapter<RandomUserAdapter.MyViewHolder> {
    ClickListener clickListener;
    private List<Results> randomUserList;
    private Context context;


    public RandomUserAdapter(List<Results> randomUserList, Context context) {

        this.randomUserList = randomUserList;
        this.context = context;
        Log.d("ADAPTER", randomUserList.size() + "");
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        Picasso.with(context).load(randomUserList.get(position).getPicture().getLarge())
                .noFade()
                .into(holder.userIcon);
        holder.userName.setText(nameBuilder(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (clickListener != null) {
                    clickListener.ItemClicked(view, position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return randomUserList.size();
    }

    public String nameBuilder(int position) {
        String firstName = randomUserList.get(position).getName().getFirst();
        String lastName = randomUserList.get(position).getName().getLast();
        firstName = firstName.substring(0, 1).toUpperCase() + firstName.substring(1);
        lastName = lastName.substring(0, 1).toUpperCase() + lastName.substring(1);


        return firstName + " " + lastName;
    }

    public void setClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public interface ClickListener {
        void ItemClicked(View v, int position);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView userName;
        public CircleImageView userIcon;

        public MyViewHolder(View view) {
            super(view);
//            itemView.setLayoutParams(new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT,RecyclerView.LayoutParams.WRAP_CONTENT));
            userName = (TextView) view.findViewById(R.id.user_full_name_AM);
            userIcon = (CircleImageView) view.findViewById(R.id.user_icon_AM);

        }


    }

}
