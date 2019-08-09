package com.example.vibhor.insertdatabettermethodapp;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText usernameText , passwordText ;
    VibhsDatabase vibhsDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        vibhsDatabase = new VibhsDatabase(MainActivity.this);

        usernameText = (EditText) findViewById(R.id.username_edittext);
        passwordText = (EditText) findViewById(R.id.passsword_edittext);

    }

    public void onAddUser(View view)
    {
        String username = usernameText.getText().toString();
        String password = passwordText.getText().toString();
        long id=vibhsDatabase.insertData(username,password);
        if(id<0)
        {
            Message.message(this, "data is saved successfully");
        }else{
            Message.message(this,"something went wrong");
        }

    }

    public void  onViewDetails(View view)
    {
        String data = vibhsDatabase.getAllData();
        Message.message(MainActivity.this,data);
    }
}
