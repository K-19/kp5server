package kursach.tables;

import javafx.util.Pair;
import kursach.Const;

import java.util.ArrayList;
import java.util.List;

public class HealthAppTable implements db_Table{
    private final String nameTable = Const.HEALTH_APP_TABLE;
    private final List<Pair<String, Object>> columns = new ArrayList<>();
    {
        columns.add(new Pair<>(Const.HEALTH_APP_ID, 1));
        columns.add(new Pair<>(Const.HEALTH_APP_ID_USER, 1));
        columns.add(new Pair<>(Const.HEALTH_APP_TERM, 1));
        columns.add(new Pair<>(Const.HEALTH_APP_BEGIN_TERM, ""));
        columns.add(new Pair<>(Const.HEALTH_APP_FEE, ""));
        columns.add(new Pair<>(Const.HEALTH_APP_PAYMENT, ""));
        columns.add(new Pair<>(Const.HEALTH_APP_DOLJ, ""));
        columns.add(new Pair<>(Const.HEALTH_APP_HARM_HAP, false));
        columns.add(new Pair<>(Const.HEALTH_APP_HEALTH_HAP, false));
        columns.add(new Pair<>(Const.HEALTH_APP_INVALID_HAP, false));
        columns.add(new Pair<>(Const.HEALTH_APP_DEATH, false));
        columns.add(new Pair<>(Const.HEALTH_APP_COVID, false));
        columns.add(new Pair<>(Const.HEALTH_APP_SPORT, false));
        columns.add(new Pair<>(Const.HEALTH_APP_RELAX, false));
        columns.add(new Pair<>(Const.HEALTH_APP_INVALID12, false));
        columns.add(new Pair<>(Const.HEALTH_APP_PROCESSED, false));
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
