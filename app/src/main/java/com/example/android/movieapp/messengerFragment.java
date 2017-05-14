package com.example.android.movieapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class messengerFragment extends Fragment {
    EditText writeMessage;
    ImageButton sendMessage;
    RecyclerView messenger;
    messengerAdapter messengerAdapter;
    ArrayList<messengerData> messengerDatas;
    DatabaseReference db;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        messengerDatas = new ArrayList<>();
        View v = inflater.inflate(R.layout.fragment_messenger, container, false);
        messenger = (RecyclerView) v.findViewById(R.id.messenger_rec);
        messenger.setLayoutManager(new LinearLayoutManager(getActivity()));
        messengerAdapter = new messengerAdapter(messengerDatas);
        messenger.setAdapter(messengerAdapter);
        db = FirebaseDatabase.getInstance().getReference();
        db.addValueEventListener(new messengevaluesEventLisnter());
        writeMessage =(EditText) v.findViewById(R.id.writeMessage);
        sendMessage = (ImageButton) v.findViewById(R.id.sendMessage);
        sendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = writeMessage.getText().toString();
                if(!TextUtils.isEmpty(message))
                {

                    db.child("messenger").push().setValue(new messengerData(message, FirebaseAuth.getInstance().getCurrentUser().getUid()));
                }
                else
                    Toast.makeText(getActivity().getBaseContext(),"Write Message",Toast.LENGTH_LONG).show();
            }
        });
        return v;
    }

    class messengevaluesEventLisnter implements ValueEventListener {

        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            messengerDatas.clear();
            for (DataSnapshot d : dataSnapshot.child("messenger").getChildren()) {
                messengerData messengerData = d.getValue(messengerData.class);
                messengerData.setUser((String) dataSnapshot.child("users").child(messengerData.getUser()).child("name").getValue());
                messengerDatas.add(messengerData);
                messengerAdapter.notifyDataSetChanged();
            }
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    }
}
