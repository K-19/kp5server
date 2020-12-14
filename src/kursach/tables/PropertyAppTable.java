package kursach.tables;

import javafx.util.Pair;
import kursach.Const;

import java.util.ArrayList;
import java.util.List;

public class PropertyAppTable implements db_Table{
    private final String nameTable = Const.PROPERTY_APP_TABLE;
    private final List<Pair<String, Object>> columns = new ArrayList<>();
    {
        columns.add(new Pair<>(Const.PROPERTY_APP_ID, 1));
        columns.add(new Pair<>(Const.PROPERTY_APP_ID_USER, 1));
        columns.add(new Pair<>(Const.PROPERTY_APP_TERM, 1));
        columns.add(new Pair<>(Const.PROPERTY_APP_BEGIN_TERM, ""));
        columns.add(new Pair<>(Const.PROPERTY_APP_FEE, ""));
        columns.add(new Pair<>(Const.PROPERTY_APP_PAYMENT, ""));
        columns.add(new Pair<>(Const.PROPERTY_APP_DOLJ, ""));
        columns.add(new Pair<>(Const.PROPERTY_APP_ADDRESS, ""));
        columns.add(new Pair<>(Const.PROPERTY_APP_AREA, 1));
        columns.add(new Pair<>(Const.PROPERTY_APP_ROOMS, 1));
        columns.add(new Pair<>(Const.PROPERTY_APP_YEAR, 1));
        columns.add(new Pair<>(Const.PROPERTY_APP_FLOOR, 1));
        columns.add(new Pair<>(Const.PROPERTY_APP_DOORS, 1));
        columns.add(new Pair<>(Const.PROPERTY_APP_STELLDOOR, false));
        columns.add(new Pair<>(Const.PROPERTY_APP_CODELOCK, false));
        columns.add(new Pair<>(Const.PROPERTY_APP_UNFIRE_SYSTEM, false));
        columns.add(new Pair<>(Const.PROPERTY_APP_SECURITY, false));
        columns.add(new Pair<>(Const.PROPERTY_APP_UNFIRE_MATERIAL, false));
        columns.add(new Pair<>(Const.PROPERTY_APP_RISK, false));
        columns.add(new Pair<>(Const.PROPERTY_APP_PROCESSED, false));
    }

    public String getNameTable() { return nameTable; }
    public List<Pair<String, Object>> getColumns() { return columns; }
    public List<Pair<String, Object>> getColumnsForInsert() {
        List<Pair<String, Object>> list = new ArrayList<>();
        for (int i = 0; i < columns.size(); i++) {
            if (i != 0) list.add(columns.get(i));
        }
        return list;
    }
}
