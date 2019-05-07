package booking.hotel.swproject.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import booking.hotel.swproject.Activities.RoomHistory;
import booking.hotel.swproject.Entity.Room;
import booking.hotel.swproject.R;

public class RoomAdapter extends RecyclerView.Adapter<RoomAdapter.RoomViewHolder> {

    private String TAG = "RoomAdapter";
    private Context mContext;

    private DatabaseReference ref = FirebaseDatabase.getInstance().getReference();

    private ArrayList<Room> roomsList=new ArrayList<>();

    public RoomAdapter(Context mContext) {
        this.mContext = mContext;
        RoomAdapter.this.ref.child("Rooms").addValueEventListener(new RoomListener());
    }

    @NonNull
    @Override
    public RoomViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new RoomViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_room, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RoomViewHolder roomViewHolder, int position) {
        final Room currentRoom = RoomAdapter.this.roomsList.get(position);

        roomViewHolder.roomNumber.setText(Integer.toString(currentRoom.getRoomNumber()));
        roomViewHolder.roomFloor.setText(Integer.toString(currentRoom.getFloor()));
        roomViewHolder.roomDayCost.setText(Float.toString(currentRoom.getDayCost()));
        roomViewHolder.roomType.setText(currentRoom.getRoomType());

        final int resourceId = mContext.getResources().getIdentifier(getRoomName(currentRoom.getRoomNumber()),
                "drawable", mContext.getPackageName());
        roomViewHolder.roomImageView.setImageResource(resourceId);

        roomViewHolder.roomCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, RoomHistory.class);
                intent.putExtra("RoomNumber", currentRoom.getRoomNumber());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        Log.i(TAG,roomsList.size()+"");
        return roomsList.size();
    }


    class RoomListener implements ValueEventListener {
        RoomListener() {
        }

        public void onDataChange(DataSnapshot dataSnapshot) {

            if (dataSnapshot.getValue() != null) {
                roomsList.clear();

                for (DataSnapshot friendSnapshot : dataSnapshot.getChildren()) {

                    Room room = friendSnapshot.getValue(Room.class);

                    roomsList.add(room);
                }

                Collections.sort(roomsList, new Comparator<Room>() {
                    @Override
                    public int compare(Room o1, Room o2) {
                        return o1.getRoomNumber() - o2.getRoomNumber();
                    }
                });
            }
            RoomAdapter.this.notifyDataSetChanged();
        }
        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    }

     String getRoomName(int roomNumber){
        return "room"+roomNumber;
    }

    class RoomViewHolder extends RecyclerView.ViewHolder{

        TextView roomNumber;
        TextView roomFloor;
        TextView roomType;
        TextView roomDayCost;
        CardView roomCardView;
        ImageView roomImageView;

        RoomViewHolder(View itemView) {
            super(itemView);
            roomNumber=(TextView) itemView.findViewById(R.id.room_number);
            roomFloor=(TextView) itemView.findViewById(R.id.room_floor);
            roomType=(TextView) itemView.findViewById(R.id.room_type);
            roomDayCost=(TextView) itemView.findViewById(R.id.room_day_cost);
            roomCardView = (CardView)itemView.findViewById(R.id.room_cardview);
            roomImageView = (ImageView)itemView.findViewById(R.id.room_image);
        }
    }
}
