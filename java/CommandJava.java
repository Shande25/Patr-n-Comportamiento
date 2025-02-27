// Interfaz Command
interface Command {
    void execute();
    void undo();
}

// Clase Radio que tendrá métodos para subir y bajar el volumen
class Radio {
    private int volume = 0;

    // Aumentar el volumen
    public void increaseVolume() {
        volume++;
        System.out.println("Volumen aumentado a: " + volume);
    }

    // Reducir el volumen, pero sin dejarlo por debajo de 0
    public void decreaseVolume() {
        if (volume > 0) {
            volume--;
        }
        System.out.println("Volumen reducido a: " + volume);
    }

    public int getVolume() {
        return volume;
    }
}

// Comando para aumentar el volumen
class VolumeUpCommand implements Command {
    private Radio radio;

    public VolumeUpCommand(Radio radio) {
        this.radio = radio;
    }

    @Override
    public void execute() {
        radio.increaseVolume();
    }

    @Override
    public void undo() {
        radio.decreaseVolume();
    }
}

// Comando para reducir el volumen
class VolumeDownCommand implements Command {
    private Radio radio;

    public VolumeDownCommand(Radio radio) {
        this.radio = radio;
    }

    @Override
    public void execute() {
        radio.decreaseVolume();
    }

    @Override
    public void undo() {
        radio.increaseVolume();
    }
}

// Clase RemoteControl que recibe los comandos
class RemoteControl {
    private Command command;

    public void setCommand(Command command) {
        this.command = command;
    }

    public void pressButton() {
        command.execute();
    }

    public void pressUndo() {
        command.undo();
    }
}

// Clase principal para probar el código
public class CommandJava {
    public static void main(String[] args) {
        // Crear objetos
        Radio radio = new Radio();
        RemoteControl remoteControl = new RemoteControl();

        // Crear comandos
        Command volumeUpCommand = new VolumeUpCommand(radio);
        Command volumeDownCommand = new VolumeDownCommand(radio);

        // Probar subir volumen
        remoteControl.setCommand(volumeUpCommand);
        remoteControl.pressButton();  // Aumentar volumen
        remoteControl.pressUndo();    // Deshacer aumentar volumen

        // Probar bajar volumen
        remoteControl.setCommand(volumeDownCommand);
        remoteControl.pressButton();  // Reducir volumen
        remoteControl.pressUndo();    // Deshacer reducir volumen

        // Intentar reducir el volumen más allá de 0
        remoteControl.setCommand(volumeDownCommand);
        remoteControl.pressButton();  // Reducir volumen (ya está en 0)
        remoteControl.pressUndo();    // Intentar deshacer (aumentar volumen)
    }
}
