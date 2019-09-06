package com.example.roomviewmodel;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserHolder> {
    private List<User> users;
    private LayoutInflater layoutInflater;
    private final String noInfo = "No information";
    private OnItemClicked onItemClicked;

    public UserAdapter(Context context){
        layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public UserHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = layoutInflater.inflate(R.layout.recycler_view_user, viewGroup, false);
        return new UserHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserHolder userHolder, final int i) {
        if(users != null) {
            userHolder.getName().setText(users.get(i).getName());
            userHolder.getSurname().setText(users.get(i).getSurname());
            userHolder.getSalary().setText(String.valueOf(users.get(i).getSalary()));
        } else {
            userHolder.getName().setText(noInfo);
            userHolder.getSurname().setText(noInfo);
            userHolder.getSalary().setText(noInfo);
        }
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClicked != null) {
                    onItemClicked.onItemClick(users.get(i));
                }
            }
        };
        userHolder.itemView.setOnClickListener(onClickListener);
    }

    @Override
    public int getItemCount() {
        if(users != null) {
            return users.size();
        }
        return 0;
    }

    public void setUsers(List<User> users) {
        this.users = users;
        notifyDataSetChanged();
    }

    public interface OnItemClicked{
        void onItemClick(User user);
    }

    public void setOnItemClicked(OnItemClicked onItemClicked) {
        this.onItemClicked = onItemClicked;
    }
}
