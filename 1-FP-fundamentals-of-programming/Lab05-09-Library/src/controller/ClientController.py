from src.controller.MyController import MyController
from src.domain.Client import Client
from random import randint

class ClientController(MyController):
    def __init__(self, repo): #, flag:
        '''
        Creates a repo and an empty undo list.
        '''
        MyController.__init__(self, repo) #, flag)

    def readClient(self):
        name = input("Name: ").strip()
        client = Client(name)
        self._repo.addElement(client)
        return client

    def updateClient(self, elemIndex):
        name = input("Name: ").strip()
        self._repo.update(elemIndex, name)

    def updateById(self, elemId):
        name = input("Name: ").strip()
        self._repo.updateById(elemId, name)

    def searchClient(self, id=None, name=None):
        id = input("Id: ").strip().lower()
        if id == "":
            id = None
        else:
            id = int(id)
        name = input("Name: ").strip().lower()
        if name == "":
            name = None
        print(str(self._repo.searchClient(id, name)))
        #self._repo.writeFile()

    def setUpClients(self):
        self._repo.addElement(Client("name0"))
        self._repo.addElement(Client("name1"))
        self._repo.addElement(Client("name12"))
        self._repo.addElement(Client("name2"))
        self._repo.addElement(Client("name2"))
        self._repo.addElement(Client("name5"))
        self._repo.addElement(Client("name11"))
        self._repo.addElement(Client("name3"))
        self._repo.addElement(Client("name8"))
        self._repo.addElement(Client("name9"))
        for i in range(10, 40):
            self._repo.addElement(Client("name" + str(i)))