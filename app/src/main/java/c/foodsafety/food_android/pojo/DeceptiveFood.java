package c.foodsafety.food_android.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import lombok.Data;


@Data
public class DeceptiveFood extends Food implements Parcelable {
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeInt(this.id);
        dest.writeString(this.PRDUCT);
        dest.writeString(this.ENTRPS);
        dest.writeString(this.ADRES1);
        dest.writeString(this.FOUND_CN);
        dest.writeString(this.DSPS_DT);
        dest.writeString(this.DSPS_CMMND);
        dest.writeString(this.VIOLT);
        dest.writeString(this.EVDNC_FILE);
        dest.writeString(this.category);
    }


    protected DeceptiveFood(Parcel in) {
        super(in);
        this.id = in.readInt();
        this.PRDUCT = in.readString();
        this.ENTRPS = in.readString();
        this.ADRES1 = in.readString();
        this.FOUND_CN = in.readString();
        this.DSPS_DT = in.readString();
        this.DSPS_CMMND = in.readString();
        this.VIOLT = in.readString();
        this.EVDNC_FILE = in.readString();
        this.category = in.readString();
    }

    public static final Creator<DeceptiveFood> CREATOR = new Creator<DeceptiveFood>() {
        @Override
        public DeceptiveFood createFromParcel(Parcel source) {
            return new DeceptiveFood(source);
        }

        @Override
        public DeceptiveFood[] newArray(int size) {
            return new DeceptiveFood[size];
        }
    };
}
