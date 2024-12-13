from src.controller.BookController import BookController
from src.repository.BookListFile import BookListFile
from src.repository.BookListPickle import BookListPickle
from src.repository.BookList import BookList
from src.controller.ClientController import ClientController
from src.repository.ClientListFile import ClientListFile
from src.repository.ClientListPickle import ClientListPickle
from src.repository.ClientList import ClientList
from src.controller.RentalController import RentalController
from src.repository.RentalListFile import RentalListFile
from src.repository.RentalListPickle import RentalListPickle
from src.repository.RentalList import RentalList
from src.ui.ConsoleMenu import ConsoleMenu

def loadSettings():
    f = open("settings.properties", "r")
    lines = f.readlines()
    settings = []
    for line in lines:
        pos = line.find("=")
        line = str(line[pos+1:]).strip()
        settings.append(line)
    print(settings)
    f.close()
    return settings

def runSettings(settings):
    if settings[0] == 'inMemory':
        bookRepo = BookList()
        clientRepo = ClientList()
        rentalRepo = RentalList()
        bookCon = BookController(bookRepo)
        clientCon = ClientController(clientRepo)
        rentalCon = RentalController(rentalRepo)
        bookCon.setUpBooks()
        clientCon.setUpClients()
        rentalCon.setUpRentals()
        userInterface = ConsoleMenu(bookCon, clientCon, rentalCon)
        userInterface.executeMenu()
    elif settings[0] == "textFiles":
        bookRepo = BookListFile(settings[1])
        clientRepo = ClientListFile(settings[2])
        rentalRepo = RentalListFile(settings[3])
        bookCon = BookController(bookRepo)
        clientCon = ClientController(clientRepo)
        rentalCon = RentalController(rentalRepo)
        userInterface = ConsoleMenu(bookCon, clientCon, rentalCon)
        userInterface.executeMenu()
    elif settings[0] == "binaryFiles":
        bookRepo = BookListFile(settings[1])
        clientRepo = ClientListFile(settings[2])
        rentalRepo = RentalListFile(settings[3])
        bookCon = BookController(bookRepo)
        clientCon = ClientController(clientRepo)
        rentalCon = RentalController(rentalRepo)
        userInterface = ConsoleMenu(bookCon, clientCon, rentalCon)
        userInterface.executeMenu()
    else:
        try:
            raise Exception("Invalid file format!")
        except Exception as ex:
            print(ex)

if __name__ == '__main__':
    runSettings(loadSettings())
