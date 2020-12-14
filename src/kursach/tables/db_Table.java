package kursach.tables;

import javafx.util.Pair;

import java.util.List;
import java.util.Map;

public interface db_Table {
    public String getNameTable();
    public List<Pair<String, Object>> getColumns();
    public List<Pair<String, Object>> getColumnsForInsert();
}
