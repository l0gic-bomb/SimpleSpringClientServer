package com.artnast.employeecheckerapp;

import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.artnast.employeecheckerapp.model.Employee;
import com.artnast.employeecheckerapp.retrofit.EmployeeApi;
import com.artnast.employeecheckerapp.retrofit.RetrofitService;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.logging.Level;
import java.util.logging.Logger;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.employee_item);

        initializeComponents();
    }

    private void initializeComponents()
    {
        TextInputEditText inputEditText = findViewById(R.id.form_textFieldName);
        TextInputEditText inputEditBranch = findViewById(R.id.form_textFieldBranch);
        TextInputEditText inputEditLocation = findViewById(R.id.form_textFieldLocation);
        MaterialButton buttonSave = findViewById(R.id.form_buttonSave);

        RetrofitService service = new RetrofitService();
        EmployeeApi employeeApi = service.getRetrofit().create(EmployeeApi.class);

        buttonSave.setOnClickListener(view-> {
            String name = inputEditText.getText().toString();
            String branch = inputEditBranch.getText().toString();
            String location = inputEditLocation.getText().toString();

            Employee employee = new Employee();
            employee.setName(name);
            employee.setBranch(branch);
            employee.setLocation(location);

            employeeApi.save(employee)
                    .enqueue(new Callback<Employee>() {
                        @Override
                        public void onResponse(Call<Employee> call, Response<Employee> response) {
                            Toast.makeText(MainActivity.this, "Save successful!", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFailure(Call<Employee> call, Throwable t) {
                            Toast.makeText(MainActivity.this, "Save failed!", Toast.LENGTH_SHORT).show();
                            Logger.getLogger(getClass().getName().toString()).log(Level.SEVERE, "Error occurred", t);
                        }

                    });
        });
    }
}