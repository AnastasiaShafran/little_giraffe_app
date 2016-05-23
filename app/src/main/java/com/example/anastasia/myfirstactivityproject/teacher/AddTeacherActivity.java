package com.example.anastasia.myfirstactivityproject.teacher;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.anastasia.myfirstactivityproject.R;
import com.example.anastasia.myfirstactivityproject.pojo.Children;
import com.example.anastasia.myfirstactivityproject.pojo.Teacher;
import com.firebase.client.Firebase;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;

public class AddTeacherActivity extends AppCompatActivity {
    private static int RESULT_LOAD_IMG = 1;
    private static int REQUEST_CODE_ASK_PERMISSIONS = 2;
    private Firebase thFirebase;
    private Button btnCbAddTh,btnCbUpdateTeacher, btnPickImage;
    private EditText txtCbThName, txtCbThLastName, txtCbThAge, txtCbThPhone;
    private HashMap<String,Teacher> teacherMap = new HashMap<>();
    private Teacher teacher;
    ImageView imgView;
    String str;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_teacher_activity);
        Firebase.setAndroidContext(this);
        Firebase refUrl = new Firebase("https://myprojectshafran.firebaseio.com");
        thFirebase = refUrl.child("Teachers");
        txtCbThName = (EditText) findViewById(R.id.txtThName);
        txtCbThName.requestFocus();
        txtCbThLastName = (EditText) findViewById(R.id.txtThLastName);
        txtCbThAge = (EditText)findViewById(R.id.txtThAge);
        txtCbThPhone = (EditText)findViewById(R.id.txtThPhone);
        teacher = new Teacher();
        str = (String) getIntent().getSerializableExtra("teacherKey");
        Teacher t = (Teacher)getIntent().getSerializableExtra("teacherValue");
        if(str == null || t == null){
            onBtnSaveTeacherClick();
        }else{
            update(t);
            onBtnClickUpdate();


        }

        onBtnPickImage();




    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        try {
            // When an Image is picked
            if (requestCode == RESULT_LOAD_IMG && resultCode == RESULT_OK
                    && null != data) {
                // Get the Image from data

                Uri selectedImage = data.getData();
                String[] filePathColumn = { MediaStore.Images.Media.DATA };

                // Get the cursor
                Cursor cursor = getContentResolver().query(selectedImage,
                        filePathColumn, null, null, null);
                // Move to first row
                cursor.moveToFirst();

                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                String imgDecodableString = cursor.getString(columnIndex);
                cursor.close();

                imgView = (ImageView) findViewById(R.id.imgView);
                // Set the Image in ImageView after decoding the String

                imgView.setImageBitmap(BitmapFactory
                        .decodeFile(imgDecodableString));

            } else {
                Toast.makeText(this, "You haven't picked Image",
                        Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG)
                    .show();
        }

    }

    public void update(Teacher teacherToUpdate){

        txtCbThName.setText(teacherToUpdate.getName());
        txtCbThLastName.setText(teacherToUpdate.getLastName());
        txtCbThAge.setText(String.valueOf(teacherToUpdate.getAge()));
        txtCbThPhone.setText(String.valueOf(teacherToUpdate.getPhone()));

        byte[] byteArray= Base64.decode(teacherToUpdate.getImage(), Base64.DEFAULT);
        Bitmap avatar = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        ImageView imgView = (ImageView) findViewById(R.id.imgView);
        imgView.setImageBitmap(avatar);


    }
    public void onBtnClickUpdate(){
        txtCbThName = (EditText) findViewById(R.id.txtThName);
        txtCbThName.requestFocus();
        txtCbThLastName = (EditText) findViewById(R.id.txtThLastName);
        txtCbThAge = (EditText)findViewById(R.id.txtThAge);
        txtCbThPhone = (EditText)findViewById(R.id.txtThPhone);
        btnCbUpdateTeacher = (Button)findViewById(R.id.btbAddTeacher);
        btnCbUpdateTeacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = txtCbThName.getText().toString();
                String lastName = txtCbThLastName.getText().toString();
                String age = txtCbThAge.getText().toString();
                String phone = txtCbThPhone.getText().toString();
                Teacher newTeacher = new Teacher();
                newTeacher.setName(name);
                newTeacher.setLastName(lastName);
                newTeacher.setAge(Integer.parseInt(age));
                newTeacher.setPhone(Integer.parseInt(phone));
                thFirebase.child(str).setValue(newTeacher);

                cleanControl();
            }
        });


    }

    public void addTeacher(){
        txtCbThName = (EditText) findViewById(R.id.txtThName);
        txtCbThName.requestFocus();
        txtCbThLastName = (EditText) findViewById(R.id.txtThLastName);
        txtCbThAge = (EditText)findViewById(R.id.txtThAge);
        txtCbThPhone = (EditText)findViewById(R.id.txtThPhone);

        String name = txtCbThName.getText().toString();
        String lastName = txtCbThLastName.getText().toString();
        String age = txtCbThAge.getText().toString();
        String phone = txtCbThPhone.getText().toString();
        teacher.setName(name);
        teacher.setLastName(lastName);
        teacher.setAge(Integer.parseInt(age));
        teacher.setPhone(Integer.parseInt(phone));

        ImageView imgView = (ImageView) findViewById(R.id.imgView);
        // Set the Image in ImageView after decoding the String

        Bitmap bmp = ((BitmapDrawable)imgView.getDrawable()).getBitmap();

        ByteArrayOutputStream bYtE = new ByteArrayOutputStream();

        bmp.compress(Bitmap.CompressFormat.PNG, 100, bYtE);
//        bmp.recycle();

        byte[] byteArray = bYtE.toByteArray();
        teacher.setImage(Base64.encodeToString(byteArray, Base64.DEFAULT));




        thFirebase.push().setValue(teacher);


    }
    public void onBtnSaveTeacherClick(){
        btnCbAddTh = (Button)findViewById(R.id.btbAddTeacher);
        btnCbAddTh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addTeacher();
                cleanControl();
//                txtCbThName.setText("");
//                txtCbThLastName.setText("");
//                txtCbThAge.setText("");
//                txtCbThPhone.setText("");
//                imgView.setImageBitmap(BitmapFactory
//                        .decodeFile(""));

            }
        });
    }


    public void cleanControl(){
        txtCbThName.setText("");
        txtCbThLastName.setText("");
        txtCbThAge.setText("");
        txtCbThPhone.setText("");
        imgView.setImageBitmap(BitmapFactory
                .decodeFile(""));


    }


    private void onBtnPickImage(){
        btnPickImage = (Button)findViewById(R.id.btnPickImage);
        btnPickImage .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int hasWriteContactsPermission = checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE);
                if (hasWriteContactsPermission != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE},REQUEST_CODE_ASK_PERMISSIONS);
                    return;
                }


                Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                // Start the Intent
                startActivityForResult(galleryIntent, RESULT_LOAD_IMG);
            }
        });
    }
}
