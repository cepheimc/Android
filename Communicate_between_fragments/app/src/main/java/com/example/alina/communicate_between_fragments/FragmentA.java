package com.example.alina.communicate_between_fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;



public class FragmentA extends Fragment
{
    private OnGreenFragmentListener mCallback;
    Integer[] sizes = {12, 14, 16, 20, 24, 36};
    int record;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_a, container, false);

        Button button = v.findViewById(R.id.button);
        final EditText text1 = v.findViewById(R.id.text);
        Spinner sp = (Spinner)v.findViewById(R.id.spin);
        ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(getActivity(), android.R.layout.simple_list_item_1, sizes);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter(adapter);
        AdapterView.OnItemSelectedListener itemSelectedListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                switch(position)
                {
                    case 0:
                        record = 12;
                        break;
                    case 1:
                        record = 14;
                        break;
                    case 2:
                        record = 16;
                        break;
                    case 3:
                        record = 20;
                        break;
                    case 4:
                        record = 24;
                        break;
                    case 5:
                        record = 36;
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        };
        sp.setOnItemSelectedListener(itemSelectedListener);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = text1.getText().toString();
                mCallback.messageFromGreenFragment(message, record);
            }
        });

        return v;
    }

    public interface OnGreenFragmentListener {
        void messageFromGreenFragment(String text, int size);
    }

    // This method insures that the Activity has actually implemented our
    // listener and that it isn't null.
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnGreenFragmentListener) {
            mCallback = (OnGreenFragmentListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnGreenFragmentListener");
        }
    }

 /*   @Override
    public void onDetach() {
        super.onDetach();
        mCallback = null;
    }*/
}
