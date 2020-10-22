package c.foodsafety.food_android.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class HarmFood extends Food implements Serializable {
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
    private int save;
    private String temp;


//    public HarmFood(HarmFood h) {
////        this.id = h.id;
////        this.PRDLST_CD = h.PRDLST_CD;
////        this.FRMLCUNIT = h.FRMLCUNIT;
////        this.RTRVL_GRDCD_NM = h.RTRVL_GRDCD_NM;
////        this.BSSHNM = h.BSSHNM;
////        this.DISTBTMLMT = h.DISTBTMLMT;
////        this.RTRVLPLANDOC_RTRVLMTHD = h.RTRVLPLANDOC_RTRVLMTHD;
////        this.BRCDNO = h.BRCDNO;
////        this.RTRVLDSUSE_SEQ = h.RTRVLDSUSE_SEQ;
////        this.CRET_DTM = h.CRET_DTM;
////        this.PRDLST_REPORT_NO = h.PRDLST_REPORT_NO;
////        this.MNFDT = h.MNFDT;
////        this.PRDLST_CD_NM = h.PRDLST_CD_NM;
////        this.RTRVLPRVNS = h.RTRVLPRVNS;
////        this.PRDTNM = h.PRDTNM;
////        this.PRCSCITYPOINT_TELNO = h.PRCSCITYPOINT_TELNO;
////        this.ADDR = h.ADDR;
////        this.IMG_FILE_PATH = h.IMG_FILE_PATH;
////        this.PRDLST_TYPE = h.PRDLST_TYPE;
////        this.category = h.category;
////        this.save = h.save;
////        this.temp = h.temp;
////    }
}
