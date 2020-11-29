package com.example.laundryhubv2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class register extends AppCompatActivity {

    TextInputLayout Firstname, Lastname, Email, Password1, Password1confirm, Mobilenumber, Address;
    Button Buttoncreate;
    FirebaseAuth FAuth;
    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;
    String firstname, lastname, email, password1, password1confirm, mobilenumber, address;
    String role="Registration";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Firstname = (TextInputLayout)findViewById(R.id.Firstname);
        Lastname = (TextInputLayout)findViewById(R.id.Lastname);
        Email = (TextInputLayout)findViewById(R.id.Email);
        Password1 = (TextInputLayout)findViewById(R.id.Password1);
        Password1confirm= (TextInputLayout)findViewById(R.id.Password1confirm);
        Mobilenumber = (TextInputLayout)findViewById(R.id.Mobilenumber);
        Address = (TextInputLayout)findViewById(R.id.Address);

        Buttoncreate = (Button)findViewById(R.id.Buttoncreate);

        databaseReference = firebaseDatabase.getInstance().getReference("Registration");
        FAuth = FirebaseAuth.getInstance();

        Buttoncreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firstname = Firstname.getEditText().getText().toString().trim();
                lastname = Lastname.getEditText().getText().toString().trim();
                email = Email.getEditText().getText().toString().trim();
                password1 = Password1.getEditText().getText().toString().trim();
                password1confirm = Password1confirm.getEditText().getText().toString().trim();
                mobilenumber = Mobilenumber.getEditText().getText().toString().trim();
                address = Address.getEditText().getText().toString().trim();

                if (isValid()){
                    final ProgressDialog mDialog = new ProgressDialog(register.this);
                    mDialog.setCancelable(false);
                    mDialog.setCanceledOnTouchOutside(false);
                    mDialog.setMessage("Registration in progress please wait...");
                    mDialog.show();

                    FAuth.createUserWithEmailAndPassword(email,password1).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if(task.isSuccessful()){
                                String useridd = FirebaseAuth.getInstance().getCurrentUser().getUid();
                                databaseReference = FirebaseDatabase.getInstance().getReference("User").child(useridd);
                                final HashMap<String , String> hashMap = new HashMap<>();
                                databaseReference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {

                                        HashMap<String , String> hashMap1 = new HashMap<>();
                                        hashMap1.put("First name",firstname);
                                        hashMap1.put("Last name",lastname);
                                        hashMap1.put("Email",email);
                                        hashMap1.put("Password",password1);
                                        hashMap1.put("Confirm password",password1confirm);
                                        hashMap1.put("Address", address);

                                        firebaseDatabase.getInstance().getReference("Registration")
                                                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                                .setValue(hashMap1).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                mDialog.dismiss();

                                                FAuth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<Void> task) {

                                                        if(task.isSuccessful()){
                                                            AlertDialog.Builder builder = new AlertDialog.Builder(register.this);
                                                            builder.setMessage("You have registered! Please verify your email to login");
                                                            builder.setCancelable(false);
                                                            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                                                @Override
                                                                public void onClick(DialogInterface dialog, int which) {

                                                                    dialog.dismiss();

                                                                }
                                                            });
                                                            AlertDialog Alert = builder.create();
                                                            Alert.show();
                                                        }else{
                                                            mDialog.dismiss();
                                                            ReusableCodeForAll.ShowAlert(register.this, "Error", task.getException().getMessage());
                                                        }
                                                    }
                                                });
                                            }
                                        });
                                    }

                                });
                            }
                        }
                    });
                }

            }
        });


    }

    String emailpattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    public boolean isValid(){
        Firstname.setErrorEnabled(false);
        Firstname.setError("");
        Lastname.setErrorEnabled(false);
        Lastname.setError("");
        Email.setErrorEnabled(false);
        Email.setError("");
        Password1.setErrorEnabled(false);
        Password1.setError("");
        Password1confirm.setErrorEnabled(false);
        Password1confirm.setError("");
        Mobilenumber.setErrorEnabled(false);
        Mobilenumber.setError("");
        Address.setErrorEnabled(false);
        Address.setError("");

        boolean isValid = false, isValidname=false, isValidlname=false, isValidemail=false, isValidpassword=false, isValidconfpassword=false, isValidmobilenum=false, isValidaddress=false;
        if(TextUtils.isEmpty(firstname)){
            Firstname.setErrorEnabled(true);
            Firstname.setError("Enter your first name");
        }else{
            isValidname = true;
        }

        if(TextUtils.isEmpty(lastname)){
            Lastname.setErrorEnabled(true);
            Lastname.setError("Enter your last name");
        }else{
            isValidlname = true;
        }

        if(TextUtils.isEmpty(email)){
            Email.setErrorEnabled(true);
            Email.setError("Email address is required");
        }else{
            if(email.matches(emailpattern)){
                isValidemail = true;
            }else{
                Email.setErrorEnabled(true);
                Email.setError("Enter a valid Email address");
            }
        }

        if(TextUtils.isEmpty(password1)){
            Password1.setErrorEnabled(true);
            Password1.setError("Enter password");
        }else{
            if(password1.length()<8){
                Password1.setErrorEnabled(true);
                Password1.setError("Password is weak");
            }else{
                isValidpassword = true;
            }
        }
        if(TextUtils.isEmpty(password1confirm)){
            Password1confirm.setErrorEnabled(true);
            Password1confirm.setError("Enter password again");
        }else{
            if(!password1.equals(password1confirm)){
                Password1confirm.setErrorEnabled(true);
                Password1confirm.setError("Password doesn't match");
            }else{
                isValidconfpassword = true;
            }
        }

        if(TextUtils.isEmpty(address)){
            Address.setErrorEnabled(true);
            Address.setError("Enter your address");
        }else{
            isValidaddress = true;
        }

        if(TextUtils.isEmpty(mobilenumber)){
            Mobilenumber.setErrorEnabled(true);
            Mobilenumber.setError("Mobile number is required");
        }else {
            if (mobilenumber.length() < 11) {
                Mobilenumber.setErrorEnabled(true);
                Mobilenumber.setError("Invalid mobile number");
            } else {
                isValidmobilenum = true;
            }
        }

        isValid = (isValidname && isValidpassword && isValidconfpassword && isValidemail && isValidmobilenum && isValidaddress) ? true : false;
        return isValid;

    }

}