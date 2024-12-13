from src.controller.MyController import MyController
from src.domain.Rental import Rental
from datetime import date
from random import randint

class RentalController(MyController):
    def __init__(self, repo):
        '''
        Creates a repo and an empty undo list.
        '''
        MyController.__init__(self, repo)

    def readDate(self):
        year = input("Year: ").strip()
        if year == "now":
            return date.today()
        else:
            year = int(year)
        month = int(input("Month: ").strip())
        day = int(input("Day: ").strip())
        return date(year, month, day)

    def readRental(self, bookList, clientList):
        bookId = int(input("BookId: ").strip())
        if bookId not in bookList:
            raise Exception("Book does not exist!")
        clientId = int(input("ClientId: ").strip())
        if clientId not in clientList:
            raise Exception("Client does not exist!")
        print("RentDate: ")
        rentDate = self.readDate()
        print("DueDate: ")
        dueDate = self.readDate()
        if dueDate < rentDate:
            raise Exception("DueDate cannot precede RentDate!")
        rental = Rental(bookId, clientId, rentDate, dueDate)
        self._repo.addElement(rental)
        return rental

    def updateRental(self, elemIndex, returnDate):
        self._repo.update(elemIndex, returnDate)

    def updateById(self, elemId, name):
        self._repo.updateById(elemId, name)

    def printMostRentedBooks(self, bookList):
        cmd = input("Sort by number of times(T) or number of days(D)?").strip()
        while cmd != 'T' and cmd != 'D':
            cmd = input("Sort by number of times(T) or number of days(D)?").strip()
        if cmd == 'T':
            #print(str(self._repo.mostRentedBooksTimes(bookList)))
            list = self._repo.mostRentedBooksTimes(bookList)
            i = 0
            while i < len(list):
                print(str(list[i]))
                i+=1
        else:
            #print(str(self._repo.mostRentedBooksDays(bookList)))
            list = self._repo.mostRentedBooksDays(bookList)
            i = 0
            while i < len(list):
                print(str(list[i]))
                i += 1


    def printMostActiveClients(self, clientList):
        #print(str(self._repo.mostActiveClients(clientList)))
        list = self._repo.mostActiveClients(clientList)
        i = 0
        while i < len(list):
            print(str(list[i]))
            i += 1

    def printMostRentedAuthor(self, bookList):
        list = self._repo.mostRentedAuthor(bookList)
        #print(str(self._repo.mostRentedAuthor(bookList)))
        i = 0
        while i < len(list):
            print(str(list[i]))
            i += 1

    def printLateRentals(self):
        #print(str(self._repo.lateRentals()))
        #list = self._repo.lateRentals()
        list = self._repo.filterLateRentals()
        i = 0
        while i < len(list):
            print(str(list[i]))
            i += 1

    def setUpRentals(self):
        self._repo.addElement(Rental(5, 8, date(2014, 11, 12), date(2016, 11, 17)))
        self._repo.addElement(Rental(5, 9, date(1999, 6, 22), date(2012, 12, 2)))
        self._repo.addElement(Rental(1, 6, date(2016, 10, 7), date(2018, 12, 12)))
        self._repo.addElement(Rental(1, 6, date(1998, 10, 7), date(2000, 12, 12)))
        self._repo.addElement(Rental(0, 0, date(1997, 11, 12), date(2017, 11, 12)))
        y = lambda x,y: randint(x,y)
        m = lambda x,y: randint(x,y)
        d = lambda x,y: randint(x,y)
        for i in range(6, 16):
            startDate = date(y(1,1900),m(1,11),d(1,27))
            self._repo.addElement(Rental(randint(0, 8),randint(0, 8),
                                         startDate, date(y(startDate.year+1, startDate.year+10),m(startDate.month, startDate.month+1),d(startDate.day, startDate.day+1))))