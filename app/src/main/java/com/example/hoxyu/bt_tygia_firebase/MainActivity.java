package com.example.hoxyu.bt_tygia_firebase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    EditText tygia1,tygia2,tygia3,tygia4,tygia5;
    TextView txt6,txt7,txt8,txt9,txt10;
    Button btn_send;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference reference=database.getReference();
    DatabaseReference reference_TyGia=reference.child("TyGia");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        addEvents();
    }

    private void addEvents() {
        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tygia1.getText().length()==0||tygia2.getText().length()==0||tygia3.getText().length()==0||tygia4.getText().length()==0||tygia5.getText().length()==0){
                    Toast.makeText(MainActivity.this, "Vui Lòng Không Để Trống Thông Tin Tỷ Giá !!!", Toast.LENGTH_SHORT).show();
                }
                else{
                    String gold,diamond,usd,gbp,vnd;
                    gold=tygia1.getText().toString();
                    diamond=tygia2.getText().toString();
                    usd=tygia3.getText().toString();
                    gbp=tygia4.getText().toString();
                    vnd=tygia5.getText().toString();
                    TyGia gia=new TyGia(gold,diamond,usd,gbp,vnd);

                    reference_TyGia.child("GiaTri").setValue(gia);

                    Toast.makeText(MainActivity.this, "Cập Nhật Thành Công !!!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        reference_TyGia.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                TyGia tyGia=dataSnapshot.getValue(TyGia.class);
                txt6.setText(tyGia.getGOLD());
                txt7.setText(tyGia.getDIAMOND());
                txt8.setText(tyGia.getUSD());
                txt9.setText(tyGia.getGBP());
                txt10.setText(tyGia.getVND());
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                TyGia tyGia=dataSnapshot.getValue(TyGia.class);
                txt6.setText(tyGia.getGOLD());
                txt7.setText(tyGia.getDIAMOND());
                txt8.setText(tyGia.getUSD());
                txt9.setText(tyGia.getGBP());
                txt10.setText(tyGia.getVND());
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void addControls() {
        tygia1=findViewById(R.id.tygia1);
        tygia2=findViewById(R.id.tygia2);
        tygia3=findViewById(R.id.tygia3);
        tygia4=findViewById(R.id.tygia4);
        tygia5=findViewById(R.id.tygia5);
        txt6=findViewById(R.id.edt6);
        txt7=findViewById(R.id.edt7);
        txt8=findViewById(R.id.edt8);
        txt9=findViewById(R.id.edt9);
        txt10=findViewById(R.id.edt10);
        btn_send=findViewById(R.id.btn_Syns);
        //btn_layve=findViewById(R.id.btn_CapNhat);

    }
}
