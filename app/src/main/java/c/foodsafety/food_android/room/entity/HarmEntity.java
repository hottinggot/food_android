package c.foodsafety.food_android.room.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;


@Entity(tableName = "harm_food")
@Data
public class HarmEntity implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String PRDLST_CD;
    private String FRMLCUNIT;
    private String RTRVL_GRDCD_NM;
    private String BSSHNM;
    private String DISTBTMLMT;
    private String RTRVLPLANDOC_RTRVLMTHD;
    private String BRCDNO;
    private String RTRVLDSUSE_SEQ;
    private Date CRET_DTM;
    private String PRDLST_REPORT_NO;
    private String MNFDT;
    private String PRDLST_CD_NM;
    private String RTRVLPRVNS;
    private String PRDTNM;
    private String PRCSCITYPOINT_TELNO;
    private String ADDR;
    private String IMG_FILE_PATH;
    private String PRDLST_TYPE;
    private String category;
    private Date savedDate;
}
