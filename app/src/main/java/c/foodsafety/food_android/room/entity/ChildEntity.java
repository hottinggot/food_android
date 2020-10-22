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
import c.foodsafety.food_android.pojo.Food;
import lombok.Data;

@Data
@Entity(tableName = "child_food")
public class ChildEntity extends ChildFood implements Serializable {
    @PrimaryKey(autoGenerate = true)
    public int mypageId;
    public Date savedDate;

}
