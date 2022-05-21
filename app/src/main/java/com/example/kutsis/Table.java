package com.example.kutsis;

import java.io.Serializable;

public class Table implements Serializable {
    private int tableId;
    private boolean isReserved;

    public Table(int tableId, boolean isReserved) {
        this.tableId = tableId;
        this.isReserved = isReserved;
    }

    public int getTableId () {
        return tableId;
    }
    public void setTableId(int tableId) {
        this.tableId = tableId;
    }

    public boolean getIsReserved() {
        return isReserved;
    }
    public void setIsReserved(boolean isReserved) {
        this.isReserved = isReserved;
    }

}


