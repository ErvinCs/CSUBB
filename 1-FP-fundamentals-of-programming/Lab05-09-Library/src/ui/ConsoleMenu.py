from src.controller.BookController import BookController
from src.controller.ClientController import ClientController
from src.controller.RentalController import RentalController
from src.controller.MyController import MyController
from datetime import date
from copy import deepcopy

class ConsoleMenu:
    def __init__(self, bookCon, clientCon, rentalCon):
        self._bookCon = bookCon
        self._clientCon = clientCon
        self._rentalCon = rentalCon
        self.undo = []
        self.redo = []
        self.undoed = []
        self.redoed = []

    @staticmethod
    def listMenu():
        string = '\nCommands:\n'
        string += '\t -1 - Help\n'
        string += '\t 0 - Exit\n'
        string += '\t 1 - Add book\n'
        string += '\t 2 - Add client\n'
        string += '\t 3 - Remove book\n'
        string += '\t 4 - Remove client\n'
        string += '\t 5 - Remove rental\n'
        string += '\t 6 - Remove all books\n'
        string += '\t 7 - Remove all clients\n'
        string += '\t 8 - List all books\n'
        string += '\t 9 - List all clients\n'
        string += '\t 10 - Update book\n'
        string += '\t 11 - Update client\n'
        string += '\t 12 - Rent a book\n'
        string += '\t 13 - Return a book\n'
        string += '\t 14 - List rental history\n'
        string += '\t 15 - Search for books\n'
        string += '\t 16 - Search for clients\n'
        string += '\t 17 - Print most rented books\n'
        string += '\t 18 - Print most active clients\n'
        string += '\t 19 - Print most rented author\n'
        string += '\t 20 - Print late rentals\n'
        string += '\t 21 - Undo\n'
        string += '\t 22 - Redo\n'
        print(string)

    def redoCommand(self, command):
        if command == '1':
            id, val = self.redoed.pop()
            self._bookCon.insertElementById(val, id)
            self.undoed.append((id,))
        elif command == '2':
            id, val = self.redoed.pop()
            self._clientCon.insertElementById(val, id)
            self.undoed.append((id,))
        elif command == '3':
            id, = self.redoed.pop()
            self.undoed.append((id, self._bookCon.removeElementById(id)))
        elif command == '4':
            id, = self.redoed.pop()
            self.undoed.append((id, self._clientCon.removeElementById(id)))
        elif command == '5':
            id, = self.redoed.pop()
            self.undoed.append((id, self._rentalCon.removeElementById(id)))
        elif command == '6':
            for b in self._bookCon.getRepo():
                self.undoed.append((deepcopy(b),))
            self.undoed.append((len(self._bookCon.getRepo()),))
            self._bookCon.removeAll()
        elif command == '7':
            for c in self._clientCon.getRepo():
                self.undoed.append((deepcopy(c),))
            self.undoed.append((len(self._clientCon.getRepo()),))
            self._clientCon.removeAll()
        elif command == '10':
            id, val = self.redoed.pop()
            self.undoed.append((id, self._bookCon.getElementById(id)))
            self._bookCon.setElementById(id, val)
        elif command == '11':
            id, val = self.redoed.pop()
            self.undoed.append((id, self._clientCon.getElementById(id)))
            self._clientCon.setElementById(id, val)
        elif command == '12':
            id, val = self.redoed.pop()
            self._rentalCon.insertElement(id, val)
            self.undoed.append((id,))
        elif command == '13':
            id, val = self.redoed.pop()
            self._rentalCon.getRepo().getElementById(id).setReturnDate(val)
            self.undoed.append((id, val))

    def undoCommand(self, command):
        if command == '1':
            id, = self.undoed.pop()
            self.redoed.append((id, self._bookCon.removeElementById(id)))
        elif command == '2':
            id, = self.undoed.pop()
            self.redoed.append((id, self._clientCon.removeElementById(id)))
        elif command == '3':
            id, val = self.undoed.pop()
            self._bookCon.insertElementById(val, id)
            self.redoed.append((id,))
        elif command == '4':
            id, val = self.undoed.pop()
            self._clientCon.insertElementById(val, id)
            self.redoed.append((id,))
        elif command == '5':
            id, val = self.undoed.pop()
            self._rentalCon.insertElementById(val, id)
            self.redoed.append((id,))
        elif command == '6':
            length, = self.undoed.pop()
            i = 0
            while i < length:
                book, = self.undoed.pop()
                self._bookCon.insertElement(0, book)
                i += 1
        elif command == '7':
            length, = self.undoed.pop()
            i = 0
            while i < length:
                client, = self.undoed.pop()
                self._clientCon.insertElement(0, client)
                i += 1
        elif command == '10':
            id, val = self.undoed.pop()
            self.redoed.append((id, self._bookCon.getElementById(id)))
            self._bookCon.setElementById(id, val)
        elif command == '11':
            id, val = self.undoed.pop()
            self.redoed.append((id, self._clientCon.getElementById(id)))
            self._clientCon.setElementById(id, val)
        elif command == '12':
            id, = self.undoed.pop()
            self.redoed.append((id, self._rentalCon.removeElementById(id)))
        elif command == '13':
            id, val = self.undoed.pop()
            self._rentalCon.getRepo().getElementById(id).setReturnDate(None)
            self.redoed.append((id, val))

    def executeMenu(self):
        ConsoleMenu.listMenu()
        while True:
            try:
                id = None
                command = input("Enter command: ").strip()
                if command == '0':
                    input("Goodbye!")
                    break
                elif command == "-1":
                    ConsoleMenu.listMenu()
                elif command == '1':
                    book = self._bookCon.readBook()
                    self.undo.append('1')
                    self.undoed.append((book.getId(),))
                elif command == '2':
                    client = self._clientCon.readClient()
                    self.undo.append('2')
                    self.undoed.append((client.getId(),))
                elif command == '3':
                    id = int(input("Enter id: "))
                    self.undo.append('3')
                    #keep the removed element and its index
                    self.undoed.append((id, self._bookCon.getRepo().getElementById(id)))
                    self._bookCon.removeElementById(id)
                elif command == '4':
                    id = int(input("Enter id: "))
                    self.undo.append('4')
                    #keep the removed element and its index
                    self.undoed.append((id, self._clientCon.getRepo().getElementById(id)))
                    self._clientCon.removeElementById(id)
                elif command == '5':
                    id = int(input("Enter id: "))
                    self.undo.append('5')
                    #keep the removed element and its index
                    self.undoed.append((id, self._rentalCon.getRepo().getElementById(id)))
                    self._rentalCon.removeElementById(id)
                elif command == '6':
                    self.undo.append('6')
                    for b in self._bookCon.getRepo():
                        self.undoed.append((deepcopy(b),))
                    self.undoed.append((len(self._bookCon.getRepo()),))
                    self._bookCon.removeAll()
                elif command == '7':
                    self.undo.append('7')
                    for c in self._clientCon.getRepo():
                        self.undoed.append((deepcopy(c),))
                    self.undoed.append((len(self._clientCon.getRepo()),))
                    self._clientCon.removeAll()
                elif command == '8':
                    self._bookCon.listRepo()
                elif command == '9':
                    self._clientCon.listRepo()
                elif command == '10':
                    id = int(input("Enter id: "))
                    self.undo.append('10')
                    #keep the old book in a list
                    self.undoed.append((id, deepcopy(self._bookCon.getRepo().getElementById(id))))
                    self._bookCon.updateById(id)
                elif command == '11':
                    id = int(input("Enter id: "))
                    self.undo.append('11')
                    #keep the old client in a list
                    self.undoed.append((id, deepcopy(self._clientCon.getRepo().getElementById(id))))
                    self._clientCon.updateById(id)
                elif command == '12':
                    rental = self._rentalCon.readRental(self._bookCon.getRepo(), self._clientCon.getRepo())
                    self.undo.append('12')
                    self.undoed.append((rental.getId(),))
                elif command == '13':
                    id = int(input("Enter id: "))
                    if self._rentalCon.getElement(id) is None:
                        print("Enter return date: ")
                        date = self._rentalCon.readDate()
                        self._rentalCon.updateById(id, date)
                        self.undo.append('13')
                        self.undoed.append((id, date))
                    else:
                        raise Exception("Book already returned!")
                elif command == '14':
                    self._rentalCon.listRepo()
                elif command == '15':
                    self._bookCon.searchBook()
                elif command == '16':
                    self._clientCon.searchClient()
                elif command == '17':
                    self._rentalCon.printMostRentedBooks(self._bookCon.getRepo())
                elif command == '18':
                    self._rentalCon.printMostActiveClients(self._clientCon.getRepo())
                elif command == '19':
                    self._rentalCon.printMostRentedAuthor(self._bookCon.getRepo())
                elif command == '20':
                    self._rentalCon.printLateRentals()
                elif command == '21':
                    if len(self.undo) == 0:
                        raise Exception("Nothing to undo!")
                    c = self.undo[len(self.undo)-1]
                    self.undoCommand(c)
                    self.redo.append(c)
                    self.undo.pop()
                elif command == '22':
                    if len(self.redo) == 0:
                        raise Exception("Nothing to redo!")
                    c = self.redo[len(self.redo)-1]
                    self.redoCommand(c)
                    self.undo.append(c)
                    self.redo.pop()
            except Exception as e:
                print("Error: " + str(e))

