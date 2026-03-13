package observer;

public class PhoneDisplay implements Observer{

    private String data = null;

    @Override
    public void update(String data) {
        this.data = data;
        display();
    }

    private void display() {
        System.out.println("The updated data printing from the phone display observer: " + data);
    }
}
