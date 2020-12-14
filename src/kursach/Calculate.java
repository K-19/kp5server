package kursach;

import javafx.util.Pair;

public class Calculate {
    public static Pair<Double, Double> calculateCar(Integer year, Double vEngine, Double lifting, Integer seats, Integer power, Integer accidents, Integer experience, Boolean dolj, Integer term) {
        Double sum = 1000000d;
        if (year != 2020) sum /= (2020 - year);
        sum *= ((vEngine * 0.05) + 1);
        sum /= ((lifting * 0.05) + 1);
        sum /= ((seats * 0.05) + 1);
        sum *= ((power * 0.001) + 1);
        if(accidents != 0) sum /= accidents;
        sum *= ((experience * 0.1) + 1);
        if(dolj) sum *= 1.5;

        return new Pair(sum,(sum / (term * 12)) / 1.5);
    }
    public static Pair<Double, Double> calculateProperty(Integer area, Integer rooms, Integer year, Integer floor, Integer doors, Boolean steelDoor, Boolean codeLock, Boolean unfireSystem, Boolean security, Boolean material, Boolean risk, Boolean dolj, Integer term) {
        Double sum = area * 1000d;
        sum *= ((rooms * 0.1) + 1);
        if (year != 2020)  sum /= (2020 - year);
        sum *= ((floor * 0.05) + 1);
        sum /= doors;
        if(steelDoor) sum *= 1.2;
        if(codeLock) sum *= 1.2;
        if(unfireSystem) sum *= 1.2;
        if(security) sum *= 1.5;
        if(material) sum *= 1.2;
        if(risk) sum /= 2;
        if(dolj) sum *= 1.5;

        return new Pair(sum,(sum / (term * 12)) / 1.5);
    }
    public static Pair<Double, Double> calculateHealth(Boolean harmHealth, Boolean harmLife, Boolean harmInvalid, Boolean deathIll, Boolean covid, Boolean sport, Boolean relax, Boolean invalid12, Boolean dolj, Integer term) {
        Double sum = 10000d;
        if(harmHealth) sum *= 1.1;
        if(harmLife) sum *= 1.1;
        if(harmInvalid) sum *= 1.2;
        if(deathIll) sum *= 1.5;
        if(covid) sum *= 1.1;
        if(sport) sum *= 1.1;
        if(relax) sum /= 1.2;
        if(invalid12) sum *= 1.1;
        if(dolj) sum *= 1.5;

        return new Pair(sum,(sum / (term * 12)) / 1.5);
    }
    public static String calculate(Integer id, String[] params) {
        Pair<Double, Double> result = null;
        switch(id) {
            case 1: result = calculateCar(Integer.parseInt(params[2]), Double.parseDouble(params[3]), Double.parseDouble(params[4]), Integer.parseInt(params[5]),
                    Integer.parseInt(params[6]), Integer.parseInt(params[7]), Integer.parseInt(params[8]), Boolean.parseBoolean(params[9]), Integer.parseInt(params[10]));
            break;
            case 2: result = calculateProperty(Integer.parseInt(params[2]), Integer.parseInt(params[3]), Integer.parseInt(params[4]), Integer.parseInt(params[5]),
                    Integer.parseInt(params[6]), Boolean.parseBoolean(params[7]), Boolean.parseBoolean(params[8]), Boolean.parseBoolean(params[9]),
                    Boolean.parseBoolean(params[10]), Boolean.parseBoolean(params[11]), Boolean.parseBoolean(params[12]), Boolean.parseBoolean(params[13]),
                    Integer.parseInt(params[14]));
            break;
            case 3: result = calculateHealth(Boolean.parseBoolean(params[2]),Boolean.parseBoolean(params[3]),Boolean.parseBoolean(params[4]),Boolean.parseBoolean(params[5]),
                    Boolean.parseBoolean(params[6]),Boolean.parseBoolean(params[7]),Boolean.parseBoolean(params[8]),Boolean.parseBoolean(params[9]),
                    Boolean.parseBoolean(params[10]), Integer.parseInt(params[11]));
            break;
        }
        return Double.toString(result.getKey()) + Const.DELIMITER + Double.toString(result.getValue());
    }
}
