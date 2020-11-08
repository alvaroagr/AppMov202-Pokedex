package com.example.pokedex.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.pokedex.R;
import com.example.pokedex.dto.Trainer;
import com.example.pokedex.model.Pokemon;
import com.google.firebase.firestore.FirebaseFirestore;

public class ProfileActivity extends AppCompatActivity {

    private TextView nameTV, typeTV, hpTV, attackTV, defenseTV, sAttackTV, sDefenseTV, speedTV;
    private Button releaseBtn;
    private ImageView mainImage;
    private Trainer trainer;
    private Pokemon pokemon;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        nameTV = findViewById(R.id.nameTV);
        typeTV = findViewById(R.id.typeTV);
        hpTV = findViewById(R.id.hpTV);
        attackTV = findViewById(R.id.attackTV);
        defenseTV = findViewById(R.id.defenseTV);
        speedTV = findViewById(R.id.speedTV);
        sAttackTV = findViewById(R.id.sAttackTV);
        sDefenseTV = findViewById(R.id.sDefenseTV);
        releaseBtn = findViewById(R.id.releaseBtn);
        mainImage = findViewById(R.id.mainImage);

        trainer = (Trainer) getIntent().getExtras().getSerializable("trainer");
        pokemon = (Pokemon) getIntent().getExtras().getSerializable("pokemon");

        nameTV.setText(Pokemon.capitalize(pokemon.getName()));
        typeTV.setText("(" + Pokemon.translateType(pokemon.getType().get(0)));
        if(pokemon.getType().size() > 1)
            typeTV.append("/" + Pokemon.translateType(pokemon.getType().get(1)));
        typeTV.append(")");
        hpTV.setText(pokemon.getHp()+"");
        attackTV.setText(pokemon.getAtk()+"");
        defenseTV.setText(pokemon.getDef()+"");
        speedTV.setText(pokemon.getSpeed()+"");
        sAttackTV.setText(pokemon.getSpAtk()+"");
        sDefenseTV.setText(pokemon.getSpDef()+"");

        Glide.with(this).load(pokemon.getSprite()).fitCenter().into(mainImage);

        db = FirebaseFirestore.getInstance();

        releaseBtn.setOnClickListener(
                (v) -> {
                    db.collection("trainers")
                            .document(trainer.getId())
                            .collection("pokemon")
                            .document(pokemon.getId())
                            .delete()
                            .addOnCompleteListener(
                                    task -> {
                                        if(task.isSuccessful()){
                                            db.collection("pokemon")
                                                    .document(pokemon.getId())
                                                    .delete()
                                                    .addOnCompleteListener( innerTask -> {
                                                        if(task.isSuccessful()){
                                                            finish();
                                                        } else {
                                                            Toast.makeText(this,
                                                                    "Hubo un error y no se " +
                                                                    "solto al Pokemon.",
                                                                    Toast.LENGTH_LONG).show();
                                                        }
                                            });
                                        } else {
                                            Toast.makeText(this,
                                                    "Hubo un error y no se solto al Pokemon.",
                                                    Toast.LENGTH_LONG).show();
                                        }
                                    }
                            );
                }
        );

    }

}