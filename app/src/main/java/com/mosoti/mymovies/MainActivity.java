package com.mosoti.mymovies;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.searchbutton) Button mSearchButton;
    @Bind(R.id.sechQuery) EditText mSearchText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

       mSearchButton.setOnClickListener(this);
    }
    @Override
    public void onClick(View v){

        String serchterm = mSearchText.getText().toString();
        Log.v("termserched", serchterm);

        Intent intent = new Intent(MainActivity.this, MoviesActivity.class);

        intent.putExtra("serchterm", serchterm);
        startActivity(intent);
    }

}
