from abc import ABC, abstractmethod


class ChatMediator(ABC):
    @abstractmethod
    def send(self, message, sender):
        pass


class ChatRoom(ChatMediator):
    def __init__(self):
        self.users = []

    def add_user(self, user):
        self.users.append(user)

    def send(self, message, sender):
        print(f"{sender.name} sends: {message}")
        for user in self.users:
            if user != sender:
                user.receive(message)


class User:
    def __init__(self, name, chat_room):
        self.name = name
        self.chat_room = chat_room
        chat_room.add_user(self)

    def send(self, message):
        self.chat_room.send(message, self)

    def receive(self, message):
        print(f"{self.name} received: {message}")


chat_room = ChatRoom()

user1 = User("Alice", chat_room)
user2 = User("Bob", chat_room)
user3 = User("Charlie", chat_room)

user1.send("Hola!")
user2.send("Cómo están?")
user3.send("Todo bien, gracias.")
