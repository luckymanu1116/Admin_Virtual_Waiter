package com.example.adminvirtualwaiter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Desserts extends AppCompatActivity {

    Button addDesserts;
    ImageView imageView;
    EditText name,cost;
    FirebaseDatabase firebaseDatabase;
    ProgressDialog progress;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desserts);
        addDesserts=findViewById(R.id.Adddessertsitem);
        imageView=findViewById(R.id.dessertimg);
        name=findViewById(R.id.itemname);
        cost=findViewById(R.id.cost);
        firebaseDatabase=FirebaseDatabase.getInstance();
        addDesserts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progress=new ProgressDialog(Desserts.this);
                progress.setMessage("Please Wait"); // Setting Message
                progress.setTitle("Adding Item"); // Setting Title
                progress.setProgressStyle(ProgressDialog.STYLE_SPINNER); // Progress Dialog Style Spinner
                progress.show(); // Display Progress Dialog
                progress.setCancelable(false);
                new Thread(new Runnable() {
                    public void run() {
                        try {
                            Thread.sleep(2000);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        progress.dismiss();
                    }
                }).start();
                if( checkName() && checkCost())
                {
                    storeInFirebase();
                }
            }
        });
    }
    private void storeInFirebase() {

        ItemDataModel singleItem=new ItemDataModel(name.getText().toString(),Integer.parseInt(cost.getText().toString()));
        DatabaseReference databaseReference=firebaseDatabase.getReference("Admin-Starters");
        databaseReference.child(name.getText().toString()).setValue(singleItem).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful())
                {
                    Toast.makeText(getApplicationContext(),"Item Added successfully",Toast.LENGTH_LONG).show();
                    onBackPressed();
                }
                else
                    Toast.makeText(Desserts.this, "Unable to insert", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public boolean checkName()
    {
        String Name=name.getText().toString();
        if (TextUtils.isEmpty(Name))
        {
            Toast.makeText(this, "Cannot be empty", Toast.LENGTH_SHORT).show();
            return false;
        }
        else
            return true;
    }
    public boolean checkCost()
    {
        Integer Cost=Integer.parseInt(cost.getText().toString());
        if (TextUtils.isEmpty(""+Cost))
        {
            Toast.makeText(this, "Cannot be empty", Toast.LENGTH_SHORT).show();
            return false;
        }
        else
        {
            if (Cost>0)
                return true;
            else
            {
                Toast.makeText(getApplicationContext(),"Please enter valid number",Toast.LENGTH_LONG).show();
                return false;
            }

        }

    }
}
