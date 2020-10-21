package c.foodsafety.food_android.room.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Entity(tableName = "haccp_food")
@Data
public class HaccpEntity implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String nutrient;
    private String rawmtrl;
    private String prdlstNm;
    private String imgurl2;
    private String barcode;
    private String imgurl1;
    private String productGb;
    private String seller;
    private String prdkindstate;
    private String rnum;
    private String manufacture;
    private String prdkind;
    private String capacity;
    private String prdlstReportNo;
    private String allergy;
    private String category;
    private Date savedDate;
}
