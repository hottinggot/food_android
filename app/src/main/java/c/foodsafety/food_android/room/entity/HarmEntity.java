package c.foodsafety.food_android.room.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.Date;

import c.foodsafety.food_android.pojo.HarmFood;
import lombok.Data;

@Data
@Entity(tableName = "harm_food")
public class HarmEntity extends HarmFood implements Serializable {
    @PrimaryKey(autoGenerate = true)
    public int mypageId;
    public Date savedDate;

//    public HarmEntity(){
//        super();
//    }
//
//    public HarmEntity( Date savedDate) {
//        this.savedDate = savedDate;
//    }

//    public HarmEntity() {
//
//    }
////
//    public HarmEntity(HarmFood h, Date savedDate) {
//        super(h);
//        this.savedDate = savedDate;
//    }


}
