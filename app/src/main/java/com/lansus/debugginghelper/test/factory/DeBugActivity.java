package com.lansus.debugginghelper.test.factory;


import android.net.Uri;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.lansus.debugginghelper.R;
import com.lansus.debugginghelper.test.ProtocolFragment;

public class DeBugActivity extends AppCompatActivity implements ProtocolFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_de_bug);
        FragmentManager fragmentManager =  getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        ProtocolFragment fragment = ProtocolFragment.newInstance("jun","");
        fragmentTransaction.add(R.id.activity_de_bug, fragment);
        fragmentTransaction.commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
