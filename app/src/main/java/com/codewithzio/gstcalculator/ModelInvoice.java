package com.codewithzio.gstcalculator;

import android.os.Parcel;
import android.os.Parcelable;

public class ModelInvoice implements Parcelable {
    public static final Creator<ModelInvoice> CREATOR = new Creator<ModelInvoice>() {
        @Override
        public ModelInvoice createFromParcel(Parcel in) {
            return new ModelInvoice(in);
        }

        @Override
        public ModelInvoice[] newArray(int size) {
            return new ModelInvoice[size];
        }
    };
    String InvNum;
    int net, gst, gross;
    String Date;

    public static Creator<ModelInvoice> getCREATOR() {
        return CREATOR;
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

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    //List<ModelProducts> itemList;
    String customer;


    public ModelInvoice(String InvNum, String Date, int gross) {
        this.InvNum = InvNum;
        this.gross = gross;
        this.Date = Date;

    }

    public ModelInvoice(String invNum, int net, int gst, int gross, String date, String customer) {
        InvNum = invNum;
        this.net = net;
        this.gst = gst;
        this.gross = gross;
        Date = date;
        this.customer = customer;
    }


    public ModelInvoice() {
    }

    protected ModelInvoice(Parcel in) {
        InvNum = in.readString();
        gross = in.readInt();
        Date = in.readString();

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(InvNum);
        dest.writeInt(gross);
        dest.writeString(Date);

    }

    public int getNet() {
        return net;
    }

    public void setNet(int net) {
        this.net = net;
    }

    public String getInvNum() {
        return InvNum;
    }

    public void setInvNum(String invNum) {
        InvNum = invNum;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }
}
