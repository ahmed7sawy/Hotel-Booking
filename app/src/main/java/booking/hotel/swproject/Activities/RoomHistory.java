package booking.hotel.swproject.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import booking.hotel.swproject.R;

public class RoomHistory extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_history);

        int roomNumber= getIntent().getIntExtra("RoomNumber",0);

        TextView textView = (TextView)findViewById(R.id.room_historyTV);
        textView.setText(roomNumber+" ");

    }
}
