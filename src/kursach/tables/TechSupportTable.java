package kursach.tables;

import javafx.util.Pair;
import kursach.Const;

import java.util.ArrayList;
import java.util.List;

public class TechSupportTable implements db_Table{
    private final String nameTable = Const.TECHSUPPORT_TABLE;
    private final List<Pair<String, Object>> columns = new ArrayList<Pair<String, Object>>();
    {
        columns.add(new Pair<>(Const.TECHSUPPORT_ID, 1));
        columns.add(new Pair<>(Const.TECHSUPPORT_ID_USER, 1));
        columns.add(new Pair<>(Const.TECHSUPPORT_QUESTION, ""));
        columns.add(new Pair<>(Const.TECHSUPPORT_CLOSED, false));
        columns.add(new Pair<>(Const.TECHSUPPORT_ID_ADMIN, 1));
        columns.add(new Pair<>(Const.TECHSUPPORT_ANSWER, ""));
    }




    public String getNameTable() { return nameTable; }
    public List<Pair<String, Object>> getColumns() { return columns; }
    public List<Pair<String, Object>> getColumnsForInsert() {
        List<Pair<String, Object>> list = new ArrayList<>();
        list.add(columns.get(1));
        list.add(columns.get(2));
        list.add(columns.get(3));
        return list;
    }
}
