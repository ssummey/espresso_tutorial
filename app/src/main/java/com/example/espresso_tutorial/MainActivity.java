package com.example.espresso_tutorial;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Find call from contact button
        Button contactButton = (Button) findViewById(R.id.call_contact_button);
        contactButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Uri uri = Uri.parse("content://contacts");
                Intent contactIntent = new Intent(Intent.ACTION_PICK,
                        ContactsContract.Contacts.CONTENT_URI);
                contactIntent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE);
                startActivityForResult(contactIntent, REQUEST_CODE);
            }
        });
        // Find edit view
        final EditText phoneNumberEditView = (EditText)
                findViewById(R.id.edit_text_phone_number);
        // Find call button
        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(phoneNumberEditView.getText() != null) {
                    Uri number = Uri.parse("tel:" + phoneNumberEditView.getText());
                    Intent callIntent = new Intent(Intent.ACTION_DIAL, number);
                    startActivity(callIntent);
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                // Bundle extras = data.getExtras();
                // String phoneNumber = extras.get("data").toString();
                Uri uri = data.getData();
                Log.e("ACT_RES", uri.toString());
                String[] projection = {
                        ContactsContract.CommonDataKinds.Phone.NUMBER,
                        ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME };
                Cursor cursor = getContentResolver().query(uri, projection, null, null, null);
                cursor.moveToFirst();

                int numberColumnIndex =
                        cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
                String number = cursor.getString(numberColumnIndex);

                int nameColumnIndex = cursor.getColumnIndex(
                        ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
                String name = cursor.getString(nameColumnIndex);
                Log.d("MAIN_ACTIVITY", "Selected number : " + number +" , name : "+name);

                // Find edit view
                final EditText phoneNumberEditView = (EditText)
                        findViewById(R.id.edit_text_phone_number);
                phoneNumberEditView.setText(number);
            }
        }
    };
    }
