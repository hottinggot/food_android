package c.foodsafety.food_android.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

import lombok.Data;


@Data
public class DeceptiveFood extends Food implements Serializable {
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
    private int save;
    private String temp;

}
