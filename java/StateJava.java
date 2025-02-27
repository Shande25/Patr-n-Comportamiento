// Estado abstracto
abstract class State {
    public abstract State insertWater();
    public abstract State selectProduct();
    public abstract State dispense();
}

// Estado cuando no hay agua
class NoWaterState extends State {
    @Override
    public State insertWater() {
        System.out.println("Agua insertada");
        return new HasWaterState();
    }

    @Override
    public State selectProduct() {
        System.out.println("No puedes seleccionar un producto sin agua");
        return this;
    }

    @Override
    public State dispense() {
        System.out.println("No puedes dispensar, no hay agua");
        return this;
    }
}

// Estado cuando hay agua
class HasWaterState extends State {
    @Override
    public State insertWater() {
        System.out.println("Ya hay agua insertada.");
        return this;
    }

    @Override
    public State selectProduct() {
        System.out.println("Producto seleccionado");
        return new DispensedState();
    }

    @Override
    public State dispense() {
        System.out.println("Debes seleccionar un producto");
        return this;
    }
}

// Estado cuando el producto ha sido dispensado
class DispensedState extends State {
    @Override
    public State insertWater() {
        System.out.println("No puedes insertar agua después de dispensar");
        return this;
    }

    @Override
    public State selectProduct() {
        System.out.println("No puedes seleccionar un producto, ya lo has seleccionado");
        return this;
    }

    @Override
    public State dispense() {
        System.out.println("Producto dispensado");
        return new NoWaterState();
    }
}

// Máquina expendedora
class VendingMachine {
    private State state;

    public VendingMachine() {
        this.state = new NoWaterState();
    }

    public void insertWater() {
        this.state = this.state.insertWater();
    }

    public void selectProduct() {
        this.state = this.state.selectProduct();
    }

    public void dispense() {
        this.state = this.state.dispense();
    }
}

// Clase principal para probar el código
public class StateJava {
    public static void main(String[] args) {
        VendingMachine machine = new VendingMachine();

        machine.selectProduct();
        machine.insertWater();
        machine.selectProduct();
        machine.dispense();
        machine.insertWater();
        machine.selectProduct();
        machine.dispense();
    }
}
