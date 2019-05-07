package booking.hotel.swproject.Activities;

import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import booking.hotel.swproject.Adapter.RoomAdapter;
import booking.hotel.swproject.R;

public class ShowRooms extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_rooms);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view_rooms);
        recyclerView.addItemDecoration(new VerticalSpaceItemDecoration(20));
        RoomAdapter mAdapter = new RoomAdapter(ShowRooms.this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(ShowRooms.this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);


    }
    public class VerticalSpaceItemDecoration extends RecyclerView.ItemDecoration {
        private final int mVerticalSpaceHeight;

        public VerticalSpaceItemDecoration(int mVerticalSpaceHeight) {
            this.mVerticalSpaceHeight = mVerticalSpaceHeight;
        }

        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            outRect.bottom = this.mVerticalSpaceHeight;
        }
    }
}
