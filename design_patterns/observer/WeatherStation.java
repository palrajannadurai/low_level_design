package observer;

import java.util.ArrayList;
import java.util.List;

public class WeatherStation implements Subject {
    List<Observer> observers = new ArrayList<>();
    private String data = null;

    @Override
    public void addObserver(Observer observer) {
        if (!observers.contains(observer)) {
            observers.add(observer);
        }
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(this.data);
        }
    }

    public void setData(String data) {
        this.data = data;
        notifyObservers();
    }
}
