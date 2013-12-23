package net.isaacl.hiswords;

import android.os.Parcel;
import android.os.Parcelable;

public class Book implements Parcelable {
	public String name;
	public int numChapts;
	public String synopsis;
	public Boolean isOldTestament;
	
	public Book() {
	
	}
	
	@Override
	public String toString() {
		return ("Book: " + this.name +
				", NumChapts: " + this.numChapts);
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(name);
		dest.writeInt(numChapts);
		dest.writeString(synopsis);
		dest.writeByte((byte)(isOldTestament ? 1 : 0)); 
	}
	
	public static final Parcelable.Creator<Book> CREATOR = new Parcelable.Creator<Book>() {
        public Book createFromParcel(Parcel in) {
            return new Book(in);
        }

        public Book[] newArray(int size) {
            return new Book[size];
        }
    };
    
    private Book(Parcel in) {
        name = in.readString();
        numChapts = in.readInt();
        synopsis = in.readString();
        isOldTestament = in.readByte() != 0;
    }
}
