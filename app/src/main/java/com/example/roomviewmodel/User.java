package com.example.roomviewmodel;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

@Entity(tableName = "my_table")
public class User implements Parcelable {

    @Ignore
    public User(){
    }

    public User(String name, String surname, int salary){
        this.name = name;
        this.surname = surname;
        this.salary = salary;
    }

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "UserName")
    private String name;

    @ColumnInfo(name = "UserSurname")
    private String surname;

    @ColumnInfo(name = "UserSalary")
    private int salary;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(surname);
        dest.writeInt(salary);
    }

    private static User createFromParcel(Parcel source){
        User user = new User();
        user.id = source.readInt();
        user.name = source.readString();
        user.surname = source.readString();
        user.salary = source.readInt();
        return user;
    }

    public static final Parcelable.Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel source) {
            return User.createFromParcel(source);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
}
