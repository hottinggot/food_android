package c.foodsafety.food_android.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import lombok.Data;

@Data
public class Food implements Parcelable {
    private String title;
    private String type;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.type);
    }

    protected Food(Parcel in) {
        this.title = in.readString();
        this.type = in.readString();
    }

    public static final Creator<Food> CREATOR = new Creator<Food>() {
        @Override
        public Food createFromParcel(Parcel source) {
            return new Food(source);
        }

        @Override
        public Food[] newArray(int size) {
            return new Food[size];
        }
    };
}
