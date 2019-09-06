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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);

        findViews();
    }

    private void findViews() {
        name = findViewById(R.id.newUserName);
        surname = findViewById(R.id.newUserSurname);
        salary = findViewById(R.id.newUserSalary);
    }

    public void saveUser(View view){
        Intent replyIntent = new Intent();
        if(checkIfFieldsEmpty()){
            setResult(RESULT_CANCELED, replyIntent);
        } else {
            String name = this.name.getText().toString();
            String surname = this.surname.getText().toString();
            int salary = Integer.valueOf(this.salary.getText().toString());
            replyIntent.putExtra(NEW_USER, new User(name, surname, salary));
            setResult(RESULT_OK, replyIntent);
        }
        finish();
    }

    private boolean checkIfFieldsEmpty(){
        return TextUtils.isEmpty(name.getText()) && TextUtils.isEmpty(surname.getText())
                && TextUtils.isEmpty(salary.getText());
    }
}
