from unittest import TestCase
from src.domain.Rental import Rental
from datetime import date

class TestRental(TestCase):
    def test_all(self):
        r1 = Rental(1, 3, date(2008, 11, 5), date(2009, 4, 6))
        self.assertEqual(str(r1), "Rental 1" + ": Client " + str(r1.getClientId()) + ", Book " +
                         str(r1.getBookId()) + ", 2008-11-5 - 2009-4-6, Not returned")
        r1.setReturnDate(date(2008, 11, 7))
        self.assertEqual(r1.getReturnDate(), date(2008, 11, 7))
        self.assertEqual(r1.nrOfDaysRented(), 2)
