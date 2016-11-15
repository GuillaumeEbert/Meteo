package shindra.meteo.City.Internal;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Guillaume on 08/11/2016.
 */

public class Main implements Parcelable {
    @SerializedName("temp")
    private Double aTemperature;
    @SerializedName("pressure")
    private double aPressure;
    @SerializedName("humidity")
    private int aHumidityPercent;
    @SerializedName("temp_min")
    private double aTempMini;
    @SerializedName("temp_max")
    private double aTempMax;

    protected Main(Parcel in) {
        aTemperature = in.readDouble();
        aPressure = in.readDouble();
        aHumidityPercent = in.readInt();
        aTempMini = in.readDouble();
        aTempMax = in.readDouble();
    }

    public static final Creator<Main> CREATOR = new Creator<Main>() {
        @Override
        public Main createFromParcel(Parcel in) {
            return new Main(in);
        }

        @Override
        public Main[] newArray(int size) {
            return new Main[size];
        }
    };

    public Double getTemperature() {
        return aTemperature;
    }

    public Double getPressure() {
        return aPressure;
    }

    public int getHumidityPercent() {
        return aHumidityPercent;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(aTemperature);
        dest.writeDouble(aPressure);
        dest.writeInt(aHumidityPercent);
        dest.writeDouble(aTempMini);
        dest.writeDouble(aTempMax);
    }
}
