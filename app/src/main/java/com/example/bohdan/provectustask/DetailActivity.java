package com.example.bohdan.provectustask;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bohdan.provectustask.data.Name;
import com.example.bohdan.provectustask.data.Results;
import com.squareup.picasso.Picasso;

import static java.lang.System.in;

public class DetailActivity extends AppCompatActivity {
    private TextView userName;
    private TextView userEmail;
    private TextView userBirth;
    private TextView userAdress;
    private TextView userPhone;
    private TextView userPassword;
    private ImageView userIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        findViews();
        Results resultUser = (Results) getIntent().getParcelableExtra("user");


        Log.d("DETAIL", resultUser.toString());
        userName.setText(resultUser.getName().getFirst() + resultUser.getName().getLast());
        userEmail.setText(resultUser.getEmail());
        userBirth.setText(resultUser.getDob());
        userAdress.setText(resultUser.getLocation().getStreet());
        userPhone.setText(resultUser.getCell());
        userPassword.setText(resultUser.getLogin().getPassword());
        Picasso.with(this).load(resultUser.getPicture().getLarge()).into(userIcon);
    }

    public void findViews() {
        userName = (TextView) findViewById(R.id.text_name_DA);
        userEmail = (TextView) findViewById(R.id.text_email_DA);
        userBirth = (TextView) findViewById(R.id.text_birth_DA);
        userAdress = (TextView) findViewById(R.id.text_adress_DA);
        userPhone = (TextView) findViewById(R.id.text_phone_DA);
        userPassword = (TextView) findViewById(R.id.text_password_DA);
        userIcon = (ImageView) findViewById(R.id.user_icon_DA);
    }
}
