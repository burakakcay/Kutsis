package com.example.kutsis;

class Table {
    public long tableId;
    public boolean isReserved;

    public Table() {}

    public Table(long tableId, boolean isReserved) {
        this.tableId = tableId;
        this.isReserved = isReserved;
    }

    public long gettableId() {
        return tableId;
    }
    public void settableId(long tableId) {
        this.tableId = tableId;
    }
    public boolean getisReserved() {
        return isReserved;
    }
    public void setisReserved(boolean isReserved) {
        this.isReserved = isReserved;
    }
}

class Ogrenci {
    private String isim;
    private String soyisim;

    public Ogrenci() { }

    public Ogrenci(String isim, String soyisim) {
        this.isim = isim;
        this.soyisim = soyisim;
    }
    public String getIsim() {
        return isim;
    }
    public void setIsim(String isim) {
        this.isim = isim;
    }
    public String getSoyisim() {
        return soyisim;
    }
    public void setSoyisim(String soyisim) {
        this.soyisim = soyisim;
    }
}

//class Library extends Table {
//    public Integer id;
//    public String name;
//    public String qr_value;
//    public Table[] tables;
//
//    public Library() {};
//
//    public Library(String libName, Integer libId, String libQr, Integer tableId, Boolean isReserve) {}
//
//    public Integer getId(){
//        return id;
//    }
//    public void setId(Integer id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getQr_value() {
//        return qr_value;
//    }
//    public void setQr_value(String qr_value) {
//        this.qr_value = qr_value;
//    }
//}

