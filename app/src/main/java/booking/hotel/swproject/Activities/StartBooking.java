package booking.hotel.swproject.Activities;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import booking.hotel.swproject.Entity.Booking;
import booking.hotel.swproject.Entity.Room;
import booking.hotel.swproject.R;

public class StartBooking extends AppCompatActivity {

    private DatabaseReference mDatabase;

    EditText guestNameET;
    EditText guestAddressET;
    EditText guestPhoneET;
    EditText roomNumberET;
    TextView firstDayTV;
    TextView lastDayTV;
    Button bookBtn;

    DatePickerDialog picker;
    private long firstDayTimeStamp = 0;
    private long lastDayTimeStamp = 0;
    private String guestName;
    private String guestPhone;
    private String guestAddress;
    private int roomNumber = -1;
    private float roomCost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_booking);

        guestNameET = (EditText) findViewById(R.id.guest_name_et);
        guestAddressET = (EditText) findViewById(R.id.guest_address_et);
        guestPhoneET = (EditText) findViewById(R.id.guest_phone_et);
        roomNumberET = (EditText) findViewById(R.id.room_number_et);
        firstDayTV = (TextView) findViewById(R.id.first_day);
        lastDayTV = (TextView) findViewById(R.id.last_day);
        bookBtn = (Button) findViewById(R.id.book_btn);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        firstDayTV.setOnClickListener(firstDayOnClickListener);
        lastDayTV.setOnClickListener(lastDayOnClickListener);
        bookBtn.setOnClickListener(bookButtonOnClickListener);

    }

    private View.OnClickListener bookButtonOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            readData();
            if(isDataReaded()){
               saveData();
            }else {
                showDataReadError();
            }
        }
    };

    private void readData(){
        guestName = guestNameET.getText().toString();
        guestAddress = guestAddressET.getText().toString();
        guestPhone = guestPhoneET.getText().toString();
        roomNumber = Integer.parseInt(roomNumberET.getText().toString());

    }

    private boolean isDataReaded() {
        return !guestName.equals("") && !guestAddress.equals("") && !guestPhone.equals("") && roomNumber != -1 && firstDayTimeStamp != 0 && lastDayTimeStamp != 0;
    }

    private void saveData() {

        String roomTitle = "Room" + roomNumber;
        Log.i("StartBooking", roomTitle);
        StartBooking.this.mDatabase.child("Rooms").child(roomTitle).child("dayCost").addListenerForSingleValueEvent(new RoomCostListener());//addValueEventListener
    }

    private void saveBooking(){
        long diffTimeStamp = lastDayTimeStamp - firstDayTimeStamp;
        long timeInDay=24*60*60*1000;
        int daysNum = (int) (diffTimeStamp/timeInDay);
        long bookingCost = (long)(daysNum * roomCost);

        Booking newBooking = new Booking(guestName,guestPhone,guestAddress,roomNumber,firstDayTimeStamp,lastDayTimeStamp,bookingCost);
        mDatabase.child("Booking").push().setValue(newBooking);

        Toast.makeText(StartBooking.this,"Saved",Toast.LENGTH_LONG).show();
        clearFields();
        showDialog(bookingCost);

    }
    private void clearFields(){
        guestNameET.setText("");
        guestAddressET.setText("");
        guestPhoneET.setText("");
        roomNumberET.setText("");
        firstDayTV.setText("click here to choose date");
        lastDayTV.setText("click here to choose date");
    }
    private void showDataReadError(){
        Toast.makeText(StartBooking.this,"failed to booking",Toast.LENGTH_LONG).show();
    }

    private void showDialog(long bookingCost) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setMessage("Please send " + bookingCost + " Pound to our Vodafone cash before three days from now");
        dialog.setTitle("Booking Confirmation");
        dialog.setPositiveButton("ok",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,
                                        int which) {
                        Toast.makeText(getApplicationContext(), "Thanks", Toast.LENGTH_LONG).show();
                    }
                });
        AlertDialog alertDialog = dialog.create();
        alertDialog.show();
    }


    private View.OnClickListener firstDayOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            final Calendar cldr = Calendar.getInstance();
            int day = cldr.get(Calendar.DAY_OF_MONTH);
            int month = cldr.get(Calendar.MONTH);
            int year = cldr.get(Calendar.YEAR);
            picker = new DatePickerDialog(StartBooking.this,
                    new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                            firstDayTV.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            Date date = new Date(year,monthOfYear,dayOfMonth);
                            firstDayTimeStamp = date.getTime();
                        }
                    }, year, month, day);
            picker.show();
        }
    };

    private View.OnClickListener lastDayOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            final Calendar cldr = Calendar.getInstance();
            int day = cldr.get(Calendar.DAY_OF_MONTH);
            int month = cldr.get(Calendar.MONTH);
            int year = cldr.get(Calendar.YEAR);
            picker = new DatePickerDialog(StartBooking.this,
                    new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                            lastDayTV.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            Date date = new Date(year,monthOfYear,dayOfMonth);
                            lastDayTimeStamp = date.getTime();
                        }
                    }, year, month, day);
            picker.show();

        }
    };

    class RoomCostListener implements ValueEventListener {
        RoomCostListener() {
        }
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

            if (dataSnapshot.getValue() != null) {
                roomCost = dataSnapshot.getValue(Float.class);
                saveBooking();
            }
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {
        }
    }

}
