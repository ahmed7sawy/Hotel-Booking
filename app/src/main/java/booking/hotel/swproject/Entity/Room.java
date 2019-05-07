package booking.hotel.swproject.Entity;

import android.os.Parcel;
import android.os.Parcelable;

public class Room implements Parcelable {

    private int roomNumber;
    private int floor;
    private float dayCost;
    private String roomType;

    public static final Creator<Room> CREATOR = new Creator<Room>() {
        @Override
        public Room createFromParcel(Parcel in) {
            return new Room(in);
        }

        @Override
        public Room[] newArray(int size) {
            return new Room[size];
        }
    };

    public Room() {
    }

    public Room(int roomNumber, int floor, float dayCost, String roomType) {
        this.roomNumber = roomNumber;
        this.floor = floor;
        this.dayCost = dayCost;
        this.roomType = roomType;
    }

    protected Room(Parcel in) {
        this.roomNumber = in.readInt();
        this.floor = in.readInt();
        this.dayCost = in.readFloat();
        this.roomType = in.readString();
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public float getDayCost() {
        return dayCost;
    }

    public void setDayCost(float dayCost) {
        this.dayCost = dayCost;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.roomNumber);
        parcel.writeInt(this.floor);
        parcel.writeFloat(this.dayCost);
        parcel.writeString(this.roomType);
    }

}
