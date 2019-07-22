package com.example.firebasestudentapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;


public class AddStudentActivity extends AppCompatActivity {

    private static final String TAG = "AddStudentActivity";

    private EditText etName, etAge;
    private Button btnAdd;
    private FirebaseFirestore db;
    private CollectionReference colRef;
    private DocumentReference docRef;

    // TODO: Task 1 - Declare Firebase variables

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        etName = (EditText) findViewById(R.id.editTextName);
        etAge = (EditText) findViewById(R.id.editTextAge);
        btnAdd = (Button) findViewById(R.id.buttonAdd);

        db = FirebaseFirestore.getInstance();

        colRef = db.collection("students");
        docRef = colRef.document("2wFYHO4ThEaZPjzjLZQl");

        colRef = db.collection("students");



        //TODO: Task 3: Get real time updates from firestore by listening to collection "students"

        // TODO: Task 2: Get FirebaseFirestore instance and collection reference to "students"


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //TODO: Task 3: Retrieve name and age from EditText and instantiate a new Student object
                //TODO: Task 4: Add student to database and go back to main screen
                String name = etName.getText().toString();
                int age = Integer.parseInt(etAge.getText().toString());

                Student newStudent = new Student(age, name);

                        colRef.add(newStudent)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Toast.makeText(AddStudentActivity.this, "Student added into Firestore", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(AddStudentActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                            }
                        });

                finish();

            }
        });


    }
}
