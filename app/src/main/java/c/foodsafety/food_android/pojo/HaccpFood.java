package c.foodsafety.food_android.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import lombok.Data;

@Data
public class HaccpFood extends Food implements Parcelable{
    private int id;
    private String businessitem;
    private String businessitemNm;
    private String rnum;
    private String company;
    private String prdlstNm;
    private String appointno;
    private String productGb;
    private String prdlstReportNo;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.businessitem);
        dest.writeString(this.businessitemNm);
        dest.writeString(this.rnum);
        dest.writeString(this.company);
        dest.writeString(this.prdlstNm);
        dest.writeString(this.appointno);
        dest.writeString(this.productGb);
        dest.writeString(this.prdlstReportNo);
    }

    protected HaccpFood(Parcel in) {
        super(in);
        this.id = in.readInt();
        this.businessitem = in.readString();
        this.businessitemNm = in.readString();
        this.rnum = in.readString();
        this.company = in.readString();
        this.prdlstNm = in.readString();
        this.appointno = in.readString();
        this.productGb = in.readString();
        this.prdlstReportNo = in.readString();
    }

    public static final Creator<HaccpFood> CREATOR = new Creator<HaccpFood>() {
        @Override
        public HaccpFood createFromParcel(Parcel source) {
            return new HaccpFood(source);
        }

        @Override
        public HaccpFood[] newArray(int size) {
            return new HaccpFood[size];
        }
    };
}
