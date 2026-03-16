package adapter;

public class LegacyPrinterAdapter implements Printer{
    private LegacyPrinter legacyPrinter;

    public LegacyPrinterAdapter() {
        this.legacyPrinter = new LegacyPrinter();
    }

    @Override
    public void print() {
        this.legacyPrinter.printDocument();
    }
}
