package com.codewithzio.gstcalculator;

public class DBConstants {

    public DBConstants() {
    }

    public static class ProductTable {
        public static final String COL_NAME = "name";
        public static final String COL_DESC = "desc";
        public static final String COL_NET = "net";
        public static final String COL_GST = "gst";
        public static final String COL_GROSS = "gross";
        public static String _ID = "id";
    }

    public static class InvoiceTable{

        public static final String COL_CUSTOMER = "customer";
        public static final String COL_DATE = "date";
        public static final String COL_BILL = "bill";
        public static final String COL_NET = "net";
        public static final String COL_GST = "gst";
        public static final String COL_GROSS = "gross";
        public static String _ID = "id";
    }
}
