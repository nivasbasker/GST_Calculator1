package com.codewithzio.gstcalculator;

import android.os.Parcel;
import android.os.Parcelable;

public class ModelProducts implements Parcelable {
    public static final Creator<ModelProducts> CREATOR = new Creator<ModelProducts>() {
        @Override
        public ModelProducts createFromParcel(Parcel in) {
            return new ModelProducts(in);
        }

        @Override
        public ModelProducts[] newArray(int size) {
            return new ModelProducts[size];
        }
    };
    String Name;
    int net;
    String Desc;

    int gst;
    int gross;


    public ModelProducts(String Name, String Desc, int net,  int gst, int gross) {
        this.Name = Name;
        this.net = net;
        this.Desc = Desc;
        this.gst = gst;
        this.gross = gross;
    }

    public ModelProducts() {
    }

    protected ModelProducts(Parcel in) {
        Name = in.readString();
        net = in.readInt();
        Desc = in.readString();
        gst = in.readInt();
        gross = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(Name);
        dest.writeInt(net);
        dest.writeString(Desc);
        dest.writeInt(gst);
        dest.writeInt(gross);
    }

    public int getNet() {
        return net;
    }

    public void setNet(int net) {
        this.net = net;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public String getDesc() {
        return Desc;
    }

    public void setDesc(String desc) {
        this.Desc = desc;
    }

    public int getGst() {
        return gst;
    }

    public void setGst(int gst) {
        this.gst = gst;
    }

    public int getGross() {
        return gross;
    }

    public void setGross(int gross) {
        this.gross = gross;
    }

}
