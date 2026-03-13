package adapter;

public class Client {

    public static void clientCode(Printer printer) {
        printer.print();
    }

    public static void main(String[] args) {
        Printer printer = new PrinterV1();
        Printer printerAdapter = new PrinterAdapter();
        clientCode(printerAdapter);
        clientCode(printer);
    }
}
