from abc import ABC, abstractmethod

class Observer(ABC):
    @abstractmethod
    def update(self, subject):
        pass


class Product:
    def __init__(self, name):  # Use __init__ here
        self.name = name
        self.in_stock = False
        self.observers = []  # Changed "observer" to "observers" for clarity


    def add_observer(self, observer):
        self.observers.append(observer)

    def remove_observer(self, observer):
        self.observers.remove(observer)

    def notify_observers(self):
        for observer in self.observers:
            observer.update(self)


    def set_in_stock(self, in_stock):
        self.in_stock = in_stock
        self.notify_observers()
        print(f"El producto {self.name} esta en stock: {self.in_stock}")


class Customer(Observer):
    def __init__(self, name):  # Use __init__ here
        self.name = name

    def update(self, subject):
        print(f"El cliente {self.name} ha recibido una notificacion sobre el producto {subject.name}")
        print(f"El producto {subject.name} esta en stock: {subject.in_stock}")


# Instantiate Product and Customers
laptop = Product("Laptop")
customer1 = Customer("Juan")
customer2 = Customer("Jane")

# Add customers as observers
laptop.add_observer(customer1)
laptop.add_observer(customer2)

# Change product stock and notify customers
laptop.set_in_stock(True)
laptop.set_in_stock(False)
