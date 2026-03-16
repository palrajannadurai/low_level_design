package observer;

public class TVDisplay implements Observer {

    private String data = null;

    @Override
    public void update(String data) {
        this.data = data;
        display();
    }

    private void display() {
        System.out.println("The updated data printing from the tv display observer: " + data);
    }
}
