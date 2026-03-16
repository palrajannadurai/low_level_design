package adapter;

public class Client {
    private static void clientCode(Printer printer) {
        printer.print();
    }

    public static void main(String[] args) {
        Printer printer = new LegacyPrinterAdapter();
        Printer printerV1 = new PrinterV1();
        clientCode(printerV1);
    }
}
