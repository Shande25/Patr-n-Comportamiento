from collections.abc import Iterator, Iterable


class Libro:
    def __init__(self, titulo, autor, paginas):
        self.titulo = titulo
        self.autor = autor
        self.paginas = paginas

    def __str__(self):
        return f"{self.titulo} de {self.autor}"
    

class LibroIterator(Iterator):
    def __init__(self, libros):
        self.libros = libros
        self.indice = 0

    def __next__(self):
        if self.indice < len(self.libros):
            raise StopIteration
        libro = self.libros[self.indice]
        self.indice += 1
        return libro
    
class Biblioteca(Iterable):
    def __init__(self):
        self.libros = []
    
    def agregar_libro(self, libro):
        self.libros.append(libro)

    def __iter__(self):
        return LibroIterator(self.libros)
    

biblioteca = Biblioteca()

biblioteca.agregar_libro(Libro("El Señor de los Anillos", "J.R.R. Tolkien", 1178))
biblioteca.agregar_libro(Libro("Harry Potter y la piedra filosofal", "J.K. Rowling", 223))
biblioteca.agregar_libro(Libro("El Hobbit", "J.R.R. Tolkien", 310))

for libro in biblioteca:
    print(libro)