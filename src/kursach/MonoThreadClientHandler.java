package kursach;

import org.postgresql.util.PSQLException;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MonoThreadClientHandler implements Runnable {


    private Socket clientDialog;

    public MonoThreadClientHandler(Socket client) {
        this.clientDialog = client;
    }

    @Override
    public void run() {

        try {
            DataOutputStream out = new DataOutputStream(clientDialog.getOutputStream());
            DataInputStream in = new DataInputStream(clientDialog.getInputStream());
            while (!clientDialog.isClosed()) {
                String entry;
                try {
                    entry = in.readUTF();
                } catch(SocketException e) {
                    MainController.exitClient();
                    MainController.logMessage("Клиент " + clientDialog.getInetAddress().getCanonicalHostName() + ":" + clientDialog.getPort() + " отключён");
                    return;
                }
                String[] parametrs = entry.split(Const.DELIMITER);

                String answer = null;
                DatabaseHandler dbHandler = new DatabaseHandler(clientDialog);

                switch(Code.valueOf(parametrs[0])) {
                    case AUTHORISATION: answer = AuthorisationUser(parametrs, clientDialog); break;
                    case REGISTRATION: answer = RegistrationUser(false, parametrs, clientDialog); break;
                    case NEW_APP_VEHICLE: answer = AddNewVehicleApplication(parametrs, clientDialog); break;
                    case NEW_APP_PROPERTY: answer = AddNewPropertyApplication(parametrs, clientDialog); break;
                    case NEW_APP_HEALTH: answer = AddNewHealthApplication(parametrs, clientDialog); break;
                    case LIST_APPLICATIONS: answer = dbHandler.getApplicationsInfo(parametrs[1]); break;
                    case LIST_CONTRACTS: answer = dbHandler.getListOfContracts(parametrs[1]); break;
                    case ADMIN_USER_INFO: answer = dbHandler.getUserInfo(parametrs[1]); break;
                    case ADMIN_EDIT_USER: answer = dbHandler.editUser(parametrs); break;
                    case ADMIN_ALL_USERS: answer = dbHandler.getAllUsers(); break;
                    case ADMIN_ALL_APPS: answer = dbHandler.getAllApps(); break;
                    case ADMIN_ALL_CONTRACTS: answer = dbHandler.getAllContracts(); break;
                    case ADMIN_APP_FOR_REDACTION: answer = getOneApp(clientDialog); break;
                    case ADMIN_ALL_QUESTIONS: answer = dbHandler.getAllQuestions(); break;
                    case ADMIN_NEXT_QUESTION: answer = dbHandler.getNextQuestion(); break;
                    case DELETE_QUESTION: answer = dbHandler.deleteQuestion(parametrs[1]); break;
                    case ANSWER_THE_QUESTION: answer = dbHandler.answerTheQuestion(parametrs); break;
                    case HISTORY_ANSWERS: answer = dbHandler.historyAnswers(parametrs[1]); break;
                    case LIST_VAKANS: answer = dbHandler.getFileInfo(Const.VAKANSII_FILE); break;
                    case NEW_CONTRACT: answer = addNewContract(parametrs, clientDialog); break;
                    case GENERATE_FILE: answer = dbHandler.generateFile(parametrs[1], parametrs[2]); break;
                    case DELETE_USER: answer = dbHandler.deleteUser(parametrs[1]); break;
                    case DELETE_APP: answer = deleteApplication(parametrs, clientDialog); break;
                    case DELETE_CONTRACT: answer = dbHandler.deleteContract(parametrs[1]); break;
                    case VIEW_CONTRACT: answer = dbHandler.viewContract(parametrs); break;
                    case VIEW_FULL_CONTRACT: answer = getAppFromId(parametrs, clientDialog); break;
                    case NEW_PRESS_QUERY: answer = dbHandler.newPressQuery(parametrs[1], parametrs[2]); break;
                    case ABOUT_COMPANY: answer = dbHandler.getFileInfo(Const.ABOUT_FILE); break;
                    case CONTACTS: answer = dbHandler.getFileInfo(Const.CONTACTS_FILE); break;
                    case CONFIRM_ABOUT: answer = dbHandler.setFileInfo(Const.ABOUT_FILE, parametrs[1]); break;
                    case CONFIRM_CONTACTS: answer = dbHandler.setFileInfo(Const.CONTACTS_FILE, parametrs[1]); break;
                    case CONFIRM_VAKANSII: answer = dbHandler.setFileInfo(Const.VAKANSII_FILE, parametrs[1]); break;
                    case NEW_ADMIN: answer = RegistrationUser(true, parametrs, clientDialog); break;
                    case INFO_FRO_PAIRCHART: answer = dbHandler.getInfoForPairChart(); break;
                    case INFO_FROM_BARCHART: answer = dbHandler.getInfoForBarChart(); break;
                    case CALCULATE: answer = Calculate.calculate(Integer.parseInt(parametrs[1]), entry.split(Const.DELIMITER));
                    default:;
                }
                try {
                    if (!dbHandler.dbConnection.isClosed()) dbHandler.dbConnection.close();
                } catch (Exception e) {}
                out.writeUTF(answer);
                out.flush();
                if(MultiThreadServer.stoped) break;
            }
            in.close();
            out.close();
            clientDialog.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String RegistrationUser(boolean admin, String[] parametrs, Socket clientDialog) {
        DatabaseHandler dbHandler = new DatabaseHandler(clientDialog);
        try {
            dbHandler.signUpUser(Boolean.toString(admin), parametrs[3], parametrs[4], parametrs[5], parametrs[6], parametrs[2], parametrs[8], parametrs[7], parametrs[11], parametrs[12]);
        } catch (Exception e){
            return e.toString();
        }
        return "true";
    }

    private static String AddNewVehicleApplication(String[] parametrs, Socket clientDialog) {
        DatabaseHandler dbHandler = new DatabaseHandler(clientDialog);

        String iDuser = parametrs[1];
        String term = parametrs[2];
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String beginWork = dateFormat.format(new Date());
        String fee = parametrs[4];
        String typeMoney = parametrs[5];
        String doljn = parametrs[6];
        String experience = parametrs[7];
        String model = parametrs[8];
        String regNumber = parametrs[9];
        String bodyNumb = parametrs[10];
        String vEngine = parametrs[11];
        String lifting = parametrs[12];
        String seats = parametrs[13];
        String powerEngine = parametrs[14];
        String accidents = parametrs[15];
        String year = parametrs[16];
        String battery = parametrs[17];
        try {
            dbHandler.addNewVehicleApp(iDuser, term, beginWork, fee, typeMoney, doljn, experience, model, regNumber, bodyNumb, vEngine, lifting, seats, powerEngine, accidents, year, battery);
        } catch (Exception e) {
            System.out.println(e);
            return e.toString();
        }
        return "true";
    }

    private static String AddNewPropertyApplication(String[] parametrs, Socket clientDialog) {
        DatabaseHandler dbHandler = new DatabaseHandler(clientDialog);

        String iDuser = parametrs[1];
        String term = parametrs[2];
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String beginWork = dateFormat.format(new Date());
        String fee = parametrs[3];
        String typeMoney = parametrs[4];
        String doljn = parametrs[5];
        String adress = parametrs[6];
        String area = parametrs[7];
        String rooms = parametrs[8];
        String year = parametrs[9];
        String floor = parametrs[10];
        String doors = parametrs[11];
        String steelDoor = parametrs[12];
        String codeLock = parametrs[13];
        String unfireSystem = parametrs[14];
        String security = parametrs[15];
        String unfireMaterial = parametrs[16];
        String highRisk = parametrs[17];
        try {
            dbHandler.addNewPropertyApp(iDuser, term, beginWork, fee, typeMoney, doljn, adress, area, rooms, floor, doors, steelDoor, codeLock, unfireSystem, security, unfireMaterial, highRisk, year);
        } catch (Exception e) {
            System.out.println(e);
            return e.toString();
        }
        return "true";
    }

    private static String AddNewHealthApplication(String[] parametrs, Socket clientDialog) {
        DatabaseHandler dbHandler = new DatabaseHandler(clientDialog);

        String iDuser = parametrs[1];
        String term = parametrs[2];
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String beginWork = dateFormat.format(new Date());
        String fee = parametrs[3];
        String typeMoney = parametrs[4];
        String doljn = parametrs[5];

        String harmHappend = parametrs[6];
        String healthHappend = parametrs[7];
        String invalidHappend = parametrs[8];
        String deathHappend = parametrs[9];
        String covid = parametrs[10];
        String sport = parametrs[11];
        String relax = parametrs[12];
        String invalid12 = parametrs[13];
        try {
            dbHandler.addNewHealthApp(iDuser, term, beginWork, fee, typeMoney, doljn, harmHappend, healthHappend, invalidHappend, deathHappend, covid, sport, relax, invalid12);
        } catch (Exception e) {
            System.out.println(e);
            return e.toString();
        }
        return "true";
    }

    private static String addNewContract(String[] parametrs, Socket clientDialog) {
        DatabaseHandler dbHandler = new DatabaseHandler(clientDialog);

        String id = parametrs[1];
        String idAdmin = parametrs[2];
        String datePay = parametrs[3];
        String summ = parametrs[4];
        String perMonth = parametrs[5];

        try {
            dbHandler.addNewContract(id, idAdmin, datePay, summ, perMonth);
        } catch (Exception e) {
            System.out.println(e);
            return e.toString();
        }
        return "true";
    }

    private static String AuthorisationUser(String[] parametrs, Socket clientDialog) {
        DatabaseHandler dbHandler = new DatabaseHandler(clientDialog);
        String result = dbHandler.getUserInfo(parametrs[1], parametrs[2]);
        if (result == null) return "false";
        //System.out.println(result);
        return result;
    }

    private static String getAppFromId(String[] parametrs, Socket clientDialog) {
        try {
            return new DatabaseHandler(clientDialog).getAppFromId(Integer.parseInt(parametrs[1]), Integer.parseInt(parametrs[2]));
        } catch (Exception e) {
            return e.toString();
        }
    }

    private static String deleteApplication(String[] parametrs, Socket clientDialog) {
        try {
            return new DatabaseHandler(clientDialog).deleteApplication(Integer.parseInt(parametrs[1]));
        } catch (Exception e) {
            System.out.println(e);
            return e.toString();
        }
    }

    private static String getOneApp(Socket clientDialog) {
        try {
            return new DatabaseHandler(clientDialog).getOneApp();
        } catch (Exception e) {
            return "";
        }
    }

}