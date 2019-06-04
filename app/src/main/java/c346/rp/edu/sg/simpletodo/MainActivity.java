package c346.rp.edu.sg.simpletodo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView inputLv;
    Button add, clear, delete;
    Spinner spinner;
    EditText inputEt;

    ArrayList<String> spinnerArray = new ArrayList<>();
    ArrayList<String> inputArray = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputLv = findViewById(R.id.inputLv);
        add = findViewById(R.id.addBtn);
        delete = findViewById(R.id.delBtn);
        clear = findViewById(R.id.clearBtn);
        spinner = findViewById(R.id.spinner);
        inputEt = findViewById(R.id.inputEt);

        addSpinnerItem();

        final ArrayAdapter adapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_spinner_dropdown_item, spinnerArray);
        spinner. setAdapter(adapter);

        final ArrayAdapter inputAdapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, inputArray);
        inputLv.setAdapter(inputAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch(position) {
                    case 0:
                        inputEt.setHint("Type a new task here");
                        delete.setEnabled(false);
                        add.setEnabled(true);
                        inputEt.setInputType(InputType.TYPE_CLASS_TEXT);
                        break;
                    case 1:
                        inputEt.setHint("Type in the index of the task to be removed");
                        add.setEnabled(false);
                        delete.setEnabled(true);
                        inputEt.setInputType(InputType.TYPE_CLASS_NUMBER);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (inputEt.getText().length() > 0) {
                    String input = inputEt.getText().toString();
                    inputArray.add(input);
                    inputAdapter.notifyDataSetChanged();
                    inputEt.setText("");
                }
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int index = Integer.parseInt(inputEt.getText().toString());

                if (inputEt.getText().length() > 0) {
                    try {
                        inputArray.remove(index);
                        inputAdapter.notifyDataSetChanged();
                        inputEt.setText("");
                    }
                    catch (IndexOutOfBoundsException ioe) {
                        Toast.makeText(MainActivity.this, "Wrong Index Number", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (inputArray.isEmpty()) {
                    Toast.makeText(MainActivity.this, "You don't have any task to remove", Toast.LENGTH_SHORT).show();
                }

                if (inputEt.getText() != null || inputArray.size() > 0) {
                    inputEt.setText("");
                    inputArray.clear();
                    inputAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    public void addSpinnerItem() {
        spinnerArray.add("Add a new task");
        spinnerArray.add("Remove a  task");
    }
}
