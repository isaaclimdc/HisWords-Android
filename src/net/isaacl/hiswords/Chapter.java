package net.isaacl.hiswords;

import android.os.Parcel;
import android.os.Parcelable;

public class Chapter implements Parcelable {
	public Book book;
	public int number;
	
	public Chapter(Book book, int number) {
		this.book = book;
		this.number = number;
	}
	
	@Override
	public String toString() {
		return (this.book.name + " " + this.number);
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeParcelable((Parcelable)book, flags);
		dest.writeInt(number);
	}
	
    public static final Parcelable.Creator<Chapter> CREATOR = new Parcelable.Creator<Chapter>() {
        public Chapter createFromParcel(Parcel in) {
            return new Chapter(in);
        }

        public Chapter[] newArray(int size) {
            return new Chapter[size];
        }
    };
    
    private Chapter(Parcel in) {
    	book = in.readParcelable(Book.class.getClassLoader());
        number = in.readInt();
    }
}
