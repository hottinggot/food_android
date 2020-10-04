package c.foodsafety.food_android.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import lombok.Data;

@Data
public class ChildFood extends Food implements Parcelable {

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


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.PRDLST_NM);
        dest.writeString(this.BSSH_NM);
        dest.writeString(this.LCNS_NO);
        dest.writeString(this.CN_WT);
        dest.writeString(this.CHILD_FFQ_CRTFC_NO);
        dest.writeString(this.CHILD_FAVOR_FOOD_TYPE_NM);
        dest.writeString(this.APPN_BGN_DT);
        dest.writeString(this.PRDLST_CD_NM);
        dest.writeString(this.APPN_END_DT);
    }

    public ChildFood() {
    }

    protected ChildFood(Parcel in) {
        this.id = in.readInt();
        this.PRDLST_NM = in.readString();
        this.BSSH_NM = in.readString();
        this.LCNS_NO = in.readString();
        this.CN_WT = in.readString();
        this.CHILD_FFQ_CRTFC_NO = in.readString();
        this.CHILD_FAVOR_FOOD_TYPE_NM = in.readString();
        this.APPN_BGN_DT = in.readString();
        this.PRDLST_CD_NM = in.readString();
        this.APPN_END_DT = in.readString();
    }

    public static final Creator<ChildFood> CREATOR = new Creator<ChildFood>() {
        @Override
        public ChildFood createFromParcel(Parcel source) {
            return new ChildFood(source);
        }

        @Override
        public ChildFood[] newArray(int size) {
            return new ChildFood[size];
        }
    };
}
