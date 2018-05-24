package com.example.hoxyu.bt_tygia_firebase;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class DanhBaActivity extends AppCompatActivity {
    ListView lv_contact;
    ArrayList<ThongTinDanhBa> arrayList;
    Adapter_Contact adapterContact;



    Button btn_addContact;
    public static final int PICK_IMAGE = 1;
    CircleImageView img_contact;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference reference=database.getReference();
    DatabaseReference reference_Contact=reference.child("Contact");

    FirebaseStorage storage = FirebaseStorage.getInstance();
    StorageReference storageRef = storage.getReference();
    //StorageReference storageRef_Contact=storageRef.child("IMG_CONTACT");



    InputStream inputStream_img;
    String pushId;

    ThongTinDanhBa danhBa_temp;
    String name,number,nickname,name_img;
    Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_ba);
        addControls();
        addEvents();
    }

    private void addEvents() {
        btn_addContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog=new Dialog(DanhBaActivity.this);
                dialog.setTitle("Hộp Thoại Xử Lý");
                dialog.setCancelable(false);
                dialog.setContentView(R.layout.layout_custom_dialog);


                final EditText edt_name=(EditText)dialog.findViewById(R.id.edt_name);
                final EditText edt_number=(EditText)dialog.findViewById(R.id.edt_number);
                final EditText edt_nickname=(EditText)dialog.findViewById(R.id.edt_nickname);
                img_contact=(CircleImageView)dialog.findViewById(R.id.img_contact);
                Button btn_addToFirebase=dialog.findViewById(R.id.btn_addToFirebase);
                Button btn_Cancle=dialog.findViewById(R.id.btn_Cancle);

                btn_Cancle.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                btn_addToFirebase.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        name= String.valueOf(edt_name.getText());
                        number= String.valueOf(edt_number.getText());
                        nickname= String.valueOf(edt_nickname.getText());
                        name_img=uri.getLastPathSegment();

                        danhBa_temp=new ThongTinDanhBa(name_img,name,number,nickname);
                        pushId = reference_Contact.push().getKey();
                        reference_Contact.child(pushId).setValue(danhBa_temp);

                        uploadIMG();
                        dialog.dismiss();
                    }
                });
                img_contact.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent=new Intent();
                        intent.setType("image/*");
                        intent.setAction(Intent.ACTION_GET_CONTENT);
                        startActivityForResult(Intent.createChooser(intent,"Select Picture"),PICK_IMAGE);
                    }
                });
                dialog.show();
            }
        });
        reference_Contact.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                ThongTinDanhBa danhBa=dataSnapshot.getValue(ThongTinDanhBa.class);
                arrayList.add(danhBa);
                adapterContact.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

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
        reference_Contact.removeEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        registerForContextMenu(lv_contact);

    }
    public void uploadIMG(){

        StorageReference filepath=storageRef.child("IMG_CONTACT").child(uri.getLastPathSegment());
        filepath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                adapterContact.notifyDataSetChanged();
                Toast.makeText(DanhBaActivity.this,"Thêm Thành Công !!!" , Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(DanhBaActivity.this,"Thêm Thất Bại !!!" , Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==PICK_IMAGE && resultCode==RESULT_OK){
            try {
                uri=data.getData();
                //name_img=uri.getLastPathSegment();
                inputStream_img = DanhBaActivity.this.getContentResolver().openInputStream(data.getData());
                img_contact.setImageBitmap(BitmapFactory.decodeStream(inputStream_img));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private void addControls() {
        lv_contact=findViewById(R.id.lv_contact);
        arrayList=new ArrayList<>();
        adapterContact=new Adapter_Contact(DanhBaActivity.this,R.layout.layout_row_contact,arrayList);
        lv_contact.setAdapter(adapterContact);
        adapterContact.notifyDataSetChanged();
        btn_addContact=findViewById(R.id.btn_addContact);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.context_menu_main,menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info= (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()){
            case R.id.delete_id:{
                arrayList.remove(info.position);
                adapterContact.notifyDataSetChanged();
                reference_Contact.child(pushId).removeValue();

                return true;
            }
        }
        return super.onContextItemSelected(item);
    }

}
