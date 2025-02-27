import java.util.ArrayList;
import java.util.List;

// Interfaz Mediador
interface NotificationMediator {
    void send(String message, Scientist sender);
}

// Clase Mediador
class ResearchGroup implements NotificationMediator {
    private List<Scientist> scientists;

    public ResearchGroup() {
        this.scientists = new ArrayList<>();
    }

    public void addScientist(Scientist scientist) {
        scientists.add(scientist);
    }

    @Override
    public void send(String message, Scientist sender) {
        System.out.println(sender.getName() + " sends: " + message);
        for (Scientist scientist : scientists) {
            if (scientist != sender) {
                scientist.receive(message);
            }
        }
    }
}

// Clase Científico
class Scientist {
    private String name;
    private ResearchGroup researchGroup;

    public Scientist(String name, ResearchGroup researchGroup) {
        this.name = name;
        this.researchGroup = researchGroup;
        researchGroup.addScientist(this);
    }

    public String getName() {
        return name;
    }

    public void send(String message) {
        researchGroup.send(message, this);
    }

    public void receive(String message) {
        System.out.println(this.name + " received: " + message);
    }
}

// Clase principal
public class MediatorJava {
    public static void main(String[] args) {
        // Crear grupo de científicos
        ResearchGroup researchGroup = new ResearchGroup();

        // Crear científicos
        Scientist scientist1 = new Scientist("Dr. Smith", researchGroup);
        Scientist scientist2 = new Scientist("Dr. Johnson", researchGroup);
        Scientist scientist3 = new Scientist("Dr. Lee", researchGroup);

        // Enviar mensajes
        scientist1.send("La investigación está avanzando bien.");
        scientist2.send("¿Han observado los resultados de la última prueba?");
        scientist3.send("Sí, tenemos algunos datos interesantes.");
    }
}
