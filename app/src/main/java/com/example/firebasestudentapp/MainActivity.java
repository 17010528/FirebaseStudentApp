package com.example.firebasestudentapp;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private ListView lvStudent;
    private ArrayList<Student> alStudent;
    private ArrayAdapter<Student> aaStudent;
    private FirebaseFirestore db;
    private CollectionReference colRef;
    private DocumentReference docRef;



    // TODO: Task 1 - Declare Firebase variables

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvStudent = (ListView)findViewById(R.id.listViewStudents);

        // TODO: Task 2: Get FirebaseFirestore instance and reference




        db = FirebaseFirestore.getInstance();

        colRef = db.collection("students");

        //TODO: Task 3: Get real time updates from firestore by listening to collection "students"
                colRef.addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value,
                                        @Nullable FirebaseFirestoreException e) {
                        if (e != null) {
                            Log.w(TAG, "Listen failed.", e);
                            return;
                        }
                        alStudent = new ArrayList<Student>();


                        for (QueryDocumentSnapshot doc : value) {
                            if (doc.get("name") != null) {
                                Student student = doc.toObject(Student.class); //use pojo
                                student.setId(doc.getId());
                                alStudent.add(student);

                            }
                        }
                        aaStudent = new ArrayAdapter<Student>(getApplicationContext(), android.R.layout.simple_list_item_1, alStudent);
                        lvStudent.setAdapter(aaStudent);
                    }
                });
        //TODO: Task 4: Read from Snapshot and add into ArrayAdapter for ListView

        lvStudent.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Student student = alStudent.get(i);  // Get the selected Student
                Intent intent = new Intent(MainActivity.this, StudentDetailsActivity.class);
                intent.putExtra("StudentID" , student.getId());
                startActivityForResult(intent, 1);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(android.view.MenuItem item) {
        // Handle action bar item clicks here.
        int id = item.getItemId();

        if (id == R.id.addStudent) {

            Intent intent = new Intent(getApplicationContext(), AddStudentActivity.class);
            startActivity(intent);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
