from unittest import TestCase
from src.domain.Book import Book

class TestBook(TestCase):
    def test_all(self):
        b1 = Book("book0", "desc0", "author2")
        b2 = Book("book1", "desc3", "author0")
        self.assertNotEqual(b1, b2)
        b1.setAuthor("author0")
        b2.setTitle("book0")
        b1.setDescription("desc3")
        self.assertEqual(b1, b2)
