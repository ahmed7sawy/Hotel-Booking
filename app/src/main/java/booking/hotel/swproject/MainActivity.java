package booking.hotel.swproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.QuickContactBadge;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import booking.hotel.swproject.Activities.ShowRooms;
import booking.hotel.swproject.Activities.StartBooking;
import booking.hotel.swproject.Entity.Room;

public class MainActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;

    private Button showRoomsBtn;
    private Button startBookingBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //mDatabase = FirebaseDatabase.getInstance().getReference();
        showRoomsBtn=(Button)findViewById(R.id.show_roomsBtn);
        startBookingBtn=(Button)findViewById(R.id.start_bookingBtn);

        showRoomsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ShowRooms.class);
                startActivity(intent);
            }
        });

        startBookingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, StartBooking.class);
                startActivity(intent);
            }
        });
    }
    private void addRooms(){
        Room room1=new Room(1,1,300,"single");
        Room room2=new Room(2,1,300,"single");
        Room room3=new Room(3,1,300,"single");
        Room room4=new Room(4,1,500,"double");
        Room room5=new Room(5,1,500,"double");
        Room room6=new Room(6,2,350,"single");
        Room room7=new Room(7,2,350,"single");
        Room room8=new Room(8,2,350,"single");
        Room room9=new Room(9,2,600,"double");
        Room room10=new Room(10,2,600,"double");
        Room room11=new Room(11,3,380,"single");
        Room room12=new Room(12,3,380,"single");
        Room room13=new Room(13,3,380,"single");
        Room room14=new Room(14,3,650,"double");
        Room room15=new Room(15,3,650,"double");

        mDatabase.child("Rooms").child("Room1").setValue(room1);
        mDatabase.child("Rooms").child("Room2").setValue(room2);
        mDatabase.child("Rooms").child("Room3").setValue(room3);
        mDatabase.child("Rooms").child("Room4").setValue(room4);
        mDatabase.child("Rooms").child("Room5").setValue(room5);
        mDatabase.child("Rooms").child("Room6").setValue(room6);
        mDatabase.child("Rooms").child("Room7").setValue(room7);
        mDatabase.child("Rooms").child("Room8").setValue(room8);
        mDatabase.child("Rooms").child("Room9").setValue(room9);
        mDatabase.child("Rooms").child("Room10").setValue(room10);
        mDatabase.child("Rooms").child("Room11").setValue(room11);
        mDatabase.child("Rooms").child("Room12").setValue(room12);
        mDatabase.child("Rooms").child("Room13").setValue(room13);
        mDatabase.child("Rooms").child("Room14").setValue(room14);
        mDatabase.child("Rooms").child("Room15").setValue(room15);

    }
}
