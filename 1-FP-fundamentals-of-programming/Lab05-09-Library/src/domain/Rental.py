from datetime import date
import unittest

class Rental:
    rentalIterator = 0  #rentalId generator
    def __init__(self, bookId, clientId, rentDate, dueDate, returnDate=None):
        '''
        Sets rentalId using the rentalIterator.
        :param bookId: integer Id field of a book item
        :param clientId: integer Id field of a client item
        :param rentDate, dueDate: date objects
        Sets returnDate to None.
        '''
        self.__rentalId = Rental.rentalIterator
        Rental.rentalIterator += 1
        self.__bookId = bookId
        self.__clientId = clientId
        self.__rentDate = rentDate
        self.__dueDate = dueDate
        self.__returnDate = None
        self.__delay = None
    #Create a constructor that sets rentDate as the currentDate: 'datetime.datetime.now'

    def getId(self):
        return self.__rentalId

    def getBookId(self):
        return self.__bookId

    def getClientId(self):
        return self.__clientId

    def getRentDate(self):
        return self.__rentDate

    def getDueDate(self):
        return self.__dueDate

    def getReturnDate(self):
        return self.__returnDate

    def getDelay(self):
        return self.__delay

    def setDelay(self, delay):
        self.__delay = delay

    def setReturnDate(self, returnDate):
        self.__returnDate = returnDate

    def nrOfDaysRented(self):
        '''
        :return: returnDate - rentDate
        '''
        ren = self.__rentDate
        ret = self.__returnDate
        if ret is None:
            ret = date.today()
        return (ret - ren).days

    def lateRental(self):
        '''
        :return: the number of days of delay or False if returned on time
        '''
        ret = self.getReturnDate()
        due = self.getDueDate()
        if ret is None:
            ret = date.today()
        if ret > due:
            self.setDelay(ret - due)
            return ret - due
        else:
            return False

    def __str__(self):
        '''
        :return: a String: 'Rental[ID]: Client[ID], Book[ID], [rentDate] - [dueDate], Returned/Not Returned'
        '''
        aString = 'Rental ' + str(self.__rentalId) + ': '
        aString += 'Client ' + str(self.__clientId) + ', '
        aString += 'Book ' + str(self.__bookId) + ', '
        aString += str(self.__rentDate) + ' to '
        aString += str(self.__dueDate) + ', '
        if self.__returnDate == None:
            aString += 'Not returned'
        else:
            aString += 'Returned ' + str(self.__returnDate)
        return aString

    def __eq__(self, rental):
        '''
        :param rental: to compare to
        :return: true if the 2 rentals are identical (except for the rentalId); False otherwise
        '''
        return (self.__bookId == rental.__bookId and self.__clientId == rental.__clientId and
                self.__rentDate == rental.__rentDate and self.__dueDate == rental.__dueDate
                and self.__returnDate == rental.__returnDate)


