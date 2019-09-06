package com.example.roomviewmodel;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

public class UserRepository {
    private UserDao userDao;
    private LiveData<List<User>> allUsers;

    public UserRepository(Application context){
        UserDatabase userDatabase = UserDatabase.getInstance(context);
        userDao = userDatabase.userDao();
        allUsers = userDao.getAllUsers();
    }

    public LiveData<List<User>> getAllUsers() {
        return allUsers;
    }

    public void insertUser(User user){
        new InsertAsyncTask(userDao).execute(user);
    }

    public void updateUser(User user){
        new UpdateAsynkTask(userDao).execute(user);
    }

    private static class UpdateAsynkTask extends AsyncTask<User, Void, Void>{

        private UserDao userDao;

        public UpdateAsynkTask(UserDao userDao){
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(User... users) {
            userDao.updateUser(users[0]);
            return null;
        }
    }

    private static class InsertAsyncTask extends AsyncTask<User, Void, Void>{

        private UserDao userDao;

        public InsertAsyncTask(UserDao userDao){
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(User... users) {
            userDao.insertUser(users[0]);
            return null;
        }
    }
}
