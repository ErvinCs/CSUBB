import unittest
from unittest import TestCase
from src.repository.RentalList import RentalList
from src.repository.ClientList import ClientList
from src.repository.BookList import BookList
from src.domain.Rental import Rental
from src.domain.Client import Client
from src.domain.Book import Book
from datetime import date
from copy import deepcopy

class TestRentalList(TestCase):
    def test_update(self):
        rl = RentalList()
        rl.addElement(Rental(5, 8, date(2014, 11, 12), date(2016, 11, 17)))
        rl.update(0, date(2020, 1, 1))
        self.assertEqual(rl.getElement(0).getReturnDate(), date(2020, 1, 1))

    def test_mostRentedBooksTimes(self):
        bl = BookList()
        bl.addElement(Book("SomeBook", "desc0", "author0"))
        bl.addElement(Book("SomeOtherBook", "desc1", "author1"))
        cl = ClientList()
        cl.addElement(Client("SomeClient"))
        cl.addElement(Client("SomeOtherClient"))
        rl = RentalList()
        rl.addElement(Rental(1, 1, date(2014, 11, 12), date(2016, 11, 17)))
        rl.addElement(Rental(1, 0, date(1999, 6, 22), date(2012, 12, 2)))
        rl.addElement(Rental(0, 0, date(1997, 11, 12), date(2017, 11, 12)))

        self.assertEqual(rl.mostRentedBooksTimes(bl)[0], bl[1])

    def test_mostRentedBooksDays(self):
        rl = RentalList()
        bl = BookList()
        bl.addElement(Book("SomeBook", "desc0", "author0"))
        bl.addElement(Book("SomeOtherBook", "desc1", "author1"))
        cl = ClientList()
        cl.addElement(Client("SomeClient"))
        cl.addElement(Client("SomeOtherClient"))
        rl.addElement(Rental(1, 1, date(2014, 11, 12), date(2016, 11, 17)))
        rl.addElement(Rental(1, 0, date(1999, 6, 22), date(2012, 12, 2)))
        rl.addElement(Rental(0, 0, date(1997, 11, 12), date(2017, 11, 12)))
        self.assertEqual(rl.mostRentedBooksTimes(bl)[0], bl[1])

    def test_mostActiveClients(self):
        bl = BookList()
        bl.addElement(Book("SomeBook", "desc0", "author0"))
        bl.addElement(Book("SomeOtherBook", "desc1", "author1"))
        cl = ClientList()
        cl.addElement(Client("SomeClient"))
        cl.addElement(Client("SomeOtherClient"))
        rl = RentalList()
        rl.addElement(Rental(1, 1, date(2014, 11, 12), date(2016, 11, 17)))
        rl.addElement(Rental(1, 0, date(1999, 6, 22), date(2012, 12, 2)))
        rl.addElement(Rental(0, 1, date(1997, 11, 12), date(2017, 11, 12)))
        self.assertEqual(rl.mostActiveClients(cl)[0], cl[1])

    def test_lateRentals(self):
        rl = RentalList()
        rl.addElement(Rental(5, 8, date(2014, 11, 12), date(2016, 11, 17)))
        rl.addElement(Rental(5, 9, date(1999, 6, 22), date(2012, 12, 2)))
        rl.addElement(Rental(0, 0, date(1997, 11, 12), date(2017, 11, 12)))
        rl.update(0, date(2020, 1, 1))
        rl.update(1, date(2011, 1, 1))
        rl.update(2, date(2016, 2, 2))
        self.assertEqual(len(rl.lateRentals()), 1)

if __name__ == "__main__":
    testSuite = unittest.TestSuite()
    testSuite.addTest(TestRentalList)
    unittest.main()
