import java.util.ArrayList;
import java.util.List;

// Interfaz Observer
interface Observer {
    void update(VendingMachine subject);
}

// Clase VendingMachine (sujeto)
class VendingMachine {
    private String name;
    private boolean inStock;
    private List<Observer> observers;

    public VendingMachine(String name) {
        this.name = name;
        this.inStock = false;
        this.observers = new ArrayList<>();
    }

    public void addObserver(Observer observer) {
        this.observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        this.observers.remove(observer);
    }

    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(this);
        }
    }

    public void setInStock(boolean inStock) {
        this.inStock = inStock;
        notifyObservers();
        System.out.println("La bebida " + this.name + " está en stock: " + this.inStock);
    }

    public String getName() {
        return this.name;
    }

    public boolean isInStock() {
        return this.inStock;
    }
}

// Clase Customer (observador)
class Customer implements Observer {
    private String name;

    public Customer(String name) {
        this.name = name;
    }

    @Override
    public void update(VendingMachine subject) {
        System.out.println("El cliente " + this.name + " ha recibido una notificación sobre la bebida " + subject.getName());
        System.out.println("La bebida " + subject.getName() + " está en stock: " + subject.isInStock());
    }
}

// Clase principal para probar el código
public class ObserverJava {
    public static void main(String[] args) {
        // Crear bebida (máquina expendedora)
        VendingMachine cola = new VendingMachine("Coca Cola");

        // Crear clientes
        Customer customer1 = new Customer("Juan");
        Customer customer2 = new Customer("Ana");

        // Agregar clientes como observadores
        cola.addObserver(customer1);
        cola.addObserver(customer2);

        // Cambiar disponibilidad de la bebida y notificar a los clientes
        cola.setInStock(true);
        cola.setInStock(false);
    }
}
