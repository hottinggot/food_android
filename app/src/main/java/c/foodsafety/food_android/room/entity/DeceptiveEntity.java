package c.foodsafety.food_android.room.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Entity(tableName = "deceptive_food")
@Data
public class DeceptiveEntity implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String PRDUCT;
    private String ENTRPS;
    private String ADRES1;
    private String FOUND_CN;
    private String DSPS_DT;
    private String DSPS_CMMND;
    private String VIOLT;
    private String EVDNC_FILE;
    private String category;
    private Date savedDate;
}
