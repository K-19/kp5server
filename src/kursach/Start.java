package kursach;

public class Start {
    public static void main(String[] args) {
        if(args.length == 1) {
            try {
                Const.PORT = Integer.parseInt(args[0]);
            } catch (Exception e) {
                System.out.println(e);
                System.out.println("Неверный порт!!!");
                return;
            }
        }
        new Thread(new Runnable() {
            public void run() {
                Main.main(new String[1]);
            }
        }).start();
    }
}
