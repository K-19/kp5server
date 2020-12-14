package kursach.tables;

import javafx.util.Pair;
import kursach.Const;

import java.util.ArrayList;
import java.util.List;

public class UsersTable implements db_Table{
    private final String nameTable = Const.USER_TABLE;
    private final List<Pair<String, Object>> columns = new ArrayList<Pair<String, Object>>();
    {
        columns.add(new Pair<>(Const.USER_ID, 1));
        columns.add(new Pair<>(Const.USER_N_PASSPORT, ""));
        columns.add(new Pair<>(Const.USER_IDN_PASSPORT, ""));
        columns.add(new Pair<>(Const.USER_PHONE, ""));
        columns.add(new Pair<>(Const.USER_EMAIL, ""));
        columns.add(new Pair<>(Const.USER_PASSWORD, ""));
        columns.add(new Pair<>(Const.USER_BIRTHDAY, ""));
        columns.add(new Pair<>(Const.USER_REG_DAY, ""));
        columns.add(new Pair<>(Const.USER_IS_ADMIN, false));
        columns.add(new Pair<>(Const.USER_LOGIN, ""));
        columns.add(new Pair<>(Const.USER_NAME, ""));
        columns.add(new Pair<>(Const.USER_LASTNAME, ""));
    }

    public String getNameTable() { return nameTable; }
    public List<Pair<String, Object>> getColumns() { return columns; }

    public List<Pair<String, Object>> getColumnsForUpdate() {
        List<Pair<String, Object>> list = new ArrayList<>();
        for (int i = 0; i < columns.size(); i++) {
            if (i != 0 && i != 7 && i != 8) list.add(columns.get(i));
        }
        return list;
    }
    public List<Pair<String, Object>> getColumnsForInsert() {
        List<Pair<String, Object>> list = new ArrayList<>();
        for (int i = 0; i < columns.size(); i++) {
            if (i != 0) list.add(columns.get(i));
        }
        return list;
    }
}
