from abc import ABC, abstractmethod

class Command(ABC):
    @abstractmethod
    def execute(self):
        pass

    @abstractmethod
    def undo(self):
        pass

class Light:
    def on(self):
        print("Luz encendida")  

    def off(self):
        print("Luz apagada")

class LightOnCommand(Command):
    def __init__(self, light):
        self.light = light

    def execute(self):
        self.light.on()

    def undo(self):
        self.light.off()

class LightOffCommand(Command):
    def __init__(self, light):
        self.light = light

    def execute(self):
        self.light.off()

    def undo(self):
        self.light.on()

class RemoteControl:
    def __init__(self):
        self.commands = []

    def add_command(self, command):
        print(f"Comando añadido: {command.__class__.__name__}")
        self.commands.append(command)

    def execute(self):
        print("Ejecutando Comandos ...")
        for command in self.commands:
            command.execute()

    def undo_last_command(self):
        if self.commands:
            print("Deshaciendo último comando ...")
            self.commands.pop().undo()
        else:
            print("No hay comandos para deshacer.")

    def undo_commands(self):
        print("Deshaciendo todos los comandos ...")
        while self.commands:
            self.commands.pop().undo()

# Creación de objetos
light = Light()
remote = RemoteControl()

lightOnCommand = LightOnCommand(light)
lightOffCommand = LightOffCommand(light)

remote.add_command(lightOnCommand)
remote.add_command(lightOffCommand)

remote.execute()
remote.undo_last_command()
remote.undo_commands()
