package kursach.tables;

import javafx.util.Pair;
import kursach.Const;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VehicleAppTable implements db_Table {
    private final String nameTable = Const.VEHICLE_APP_TABLE;
    private final List<Pair<String, Object>> columns = new ArrayList<Pair<String, Object>>();
    {
        columns.add(new Pair<>(Const.VEHICLE_APP_ID_USER, 1));
        columns.add(new Pair<>(Const.VEHICLE_APP_TERM, 1));
        columns.add(new Pair<>(Const.VEHICLE_APP_BEGIN_TERM, ""));
        columns.add(new Pair<>(Const.VEHICLE_APP_FEE, ""));
        columns.add(new Pair<>(Const.VEHICLE_APP_PAYMENT, ""));
        columns.add(new Pair<>(Const.VEHICLE_APP_DOLJ, ""));
        columns.add(new Pair<>(Const.VEHICLE_APP_EXPERIANCE, 1));
        columns.add(new Pair<>(Const.VEHICLE_APP_MODEL, ""));
        columns.add(new Pair<>(Const.VEHICLE_APP_SIGN, ""));
        columns.add(new Pair<>(Const.VEHICLE_APP_BODY, ""));
        columns.add(new Pair<>(Const.VEHICLE_APP_V_ENGINE, ""));
        columns.add(new Pair<>(Const.VEHICLE_APP_LIFTING, ""));
        columns.add(new Pair<>(Const.VEHICLE_APP_SEATS, 1));
        columns.add(new Pair<>(Const.VEHICLE_APP_POWER, 1));
        columns.add(new Pair<>(Const.VEHICLE_APP_ACCIDENTS, 1));
        columns.add(new Pair<>(Const.VEHICLE_APP_YEAR, 1));
        columns.add(new Pair<>(Const.VEHICLE_APP_BATTERY, ""));
        columns.add(new Pair<>(Const.VEHICLE_APP_PROCESSED, false));
    }




    public String getNameTable() { return nameTable; }
    public List<Pair<String, Object>> getColumns() { return columns; }
    public List<Pair<String, Object>> getColumnsForInsert() { return columns; }
}
