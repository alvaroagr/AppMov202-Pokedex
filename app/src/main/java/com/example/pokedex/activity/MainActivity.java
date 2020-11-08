package com.example.pokedex.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.pokedex.R;
import com.example.pokedex.dto.Trainer;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    private EditText usernameET;
    private Button loginBtn;

    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usernameET = findViewById(R.id.usernameET);
        loginBtn = findViewById(R.id.loginBtn);

        db = FirebaseFirestore.getInstance();

        loginBtn.setOnClickListener(
                (v) -> {
                    String username = usernameET.getText().toString();
                    Trainer trainer = new Trainer(UUID.randomUUID().toString(), username);
                    CollectionReference trainersRef = db.collection("trainers");
                    Query query = trainersRef.whereEqualTo("name", username);
                    query.get().addOnCompleteListener(
                            task -> {
                                if(task.isSuccessful()){
                                    if(task.getResult().size() > 0){
                                        for(QueryDocumentSnapshot doc : task.getResult()){
                                            Trainer dbTrainer = doc.toObject(Trainer.class);
                                            goToHomeActivity(dbTrainer);
                                            break;
                                        }
                                    } else {
                                        db.collection("trainers")
                                                .document(trainer.getId()).set(trainer);
                                        goToHomeActivity(trainer);
                                    }
                                }
                            }
                    );
                }
        );

    }

    public void goToHomeActivity(Trainer trainer){
        Intent i = new Intent(this, HomeActivity.class);
        i.putExtra("trainer", trainer);
        startActivity(i);
    }
}