from unittest import TestCase
from src.domain.Client import Client

class TestClient(TestCase):
    def setUp(self):
        c1 = Client("SomeName")
        c1.setName("Name")
        self.assertEqual(c1.getName(), "Name")


