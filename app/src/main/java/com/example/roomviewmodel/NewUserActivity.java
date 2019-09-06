package com.example.roomviewmodel;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

public class NewUserActivity extends AppCompatActivity {
    public static final String NEW_USER = "newUser";
    private EditText name, surname, salary;
    private boolean isNewUser = true;
    private int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);

        findViews();

        if(getIntent().hasExtra(MainActivity.KEY_UPDATE)) {
            Intent intent = getIntent();
            User user = intent.getParcelableExtra(MainActivity.KEY_UPDATE);
            id = user.getId();
            name.setText(user.getName());
            surname.setText(user.getSurname());
            salary.setText(String.valueOf(user.getSalary()));
            isNewUser = false;
        }
    }

    private void findViews() {
        name = findViewById(R.id.newUserName);
        surname = findViewById(R.id.newUserSurname);
        salary = findViewById(R.id.newUserSalary);
    }

    public void saveUser(View view){
        saveNewUser();
    }

    private void saveNewUser(){
        Intent replyIntent = new Intent();
        if(isNewUser) {
            if (checkIfFieldsEmpty()) {
                setResult(RESULT_CANCELED, replyIntent);
            } else {
                User user = createUser(0);
                replyIntent.putExtra(NEW_USER, user);
                setResult(RESULT_OK, replyIntent);
            }
        } else {
            User user = createUser(id);
            replyIntent.putExtra(MainActivity.KEY_UPDATE, user);
            setResult(RESULT_OK, replyIntent);
        }
        finish();
    }

    private User createUser(int id){
        String name = this.name.getText().toString();
        String surname = this.surname.getText().toString();
        int salary = Integer.valueOf(this.salary.getText().toString());
        return new User(id, name, surname, salary);
    }

    private boolean checkIfFieldsEmpty(){
        return TextUtils.isEmpty(name.getText()) && TextUtils.isEmpty(surname.getText())
                && TextUtils.isEmpty(salary.getText());
    }
}
