import unittest
from unittest import TestCase
from src.repository.MyList import MyList

class TestMyList(TestCase):
    def test_getAll(self):
        list = MyList()
        list.addElement('a')
        list.addElement(1)
        self.assertEqual(list.getAll(), ['a', 1])

    def test_getElement(self):
        list = MyList()
        list.addElement('a')
        list.addElement(1)
        self.assertEqual(list.getElement(0), 'a')

    def test_getTop(self):
        list = MyList()
        list.addElement('a')
        list.addElement(1)
        self.assertEqual(list.getTop(), 1)

    def test_setElement(self):
        list = MyList()
        list.addElement('a')
        list.addElement(1)
        list.setElement(0, 11)
        self.assertEqual(list[0], 11)

    def test_setRepo(self):
        list = MyList()
        list.addElement('a')
        list.addElement(1)
        list.setRepo(['newa', 111])
        self.assertEqual(list.getAll(), ['newa', 111])

    def test_addElement(self):
        list = MyList()
        list.addElement('a')
        list.addElement(1)
        self.assertEqual(len(list), 2)
        self.assertEqual(list[0], 'a')

    def test_insertElement(self):
        list = MyList()
        list.addElement('a')
        list.addElement(1)
        list.insertElement('elem', 1)
        self.assertEqual(list[1], 'elem')

    def test_removeElement(self):
        list = MyList()
        list.addElement('a')
        list.addElement(1)
        list.removeElement(0)
        self.assertEqual(len(list), 1)

    def test_removeAll(self):
        list = MyList()
        list.addElement('a')
        list.addElement(1)
        self.assertEqual(list.removeAll(), None)

    def test_swapElements(self):
        list = MyList()
        list.addElement('a')
        list.addElement(1)
        list.swapElements(0, 1)
        self.assertEqual(list[0], 1)
        self.assertEqual(list[1], 'a')

    def test_partialStrMatch(self):
        self.assertTrue(MyList.partialStrMatch("BookList", "Book"))
        self.assertFalse(MyList.partialStrMatch("BookList", "Books"))

if __name__ == "__main__":
    testSuite = unittest.TestSuite()
    testSuite.addTest(TestMyList)
    unittest.main()
