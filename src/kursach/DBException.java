package kursach;

public class DBException extends Exception{
    public String toString() {
        return "Ошибка!!! Данный договор не может быть заключён один лицом больше 1-го раза";
    }
}
