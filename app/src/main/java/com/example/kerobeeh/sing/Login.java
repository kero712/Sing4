package com.example.kerobeeh.sing;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Login extends Activity {
    DB sql =new DB(this);

    ListView listView;
    EditText txtname,txtpass;
    Button btnlog,btnsin,show;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);


        txtname = (EditText) findViewById(R.id.txtName);
        txtpass = (EditText) findViewById(R.id.txtPass);
        show = (Button)findViewById(R.id.buttonlist);
        listView = (ListView)findViewById(R.id.list);
        btnlog = (Button)findViewById(R.id.btnlog);
        btnsin = (Button)findViewById(R.id.btnsin);




    }




    public void ShowData(){
        ArrayList<String> list = sql.getAllrecord();

        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,list);

        listView.setAdapter(adapter);
    }

    public void onClick(View view) {
        int id = view.getId();
        switch (id){
            case R.id.buttonlist:
                ShowData();
                break;
            case R.id.btnlog:
                boolean result=sql.ch(txtname.getText().toString(),txtpass.getText().toString());

                if(result){
                    Toast.makeText(getApplicationContext(), "Login successful, redirecting to Home Page.", Toast.LENGTH_LONG).show();
                   /*  Intent logintent = new Intent(Login.this,listview .class);
                     startActivity(logintent);
                     */
                } else {
                    Toast.makeText(getApplicationContext(), "Invalid credentials, please try again.", Toast.LENGTH_LONG).show();
                }

                break;
            case R.id.btnsin:

                Intent myintent = new Intent(Login.this, Sign.class);
                startActivity(myintent);
                break;

        }

    }
}

