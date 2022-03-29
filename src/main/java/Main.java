import Repositories.DatabaseRepository;

public class Main {
    public static void main(String[] args) {
        DatabaseRepository dr = new DatabaseRepository();

        dr.connectTo();
        dr.disconnectFrom();
    }
}
