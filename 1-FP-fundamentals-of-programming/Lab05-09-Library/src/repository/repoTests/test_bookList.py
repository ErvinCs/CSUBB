import unittest
from unittest import TestCase
from src.repository.BookList import BookList
from src.domain.Book import Book


class TestBookList(TestCase):
    def test_getBookById(self):
        bl1 = BookList()
        a = Book("SomeBook", "desc0", "author0")
        bl1.addElement(a)
        b = Book("SomeOtherBook", "desc1", "author1")
        bl1.addElement(b)
        self.assertEqual(bl1.getBookById(3), b)

    def test_getAuthorList(self):
        bl = BookList()
        b1 = Book("SomeBook", "desc0", "author0")
        b2 = Book("SomeOtherBook", "desc1", "author1")
        bl.addElement(b1)
        bl.addElement(b2)
        self.assertEqual(bl.getAuthorList().getAll(), ["author0", "author1"])

    def test_update(self):
        bl1 = BookList()
        bl1.addElement(Book("SomeBook", "desc0", "author0"))
        bl1.addElement(Book("SomeOtherBook", "desc1", "author1"))
        bl1.update(0,"a", "a", "a")
        self.assertEqual(bl1.getElement(0).getTitle(), "a")
        self.assertEqual(bl1.getElement(0).getDescription(), "a")
        self.assertEqual(bl1.getElement(0).getAuthor(), "a")

    def test_searchBook(self):
        bl1 = BookList()
        bl1.addElement(Book("SomeBook", "desc0", "author0"))
        bl1.addElement(Book("SomeOtherBook", "desc1", "author1"))
        bl1.addElement(Book("NotABook", "d", "a"))
        bl2 = BookList()
        bl2.addElement(Book("SomeBook", "desc0", "author0"))
        bl2.addElement(Book("SomeOtherBook", "desc1", "author1"))
        self.assertEqual((bl2.getElement(0).getTitle(), bl2.getElement(0).getAuthor(), bl2.getElement(0).getDescription(),
                          bl2.getElement(1).getTitle(), bl2.getElement(0).getAuthor(), bl2.getElement(0).getDescription()),
                         (bl1.getElement(0).getTitle(), bl1.getElement(0).getAuthor(), bl1.getElement(0).getDescription(),
                          bl1.getElement(1).getTitle(), bl1.getElement(0).getAuthor(), bl1.getElement(0).getDescription()))

if __name__ == "__main__":
    testSuite = unittest.TestSuite()
    testSuite.addTest(TestBookList)
    unittest.main()
