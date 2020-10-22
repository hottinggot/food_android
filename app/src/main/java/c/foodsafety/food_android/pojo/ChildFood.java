package c.foodsafety.food_android.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

import lombok.Data;

@Data
public class ChildFood extends Food implements Serializable {

    private int id;
    private String PRDLST_NM;
    private String BSSH_NM;
    private String LCNS_NO;
    private String CN_WT;
    private String CHILD_FFQ_CRTFC_NO;
    private String CHILD_FAVOR_FOOD_TYPE_NM;
    private String APPN_BGN_DT;
    private String PRDLST_CD_NM;
    private String APPN_END_DT;
    private String category;
    private String imgUrl;
    private int save;
    private String temp;

}
