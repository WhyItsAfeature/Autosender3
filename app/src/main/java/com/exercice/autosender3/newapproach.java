package com.exercice.autosender3;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//Bei der täglichen Controlle, ob eine Nachricht gesendet wir. Muss eine Kontrolle eingerichted werden, ob die message  variable ein aufruf für eine liste oder einer selbstgeschriebenen nachricht ist.

// sqlite erstellen und alle inputs von newapproach speichern mit dem button create


public class newapproach  extends AppCompatActivity {
    private Button btn_date;
    private Button btn_contact;
    private Button btn_create;
    private EditText multiLine_message;
    private TextView tv_selectet_date;
    private TextView tv_selectet_contact;
    private Spinner spContainer;
    // input newapproach
        //date  value
    private String date;
        //contact  value
    private String phoneNr;
    private  String name;
        // message value
    private String message;
    private String selectedItem;

    private static final int REQUEST_READ_CONTACTS_PERMISSION = 0;
    private static final int REQUEST_CONTACT = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newapproach);
        //declare GUI objects
        btn_date= findViewById(R.id.btn_date);
        btn_contact= findViewById(R.id.btn_contact);
        btn_create= findViewById(R.id.btn_create);
        multiLine_message= findViewById(R.id.multiLine_message);
        tv_selectet_date= findViewById(R.id.tv_selectet_date);
        tv_selectet_contact=findViewById(R.id.tv_selectet_contact);
        spContainer=findViewById(R.id.spContainer);
        //contact
        final Intent pickContact = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);


        //messageList
        List<String> messageList = new ArrayList<>();


        //Birthday lists
        List<String> casual = new ArrayList<>();
        casual.add("Herzlichen Glückwunsch zum Geburtstag und alles Gute");
        casual.add("Wünschen dir einen Geburtstag voller Freude.");
        casual.add("Happy birthday ");
        casual.add("Alles gute");

        List<String> business = new ArrayList<>();
        business.add("Herzlichen Glückwunsch zu Ihrem Geburtstag und alles Gute, vor allem Gesundheit und persönliches Wohlergehen.");
        business.add("Für dieses neue Lebensjahr wünsche ich Ihnen Gesundheit, Glück und viel Erfolg");


        List<String> babyBoomer = new ArrayList<>();
        babyBoomer.add("");

        
        
        //spinner (drop-down menu)

        List<String> spinnerList = new ArrayList<>();
        spinnerList.add("");
        spinnerList.add("formal");
        spinnerList.add("casual");
        spinnerList.add("Baby boomer");
        // Create an ArrayAdapter to bind the spinnerList to the Spinner
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, spinnerList);
        // Set the layout for the dropdown menu
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Set the adapter for the Spinner
        spContainer.setAdapter(spinnerAdapter);
        // Next, set a listener for selection events on the Spinner
        spContainer.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                 @Override
                  public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                     // This method is called whenever an item in the Spinner is selected
                             selectedItem = parent.getItemAtPosition(position).toString();
                         if (selectedItem!=""){
                             // Shuffle the lists

                             Collections.shuffle(business);
                            switch (selectedItem){
                                case "formal wishes": message=String.join("\n\n", business);break;
                                case "casual": message=String.join("\n\n", casual);break;
                                case "Baby boomer": message=String.join("\n\n", babyBoomer);break;
                            }

                             multiLine_message.setText(message);


                             // message=casual.get(ra)
                            // Toast.makeText(newapproach.this, "Selected: " + selectedItem, Toast.LENGTH_SHORT).show();
                         }

                  }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                    // This method is called whenever nothing is selected in the Spinner
                    spContainer.setSelection(-1);
                }
                 });


        //Listeners
        btn_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickDate();
            }
        });
        btn_contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(pickContact, REQUEST_CONTACT);
            }
        });

        btn_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // date/ phoneNr/ name/ message
                message= String.valueOf(multiLine_message.getText());
                multiLine_message.setText(message);
                try{
                   // if (!date.isEmpty() && !name.isEmpty() && !message.isEmpty())
                   if(date!= null&&name!= null&&(!message.isEmpty()||selectedItem!=""))
                    {

                        Toast.makeText(newapproach.this, "Das Handy sagt ja"+message, Toast.LENGTH_SHORT).show();
                        if (selectedItem!=""){message=selectedItem;}
                        else { message = String.valueOf(multiLine_message.getText());}
                        //sqlite injection

                        //back to main activity
                        Intent intent = new Intent(newapproach.this, MainActivity.class);
                        startActivity(intent);
                    }
                   else{ Toast.makeText(newapproach.this,"You have too choose contact, date and a message",Toast.LENGTH_SHORT).show();}
                }catch (NullPointerException e){
                    Toast.makeText(newapproach.this,"Error: 6767",Toast.LENGTH_SHORT).show();
                }

            }
        });


    }

//Methods

    //Date
    private void pickDate()
    {
        final String[] chosenDate = new String[1];

        Date fragment = Date.newInstance(new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                chosenDate[0] =month+"-"+day;
                date=chosenDate[0];
                String monthName;
                // a switch ti create an pretty output
                switch (month){
                    case 0: monthName="January";break;
                    case 1: monthName="February";break;
                    case 2: monthName="March";break;
                    case 3: monthName="April";break;
                    case 4: monthName="May";break;
                    case 5: monthName="June";break;
                    case 6: monthName="July";break;
                    case 7: monthName="August";break;
                    case 8: monthName="September";break;
                    case 9: monthName="October";break;
                    case 10: monthName="November";break;
                    case 11: monthName="December";break;
                    default: monthName="error";break;
                }
                tv_selectet_date.setText("Chosen Date: "+day+"."+monthName);

            }
        });
        fragment.show(getSupportFragmentManager(), "datePicker");
    }

    //contact
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults)
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == REQUEST_READ_CONTACTS_PERMISSION && grantResults.length > 0)
        {
           // updateButton(grantResults[0] == PackageManager.PERMISSION_GRANTED);
        }
    }

    @SuppressLint("Range")
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode != Activity.RESULT_OK) return;

        if (requestCode == REQUEST_CONTACT && data != null)
        {
            Uri contactUri = data.getData();
            String[] projection = new String[] {
                    ContactsContract.Contacts.DISPLAY_NAME,
                    ContactsContract.CommonDataKinds.Phone.NUMBER
            };
        try{
            // Perform your query - the contactUri is like a "where" clause here
            Cursor cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                    projection, null, null, null);


            while (cursor.moveToNext()) {
                // Get the field values

                 name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                phoneNr= cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                //do somthing with number and name
                //tv_selectet_contact.setText(name+" "+number);
                tv_selectet_contact.setText("Contact: "+name+"\n("+phoneNr+")");
            }
            cursor.close();
        }catch(SecurityException e){tv_selectet_contact.setText("You have too accept the read \n contacts permission!");}
        catch (IndexOutOfBoundsException e){tv_selectet_contact.setText("There is no contact");}
    }}

    private boolean hasContactsPermission()
    {
        return ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) ==
                PackageManager.PERMISSION_GRANTED;
    }

    // Request contact permission if it
    // has not been granted already
    private void requestContactsPermission()
    {
        if (!hasContactsPermission())
        {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_CONTACTS}, REQUEST_READ_CONTACTS_PERMISSION);
        }
    }


}
