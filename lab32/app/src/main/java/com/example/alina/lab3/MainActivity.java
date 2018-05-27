package com.example.alina.lab3;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Integer[] sizes = {12, 14, 16, 20, 24, 36};

    TextView text;
    EditText edit;
    Integer record;
    Button butt3;
    private final static String FILE_NAME = "content.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        Spinner sp = (Spinner) findViewById(R.id.spin);
        text = (TextView) findViewById(R.id.textView);
        Button butt1 = (Button) findViewById(R.id.button1);
        Button butt2 = (Button) findViewById(R.id.button2);
        butt3 = (Button) findViewById(R.id.button3);
        edit = (EditText) findViewById(R.id.editText);


        ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_list_item_1, sizes);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter(adapter);

        AdapterView.OnItemSelectedListener itemSelectedListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                switch (position) {
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

        try {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
                    openFileOutput(FILE_NAME, MODE_PRIVATE)));
            bw.write("");
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        sp.setOnItemSelectedListener(itemSelectedListener);

        butt1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                text.setTextSize(record);
                text.setText(edit.getText());
                CharSequence messag = edit.getText();
                CharSequence index = Integer.toString(record);

                writeToFile(messag, index);

            }
        });

        butt2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                text.setText("");
                edit.getText().clear();
            }
        });

        butt3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                ArrayList<String> mess = readFile();

                String ordersText = TextUtils.join("\n\n\n", mess);
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Список данных")
                        .setMessage(ordersText)

                        .setCancelable(false)
                        .setNegativeButton("Закрыть",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                });
                AlertDialog alert = builder.create();
                alert.show();

            }
        });


    }

    ArrayList<String> readFile() {
        ArrayList<String> mess = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    openFileInput(FILE_NAME)));

            String tempStr;
            while ((tempStr = br.readLine()) != null) {
                mess.add(tempStr);
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mess;
    }

    void writeToFile(CharSequence mess, CharSequence index) {
        try {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
                    openFileOutput(FILE_NAME, MODE_APPEND)));

            mess = mess.toString();
            bw.write("текст: "+mess + " шрифт: " + index);
            bw.newLine();
            Toast.makeText(this, "Данные сохранены", Toast.LENGTH_SHORT).show();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
