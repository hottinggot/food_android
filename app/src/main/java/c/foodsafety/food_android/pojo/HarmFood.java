package c.foodsafety.food_android.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

import lombok.Data;

@Data
public class HarmFood extends Food implements Parcelable {
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


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeInt(this.id);
        dest.writeString(this.PRDLST_CD);
        dest.writeString(this.FRMLCUNIT);
        dest.writeString(this.RTRVL_GRDCD_NM);
        dest.writeString(this.BSSHNM);
        dest.writeString(this.DISTBTMLMT);
        dest.writeString(this.RTRVLPLANDOC_RTRVLMTHD);
        dest.writeString(this.BRCDNO);
        dest.writeString(this.RTRVLDSUSE_SEQ);
        dest.writeLong(this.CRET_DTM != null ? this.CRET_DTM.getTime() : -1);
        dest.writeString(this.PRDLST_REPORT_NO);
        dest.writeString(this.MNFDT);
        dest.writeString(this.PRDLST_CD_NM);
        dest.writeString(this.RTRVLPRVNS);
        dest.writeString(this.PRDTNM);
        dest.writeString(this.PRCSCITYPOINT_TELNO);
        dest.writeString(this.ADDR);
        dest.writeString(this.IMG_FILE_PATH);
        dest.writeString(this.PRDLST_TYPE);
        dest.writeString(this.category);
        dest.writeInt(this.save);
        dest.writeString(this.temp);
    }

    protected HarmFood(Parcel in) {
        super(in);
        this.id = in.readInt();
        this.PRDLST_CD = in.readString();
        this.FRMLCUNIT = in.readString();
        this.RTRVL_GRDCD_NM = in.readString();
        this.BSSHNM = in.readString();
        this.DISTBTMLMT = in.readString();
        this.RTRVLPLANDOC_RTRVLMTHD = in.readString();
        this.BRCDNO = in.readString();
        this.RTRVLDSUSE_SEQ = in.readString();
        long tmpCRET_DTM = in.readLong();
        this.CRET_DTM = tmpCRET_DTM == -1 ? null : new Date(tmpCRET_DTM);
        this.PRDLST_REPORT_NO = in.readString();
        this.MNFDT = in.readString();
        this.PRDLST_CD_NM = in.readString();
        this.RTRVLPRVNS = in.readString();
        this.PRDTNM = in.readString();
        this.PRCSCITYPOINT_TELNO = in.readString();
        this.ADDR = in.readString();
        this.IMG_FILE_PATH = in.readString();
        this.PRDLST_TYPE = in.readString();
        this.category = in.readString();
        this.save = in.readInt();
        this.temp = in.readString();
    }

    public static final Creator<HarmFood> CREATOR = new Creator<HarmFood>() {
        @Override
        public HarmFood createFromParcel(Parcel source) {
            return new HarmFood(source);
        }

        @Override
        public HarmFood[] newArray(int size) {
            return new HarmFood[size];
        }
    };
}
