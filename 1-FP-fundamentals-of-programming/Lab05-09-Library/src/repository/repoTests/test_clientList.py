import unittest
from unittest import TestCase
from src.repository.ClientList import ClientList
from src.domain.Client import Client

class TestClientList(TestCase):
    def test_update(self):
        cl1 = ClientList()
        cl1.addElement(Client("SomeClient"))
        cl1.addElement(Client("SomeOtherClient"))
        cl1.update(1, "NewName")
        self.assertEqual(cl1.getElement(1).getName(), "NewName")

    def test_searchClient(self):
        cl1 = ClientList()
        cl1.addElement(Client("SomeClient"))
        cl1.addElement(Client("SomeOtherClient"))
        cl1.addElement(Client("Client"))
        cl2 = ClientList()
        cl2.addElement(Client("SomeClient"))
        cl2.addElement(Client("SomeOtherClient"))
        self.assertEqual((cl2.getElement(0).getName(), cl2.getElement(1).getName()),
                         (cl1.getElement(0).getName(), cl1.getElement(1).getName()))

if __name__ == "__main__":
    testSuite = unittest.TestSuite()
    testSuite.addTest(TestClientList)
    unittest.main()
