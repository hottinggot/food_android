package c.foodsafety.food_android.room.entity;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.versionedparcelable.ParcelField;
import androidx.versionedparcelable.VersionedParcelize;

import java.io.Serializable;
import java.util.Date;

import c.foodsafety.food_android.pojo.ChildFood;
import lombok.Data;

@Data

@Entity(tableName = "child_food")
public class ChildEntity extends ChildFood{
    @PrimaryKey(autoGenerate = true)
    private int mypageId;
    private Date savedDate;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeInt(this.mypageId);
        dest.writeLong(this.savedDate != null ? this.savedDate.getTime() : -1);
    }

    protected ChildEntity(Parcel in) {
        super(in);
        this.mypageId = in.readInt();
        long tmpSavedDate = in.readLong();
        this.savedDate = tmpSavedDate == -1 ? null : new Date(tmpSavedDate);
    }

    public static final Creator<ChildEntity> CREATOR = new Creator<ChildEntity>() {
        @Override
        public ChildEntity createFromParcel(Parcel source) {
            return new ChildEntity(source);
        }

        @Override
        public ChildEntity[] newArray(int size) {
            return new ChildEntity[size];
        }
    };
}
