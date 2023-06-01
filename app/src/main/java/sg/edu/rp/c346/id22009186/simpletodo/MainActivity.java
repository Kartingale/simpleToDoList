package sg.edu.rp.c346.id22009186.simpletodo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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

    EditText etTask;
    Button btnAdd;

    Button btnDelete;
    Button btnClear;
    ListView lvTasks;
    Spinner spinner;

    ArrayList<String> alTasks = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etTask = findViewById(R.id.editTextTask);
        btnAdd = findViewById(R.id.buttonAddTask);
        btnDelete = findViewById(R.id.buttonDeleteTask);
        btnClear = findViewById(R.id.buttonClearTask);
        lvTasks = findViewById(R.id.listViewTask);
        spinner = findViewById(R.id.spinner);

        ArrayAdapter<String> aaTasks = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, alTasks);

        lvTasks.setAdapter(aaTasks);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                 String selectedItem = spinner.getSelectedItem().toString();

                 if (selectedItem.equals("Add a new task")) {
                     etTask.setHint("Type in a new task here");
                     btnAdd.setEnabled(true);
                     btnDelete.setEnabled(false);
                 } else if (selectedItem.equals("Remove a task")) {
                     etTask.setHint("Type in the index of the task to be removed.");
                     btnAdd.setEnabled(false);
                     btnDelete.setEnabled(true);
                 }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String task = etTask.getText().toString();
                alTasks.add(task);
                aaTasks.notifyDataSetChanged();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String indexStr = etTask.getText().toString();
                int index = Integer.parseInt(indexStr);

                if (index >=0 && index < alTasks.size()) {
                    alTasks.remove(index);
                    aaTasks.notifyDataSetChanged();
                    etTask.setText("");
                } else {
                    Toast.makeText(MainActivity.this, "Invalid index", Toast.LENGTH_SHORT).show();
                }

            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alTasks.clear();
                aaTasks.notifyDataSetChanged();
            }
        });
    }
}