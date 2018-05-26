package com.example.alina.communicate_between_fragments;

//import android.app.Activity;
//import android.app.FragmentManager;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity implements FragmentA.OnGreenFragmentListener
{
    private static final String BLUE_TAG = "2";
    private static final String GREEN_TAG = "1";
    FragmentA mGreenFragment;
    FragmentB  mBlueFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // add fragments
        FragmentManager fragmentManager = getSupportFragmentManager();

        mBlueFragment = (FragmentB) fragmentManager.findFragmentByTag(BLUE_TAG);
        if (mBlueFragment == null) {
            mBlueFragment = new FragmentB();
            fragmentManager.beginTransaction().add(R.id.fragment2, mBlueFragment, BLUE_TAG).commit();
        }

        mGreenFragment = (FragmentA) fragmentManager.findFragmentByTag(GREEN_TAG);
        if (mGreenFragment == null) {
            mGreenFragment = new FragmentA();
            fragmentManager.beginTransaction().add(R.id.fragment1, mGreenFragment, GREEN_TAG).commit();
        }
    }
    @Override
    public void messageFromGreenFragment(String message,int size) {
        mBlueFragment.youveGotMail(message, size);
    }
}