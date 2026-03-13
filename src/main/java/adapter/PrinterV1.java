package adapter;

public class PrinterV1 implements Printer {
    @Override
    public void print() {
        System.out.println("Printer V1 is printing the document.");
    }
}
