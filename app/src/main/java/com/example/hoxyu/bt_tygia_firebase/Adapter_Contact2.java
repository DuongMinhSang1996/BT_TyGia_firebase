package com.example.hoxyu.bt_tygia_firebase;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class Adapter_Contact2 extends ArrayAdapter {
    Context context;
    int resource;
    ArrayList<ThongTinDanhBa2> arrayList;
    ThongTinDanhBa2 danhBa;
    public Adapter_Contact2(@NonNull Context context, int resource, @NonNull ArrayList<ThongTinDanhBa2> arrayList) {
        super(context, resource, arrayList);
        this.context=context;
        this.resource=resource;
        this.arrayList=arrayList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(resource,null);

        CircleImageView img_contact=view.findViewById(R.id.img_contact);
        TextView txt_name=view.findViewById(R.id.txt_name);
        TextView txt_number=view.findViewById(R.id.txt_number);
        TextView txt_nickname=view.findViewById(R.id.txt_nickname);

        danhBa=new ThongTinDanhBa2();
        danhBa=arrayList.get(position);

        txt_name.setText(danhBa.getName_contact());
        txt_number.setText(danhBa.getNumber_contact());
        txt_nickname.setText(danhBa.getNickname_contact());
        img_contact.setContentDescription(danhBa.getImg_contact());

        //FirebaseStorage storage = FirebaseStorage.getInstance("gsz//hocandroid-c3fd0.appspot.com ");
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReference();
        StorageReference pathReference = storageRef.child("IMG_CONTACT/"+danhBa.getImg_contact());
        Glide.with(context).using(new FirebaseImageLoader()).load(pathReference).into(img_contact);

        return view;
    }
//    private void downloadFile() throws IOException {
//        FirebaseStorage storage = FirebaseStorage.getInstance("gsz//hocandroid-c3fd0.appspot.com ");
//        StorageReference storageRef = storage.getReference();
//        StorageReference pathReference = storageRef.child("IMG_CONTACT/"+danhBa.getImg_contact()+".jpg");
//        File localFile = File.createTempFile("images_contact", "jpg");
//        mFilename= localFile.getAbsolutePath();
//        pathReference.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
//            @Override
//            public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
//
//            }
//        });
//        Glide.with(context).using(new FirebaseImageLoader()).load(pathReference).into(img_contact)
//    }
}
