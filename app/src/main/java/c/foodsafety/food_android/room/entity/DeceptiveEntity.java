package c.foodsafety.food_android.room.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.Date;

import c.foodsafety.food_android.pojo.DeceptiveFood;
import lombok.Data;

@Entity(tableName = "deceptive_food")
@Data
public class DeceptiveEntity extends DeceptiveFood implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int mypageId;
    private Date savedDate;
}
