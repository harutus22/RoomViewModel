package com.example.roomviewmodel;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private UserViewModel userViewModel;
    public static final int NEW_USER_ACTIVITY = 202;
    public static final int UPDATE_USER = 300;
    public static final String KEY_UPDATE = "keyUpdate";
    private UserAdapter userAdapter;

    private UserAdapter.OnItemClicked onItemClicked = new UserAdapter.OnItemClicked() {
        @Override
        public void onItemClick(User user) {
            Intent intent = new Intent(getApplicationContext(), NewUserActivity.class);
            intent.putExtra(KEY_UPDATE, user);
            startActivityForResult(intent, UPDATE_USER);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.userRecyclerView);
        userAdapter = new UserAdapter(this);
        userAdapter.setOnItemClicked(onItemClicked);
        recyclerView.setAdapter(userAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        userViewModel = ViewModelProviders.of(this).get(UserViewModel.class);
        userViewModel.getAllUsers().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(@Nullable List<User> users) {
                userAdapter.setUsers(users);

                for(User user: users){
                    Log.d("Tag", user.getId() + " " + user.getName() + " " +
                            user.getSurname() + " " + user.getSalary());
                }
            }
        });

    }

    public void goToNewUserActivity(View view){
        Intent intent = new Intent(this, NewUserActivity.class);
        startActivityForResult(intent, NEW_USER_ACTIVITY);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == NEW_USER_ACTIVITY && resultCode == RESULT_OK){
            User user = data.getParcelableExtra(NewUserActivity.NEW_USER);
            userViewModel.insert(user);
        } else if(requestCode == UPDATE_USER && resultCode == RESULT_OK){
            User user = data.getParcelableExtra(KEY_UPDATE);
            userViewModel.update(user);
            Log.d("Tag", user.getId() + " " + user.getName() + " " +
                    user.getSurname() + " " + user.getSalary());
        }
    }
}
