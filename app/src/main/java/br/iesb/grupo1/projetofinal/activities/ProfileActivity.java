package br.iesb.grupo1.projetofinal.activities;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;

import br.iesb.grupo1.projetofinal.R;
import br.iesb.grupo1.projetofinal.util.UserTest;

public class ProfileActivity extends AppCompatActivity {

    TextView txProfileEmail;
    TextView txProfileName;
    String currentUserStoredEmail;

    ImageView imgProfileImage;
    String uploadedImageURL;

    Button btnSaveProfile;
    Button btnChangePassword;

    FirebaseAuth auth;
    FirebaseDatabase database;
    DatabaseReference databaseReference;
    FirebaseStorage storage;
    StorageReference storageReference;

    private static final int RESULT_LOAD_IMG = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        //LOAD COMPONENTS FROM XML
        txProfileEmail = findViewById(R.id.txProfileEmailField);
        txProfileEmail.setText(currentUserStoredEmail);
        txProfileName = findViewById(R.id.txNameProfileField);
        imgProfileImage = findViewById(R.id.imgProfileImage);
        btnSaveProfile = findViewById(R.id.btnSaveProfile);
        btnChangePassword = findViewById(R.id.btnChangePassword);

        //AUTH
        auth = FirebaseAuth.getInstance();

        //DATABASE
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("/users");
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference("profiles/");

        //SET LISTENER FOR SAVE BUTTON
        btnSaveProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(txProfileEmail == null || txProfileName == null) {
                    return;
                }else{
                    if(txProfileEmail.getText() == null || txProfileName == null){
                        return;
                    }else{
                        saveProfileData(txProfileName.getText().toString());
                    }
                }
            }
        });

        //SET LISTENER FOR PROFILE IMAGE
        imgProfileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, RESULT_LOAD_IMG);
            }
        });

        //SET LISTENER FOR CHANGE PASSWORD BUTTON
        btnChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth auth = FirebaseAuth.getInstance();

                auth.sendPasswordResetEmail(currentUserStoredEmail).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(ProfileActivity.this, "Email sent!", Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });

        //GET INFO FROM INTENT
        Bundle b = getIntent().getExtras();
        if(b != null) {
            currentUserStoredEmail = b.getString("email");
        }else {
            loadProfileData();
        }
    }

    public void loadProfileData(){
        String email = auth.getCurrentUser().getEmail();

        if(email == null){
            return;
        }else {
            currentUserStoredEmail = email;
            txProfileEmail.setText(email);
        }

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                for (DataSnapshot child : snapshot.getChildren()) {
                    UserTest u = child.getValue(UserTest.class);

                    if(u != null){
                        if(u.getEmail().equals(currentUserStoredEmail)){
                            txProfileName.setText(u.getName());
                            String temp = u.getPicturePath();
                            if(u.getPicturePath() != null && u.getPicturePath() != "")
                                Glide.with(ProfileActivity.this).load(u.getPicturePath()).into(imgProfileImage);
                            break;
                        }
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void saveProfileData(String name){

        //UPLOAD IMAGE TO STORAGE
        BitmapDrawable bitmap = (BitmapDrawable) imgProfileImage.getDrawable();

        if (bitmap.getBitmap() == null) {
            return;
        }

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.getBitmap().compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
        byte[] byteData = outputStream.toByteArray();

        UploadTask uploadTask = storageReference.putBytes(byteData);
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(ProfileActivity.this, "Failed to upload file", Toast.LENGTH_SHORT).show();
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                uploadedImageURL = taskSnapshot.getDownloadUrl().toString();
                //SAVE PROFILE DATA INTO DATABASE
                databaseReference.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("name").setValue(txProfileName.getText().toString());
                databaseReference.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("email").setValue(txProfileEmail.getText().toString());
                databaseReference.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("picturePath").setValue(uploadedImageURL);
            }
        });

        Intent i = new Intent(ProfileActivity.this, JobListActivity.class);
        startActivity(i);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == RESULT_LOAD_IMG && resultCode == RESULT_OK && null != data){
            //SELECT IMAGE FROM GALLERY
            Uri selectImage = data.getData();
            String[] filePath = { MediaStore.Images.Media.DATA };

            if(selectImage != null) {
                Cursor cursor = getContentResolver().query(selectImage, filePath, null, null, null);
                cursor.moveToFirst();

                int columnIndex = cursor.getColumnIndex(filePath[0]);
                String picturePath = cursor.getString(columnIndex);
                cursor.close();

                imgProfileImage.setImageBitmap(BitmapFactory.decodeFile(picturePath));
            }
        }
    }
}
