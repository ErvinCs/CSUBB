from src.repository.MyList import MyList
from src.repository.MyList import RepositoryException
from src.domain.Rental import Rental
from src.domain import IterableModule
from copy import deepcopy
import unittest

class RentalList(MyList):
    def __init__(self):
        MyList.__init__(self)

    def update(self, elemIndex, returnDate):
        '''
        :param elemIndex: index of the element to update
        :param returnDate: date object
        :return: sets the specified objects returnDate to the given one
        Raises a RepositoryException if the index is invalid (<0 or >=the length of the RentalList)
        '''
        if elemIndex < 0 or elemIndex >= len(self._repo):
            raise RepositoryException("Invalid item position")
        self._repo[elemIndex].setReturnDate(returnDate)

    def updateById(self, elemId, returnDate):
        '''
        :param elemId: id of the element to update
        :param returnDate: date object
        :return: sets the specified objects returnDate to the given one
        Raises a RepositoryException if the item can not be found
        '''
        catch = True
        index = 0
        while index < len(self._repo):
            if self._repo[index].getId() == elemId:
                self._repo[index].setReturnDate(returnDate)
                catch = False
            index += 1
        self._repo.resetIndex()
        if catch is True:
            raise RepositoryException("UpdateById: Item not found")

    def mostRentedBooksTimes(self, bookList):
        '''
        :param bookList:
        :return: bookList in descending order by the number of times each book was rented
        '''
        timesRented = [0]*len(bookList)
        for r in self._repo:
            timesRented[r.getBookId()] += 1
        print(timesRented)
        return IterableModule.gnomeSortByList(bookList, timesRented)

    def mostRentedBooksDays(self, bookList):
        '''
        :param bookList:
        :return: bookList in descending order by the number of days each book was rented
        '''
        daysRented = [0] * len(bookList)
        for r in self.getAll():
            daysRented[r.getBookId()] += r.nrOfDaysRented()
        print(daysRented)
        return IterableModule.gnomeSortByList(bookList, daysRented)


    def mostActiveClients(self, clientList):
        '''
        :param clientList:
        :return: clientList in descending order of the number of book rental days
        '''
        daysRented = [0] * len(clientList)
        for r in self.getAll():
            daysRented[r.getClientId()] += r.nrOfDaysRented()
        print(daysRented)
        return IterableModule.gnomeSortByList(clientList, daysRented)

    def mostRentedAuthor(self, bookList):
        '''
        :param bookList:
        :return: the list of authors in descending order
                 of the total number of their book rentals
        '''
        authorList = bookList.getAuthorList()
        authorsRented = {author: 0 for author in authorList}
        for r in self._repo:
            authorsRented[bookList.getBookById(r.getBookId()).getAuthor()] += 1
        print(authorsRented)
        return IterableModule.gnomeSortByList(bookList, list(authorsRented.values()))

    def filterLateRentals(self):
        '''
        :return: all the books that are currently rented
                 for which the due date for return has passed,
                 sorted in descending order of the number of days of delay
        '''
        list = deepcopy(self._repo)
        list = IterableModule.filter(list, Rental.lateRental)
        delayList = []
        i = 0
        while i < len(list):
            d = list[i].lateRental()
            if d:
                delayList.append(d)
            i += 1
        return IterableModule.gnomeSortByList(list, delayList)

