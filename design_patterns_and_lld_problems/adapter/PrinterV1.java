package adapter;

public class PrinterV1 implements Printer {
    @Override
    public void print() {
        System.out.println("Document printed using printer v1.");
    }
}
