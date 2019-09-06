package com.example.roomviewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

public class UserViewModel extends AndroidViewModel {

    private UserRepository userRepository;
    private LiveData<List<User>> allUsers;

    public UserViewModel(Application context){
        super(context);
        userRepository = new UserRepository(context);
        allUsers = userRepository.getAllUsers();
    }

    public LiveData<List<User>> getAllUsers() {
        return allUsers;
    }

    public void insert(User user){
        userRepository.insertUser(user);
    }

    public void update(User user){
        userRepository.updateUser(user);
    }
}
