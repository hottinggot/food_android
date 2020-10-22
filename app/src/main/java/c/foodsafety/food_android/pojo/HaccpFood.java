package c.foodsafety.food_android.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

import lombok.Data;

@Data
public class HaccpFood extends Food implements Serializable {
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
    private int save;
    private String temp;

}
