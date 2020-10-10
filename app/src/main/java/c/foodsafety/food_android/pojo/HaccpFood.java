package c.foodsafety.food_android.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import lombok.Data;

@Data
public class HaccpFood extends Food implements Parcelable{
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


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeInt(this.id);
        dest.writeString(this.nutrient);
        dest.writeString(this.rawmtrl);
        dest.writeString(this.prdlstNm);
        dest.writeString(this.imgurl2);
        dest.writeString(this.barcode);
        dest.writeString(this.imgurl1);
        dest.writeString(this.productGb);
        dest.writeString(this.seller);
        dest.writeString(this.prdkindstate);
        dest.writeString(this.rnum);
        dest.writeString(this.manufacture);
        dest.writeString(this.prdkind);
        dest.writeString(this.capacity);
        dest.writeString(this.prdlstReportNo);
        dest.writeString(this.allergy);
        dest.writeString(this.category);
    }

    protected HaccpFood(Parcel in) {
        super(in);
        this.id = in.readInt();
        this.nutrient = in.readString();
        this.rawmtrl = in.readString();
        this.prdlstNm = in.readString();
        this.imgurl2 = in.readString();
        this.barcode = in.readString();
        this.imgurl1 = in.readString();
        this.productGb = in.readString();
        this.seller = in.readString();
        this.prdkindstate = in.readString();
        this.rnum = in.readString();
        this.manufacture = in.readString();
        this.prdkind = in.readString();
        this.capacity = in.readString();
        this.prdlstReportNo = in.readString();
        this.allergy = in.readString();
        this.category = in.readString();
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
