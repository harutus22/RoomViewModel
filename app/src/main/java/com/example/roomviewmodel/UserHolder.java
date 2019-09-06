package com.example.roomviewmodel;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class UserHolder extends RecyclerView.ViewHolder {
    private TextView name, surname, salary;

    public UserHolder(@NonNull View itemView) {
        super(itemView);
        name = itemView.findViewById(R.id.userName);
        surname = itemView.findViewById(R.id.userSurname);
        salary = itemView.findViewById(R.id.userSalary);
    }

    public TextView getName() {
        return name;
    }

    public TextView getSurname() {
        return surname;
    }

    public TextView getSalary() {
        return salary;
    }
}
