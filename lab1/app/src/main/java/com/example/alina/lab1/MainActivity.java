package com.example.alina.lab1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.AdapterView;

public class MainActivity extends AppCompatActivity {

    Integer[] sizes = {12, 14, 16, 20, 24, 36};
    Spinner sp;
    TextView text;
    Button butt1, butt2;
    EditText edit;
    Integer record;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        sp = (Spinner)findViewById(R.id.spin);
        text = (TextView)findViewById(R.id.textView);
        butt1 = (Button)findViewById(R.id.button1);
        butt2 = (Button)findViewById(R.id.button2);
        edit = (EditText)findViewById(R.id.editText);

        // Создаем адаптер ArrayAdapter с помощью массива строк и стандартной разметки элемета spinner
        ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_list_item_1, sizes);
        // Определяем разметку для использования при выборе элемента
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Применяем адаптер к элементу spinner
        sp.setAdapter(adapter);

        OnItemSelectedListener itemSelectedListener = new OnItemSelectedListener() {
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

        butt1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Обработка нажатия
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

    }


}
