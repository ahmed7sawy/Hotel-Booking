package booking.hotel.swproject.Entity;

import android.os.Parcel;
import android.os.Parcelable;

public class Booking implements Parcelable {

    private String guestName;
    private String guestPhone;
    private String guestAddress;
    private int roomNumber;
    private long firstDayTimeStamp;
    private long lastDayTimeStamp;
    private long bookingCost;

    public static final Creator<Booking> CREATOR = new Creator<Booking>() {
        @Override
        public Booking createFromParcel(Parcel in) {
            return new Booking(in);
        }

        @Override
        public Booking[] newArray(int size) {
            return new Booking[size];
        }
    };

    public Booking() {
    }

    public Booking(String guestName, String guestPhone, String guestAddress, int roomNumber, long firstDayTimeStamp, long lastDayTimeStamp, long bookingCost) {
        this.guestName = guestName;
        this.guestPhone = guestPhone;
        this.guestAddress = guestAddress;
        this.roomNumber = roomNumber;
        this.firstDayTimeStamp = firstDayTimeStamp;
        this.lastDayTimeStamp = lastDayTimeStamp;
        this.bookingCost = bookingCost;
    }

    protected Booking(Parcel in) {
        this.guestName = in.readString();
        this.guestPhone = in.readString();
        this.guestAddress = in.readString();
        this.roomNumber = in.readInt();
        this.firstDayTimeStamp = in.readLong();
        this.lastDayTimeStamp = in.readLong();
        this.bookingCost = in.readLong();
    }

    public String getGuestName() {
        return guestName;
    }

    public void setGuestName(String guestName) {
        this.guestName = guestName;
    }

    public String getGuestPhone() {
        return guestPhone;
    }

    public void setGuestPhone(String guestPhone) {
        this.guestPhone = guestPhone;
    }

    public String getGuestAddress() {
        return guestAddress;
    }

    public void setGuestAddress(String guestAddress) {
        this.guestAddress = guestAddress;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public long getFirstDayTimeStamp() {
        return firstDayTimeStamp;
    }

    public void setFirstDayTimeStamp(long firstDayTimeStamp) {
        this.firstDayTimeStamp = firstDayTimeStamp;
    }

    public long getLastDayTimeStamp() {
        return lastDayTimeStamp;
    }

    public void setLastDayTimeStamp(long lastDayTimeStamp) {
        this.lastDayTimeStamp = lastDayTimeStamp;
    }

    public long getBookingCost() {
        return bookingCost;
    }

    public void setBookingCost(long bookingCost) {
        this.bookingCost = bookingCost;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.guestName);
        parcel.writeString(this.guestPhone);
        parcel.writeString(this.guestAddress);
        parcel.writeInt(this.roomNumber);
        parcel.writeLong(this.firstDayTimeStamp);
        parcel.writeLong(this.lastDayTimeStamp);
        parcel.writeLong(this.bookingCost);

    }


}
