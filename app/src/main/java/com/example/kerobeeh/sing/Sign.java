package com.example.kerobeeh.sing;


import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class Sign extends Activity implements View.OnClickListener {


    private DB sql = new DB(this);



    private static final int CAMERA_REQUEST = 1888;
    private ImageView imageView;

    private EditText f_name, password, email, phone, message;
    private TextView pickPhone;
    private Button send, call, pick, share, signup;
    private RadioGroup group;
    private RadioButton male, female;
    private CheckBox agree;


    private void UI() {
        imageView = (ImageView) findViewById(R.id.image);
        f_name = (EditText) findViewById(R.id.F_name);
        password = (EditText) findViewById(R.id.pass);
        email = (EditText) findViewById(R.id.Txtmail);
        phone = (EditText) findViewById(R.id.phon_n);
        message = (EditText) findViewById(R.id.sendmess);
        pickPhone = (TextView) findViewById(R.id.pick);
        send = (Button) findViewById(R.id.btnsend);
        call = (Button) findViewById(R.id.btncall);
        pick = (Button) findViewById(R.id.btncontact);
        share = (Button) findViewById(R.id.btnshare);
        signup = (Button) findViewById(R.id.btnSignup);
        signup.setEnabled(false);
        group = (RadioGroup) findViewById(R.id.gen);
        male = (RadioButton) findViewById(R.id.R_male);
        female = (RadioButton) findViewById(R.id.R_Female);
        agree = (CheckBox) findViewById(R.id.agree);

    }


    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(this, "pause", Toast.LENGTH_LONG).show();
    }


    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(this, "Resume", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(this, "Stop", Toast.LENGTH_LONG).show();


    }

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(this, "Start", Toast.LENGTH_LONG).show();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign);


        UI();
        imageView.setOnClickListener(this);
        send.setOnClickListener(this);
        call.setOnClickListener(this);
        pick.setOnClickListener(this);
        share.setOnClickListener(this);
        signup.setOnClickListener(this);
        agree.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.image:
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
                break;
            case R.id.btnsend:
                if (!email.getText().toString().isEmpty()) {
                    Intent share = new Intent();
                    share.setAction(Intent.ACTION_SEND);
                    share.putExtra(Intent.EXTRA_EMAIL, email.getText().toString());
                    share.setType("text/plain");
                    startActivity(share);
                } else
                    message.setError("Please Enter a Message !!!!");

                break;
            case R.id.btncall:
                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel:" + call.getText().toString()));
                startActivity(callIntent);
                break;
            case R.id.btncontact:

                Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
                startActivity(intent);

                break;
            case R.id.btnshare:
                if (!message.getText().toString().isEmpty()) {
                    Intent share = new Intent();
                    share.setAction(Intent.ACTION_SENDTO);
                    share.putExtra(Intent.EXTRA_EMAIL, message.getText().toString());
                    share.setType("text/plain");
                    startActivity(share);
                } else
                    message.setError("Please Enter a Message !!!!");
                        break;



            case R.id.btnSignup:
                  boolean result = sql.insertdata(f_name.getText().toString(),password.getText().toString());
              if (result){
                  Intent myinIntent = new Intent(Sign.this,Login.class);
                  startActivity(myinIntent);
                  Toast.makeText(Sign.this ,"done" , Toast.LENGTH_SHORT).show();
              }else {
                  Toast.makeText(Sign.this ,"erro" , Toast.LENGTH_SHORT).show();
              }
                 break;
            case R.id.agree:

                if (agree.isChecked())
                    signup.setEnabled(true);
                else
                    signup.setEnabled(false);
                break;

        }


        }




    // @Override
   // public void onActivityResult(int reqCode, int resultCode, Intent data) {
       // super.onActivityResult(reqCode, resultCode, data);

       // switch (reqCode) {
          //  case (PICK_CONTACT) :
              //  if (resultCode == Activity.RESULT_OK) {
                   // Uri contactData = data.getData();
                   // Cursor c =  managedQuery(contactData, null, null, null, null);
                  //  if (c.moveToFirst()) {
                     //   String name = c.getString(c.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                        // TODO Fetch other Contact details as you want to use
                       // pickPhone.setText(name);

                  //  }
              //  }
              //  break;
        }

