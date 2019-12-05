package com.example.adminvirtualwaiter;


import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class Dashboardhm extends Fragment
{
    ListView ls;
    ProgressDialog progress;
    String name,tablenum;
    Long cost1;


    public Dashboardhm() {
        // Required empty public constructor
    }

    public ArrayList<ItemDetails> itemDetailsArrayList = new ArrayList<>();
    MyAdapterOrder orders;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View V=inflater.inflate(R.layout.fragment_dashboardhm, container, false);
        // Inflate the layout for this fragment
        ls=V.findViewById(R.id.ListViewadminorders);
                progress=new ProgressDialog(getActivity());
        progress.setMessage("Please Wait for Orders......."); // Setting Message
        progress.setTitle("Fetching Orders"); // Setting Title
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
        DatabaseReference reference=FirebaseDatabase.getInstance().getReference("Orders");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                itemDetailsArrayList.clear();
                for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren())
                {
                    if(dataSnapshot1.exists())
                    {
                        name=dataSnapshot1.child("ItemName").getValue(String.class);
                        cost1=dataSnapshot1.child("cost").getValue(Long.class);
                        tablenum=dataSnapshot1.child("tableno").getValue(String.class);
                        ItemDetails itemDetails=new ItemDetails(name,cost1,tablenum);
                        itemDetailsArrayList.add(itemDetails);
                    }
                }
                orders.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        orders=new MyAdapterOrder(getActivity(),R.layout.recyclerview_items,itemDetailsArrayList);
        ls.setAdapter(orders);
        return V;
    }

    public class MyAdapterOrder extends ArrayAdapter<ItemDetails>
     {
         ArrayList<ItemDetails> itemDetailsArrayList;
        public MyAdapterOrder(Context context, int items, ArrayList<ItemDetails> objects) {
            super(context,items,objects);
            itemDetailsArrayList=objects;
        }

         @NonNull
         @Override
         public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
         {
             convertView=LayoutInflater.from(getActivity()).inflate(R.layout.recyclerview_items,parent,false);
             final TextView ItemName = convertView.findViewById(R.id.ItemName);
             final TextView ItemCost = convertView.findViewById(R.id.ItemCost);
             final TextView Tableno=convertView.findViewById(R.id.tablenum);
             ItemName.setText(itemDetailsArrayList.get(position).getName());
             Tableno.setText(itemDetailsArrayList.get(position).getTablenum());
             ItemCost.setText(itemDetailsArrayList.get(position).getCost().toString());
             return convertView;
         }

         @Override
         public int getCount() {
             return super.getCount();
         }
     }
}
