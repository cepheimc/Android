package com.example.alina.lab3;

import android.content.Context;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
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
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class MainActivity extends AppCompatActivity {
    Integer[] sizes = {12, 14, 16, 20, 24, 36};

    TextView text;
    EditText edit;
    Integer record;
    Button butt3, butt4;
    private final static String FILE_NAME = "content.txt";
    private String TAG_WRITE_READ_FILE = "TAG_WRITE_READ_FILE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        Spinner sp = (Spinner) findViewById(R.id.spin);
        text = (TextView) findViewById(R.id.textView);
        Button butt1 = (Button) findViewById(R.id.button1);
        Button butt2 = (Button) findViewById(R.id.button2);
        butt3 = (Button) findViewById(R.id.button3);
        butt4 = (Button) findViewById(R.id.button4);
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
        sp.setOnItemSelectedListener(itemSelectedListener);

        butt1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                text.setTextSize(record);
                text.setText(edit.getText());

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
              //  StringBuilder result = new StringBuilder();
                String mess = edit.getText().toString() + ":" + record.toString();
               // while (mess != null) {
              //      result.append('\n').append(mess);
              //  }

                if(TextUtils.isEmpty(mess))
                {
                    Toast.makeText(getApplicationContext(), "Input data can not be empty.", Toast.LENGTH_LONG).show();
                    return;
                }else {
                    Context ctx = getApplicationContext();

                    try
                    {
                        FileOutputStream fileOutputStream = ctx.openFileOutput(FILE_NAME, Context.MODE_PRIVATE);
                        writeDataToFile(fileOutputStream, mess);
                        Toast.makeText(ctx, "Data has been written to file " + FILE_NAME, Toast.LENGTH_LONG).show();
                    }catch(FileNotFoundException ex)
                    {
                        Log.e(TAG_WRITE_READ_FILE, ex.getMessage(), ex);
                    }


                }
            }
        });

        butt4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                StringBuilder text1 = new StringBuilder();
                try {
                    Context ctx = getApplicationContext();

                    FileInputStream fileInputStream = ctx.openFileInput(FILE_NAME);

                 /* //  try {
                        if (fileInputStream != null) {
                            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
                            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                            String lineData;
                            while ((lineData = bufferedReader.readLine()) != null) {
                                text1.append(lineData);
                                text.setText(text1.toString());
                                text1.append("\n");
                            }
                        }*/
                   // }catch(IOException ex)
                  //  {
                   //     Log.e(TAG_WRITE_READ_FILE, ex.getMessage(), ex);
                   // }

                    String fileData = readFromFileInputStream(fileInputStream);
                    StringTokenizer tokens = new StringTokenizer(fileData, "\n");
                    String first = tokens.nextToken();
                    String second = tokens.nextToken();
                    Integer size = Integer.parseInt(second);

                    if(first.length()>0) {
                       // text.setTextSize(size);
                        text.setText(first);
                        Toast.makeText(ctx, "Load saved data complete.", Toast.LENGTH_SHORT).show();
                    }else
                    {
                        Toast.makeText(ctx, "Not load any data.", Toast.LENGTH_SHORT).show();
                    }
                }catch(IOException ex)
                {
                    Log.e(TAG_WRITE_READ_FILE, ex.getMessage(), ex);
                }
            }
        });

    }


    // This method will write data to FileOutputStream.
    private void writeDataToFile(FileOutputStream fileOutputStream, String data)
    {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
            BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);

            bufferedWriter.write(data);

            bufferedWriter.flush();
            bufferedWriter.close();
            outputStreamWriter.close();
        }catch(FileNotFoundException ex)
        {
            Log.e(TAG_WRITE_READ_FILE, ex.getMessage(), ex);
        }catch(IOException ex)
        {
            Log.e(TAG_WRITE_READ_FILE, ex.getMessage(), ex);
        }
    }

    // This method will read data from FileInputStream.
    private String readFromFileInputStream(FileInputStream fileInputStream)
    {
        StringBuilder text1 = new StringBuilder();

        try {
            if (fileInputStream != null) {
                InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                String lineData;
                while ((lineData = bufferedReader.readLine()) != null) {
                    text1.append(lineData);
                    text1.append("\n");
                }
            }
        }catch(IOException ex)
        {
            Log.e(TAG_WRITE_READ_FILE, ex.getMessage(), ex);
        }finally
        {
            return text1.toString();
        }
    }
}
