from src.repository.ClientList import ClientList
from src.domain.Client import Client
class ClientListFile(ClientList):
    def __init__(self, fileName="ClientList.txt"):
        '''
        :param fileName: file to read/write
        Stores data in string format
        '''
        ClientList.__init__(self)
        self.__fileName = fileName
        self.readFile()

    def writeFile(self):
        '''
        Saves the repository into a text file
        '''
        f = open(self.__fileName, "w")
        fileRepo = self._repo
        for b in fileRepo:
            f.write(str(b) + "\n")
        f.close()

    def readFile(self):
        '''
        Reads clients from a text file
        '''
        f = open(self.__fileName, "r")
        lines = f.readlines()
        for line in lines:
            pos = line.find(":")
            line = line[pos + 1:].strip()
            fields = line.split(" - ")
            client = Client(fields[0])
            ClientList.addElement(self, client)
        f.close()

    def setElement(self, index, object):
        ClientList.setElement(self, index, object)
        self.writeFile()

    def setElementById(self, id, object):
        ClientList.setElementById(self, id, object)
        self.writeFile()

    def setRepo(self, newRepo):
        ClientList.setRepo(self, newRepo)
        self.writeFile()

    def addElement(self, element):
        ClientList.addElement(self, element)
        self.writeFile()

    def insertElement(self, element, position):
        ClientList.insertElement(self, element, position)
        self.writeFile()

    def insertElementById(self, element, id):
        ClientList.setElementById(self, element, id)
        self.writeFile()

    def removeElement(self, index):
        ClientList.removeElement(self, index)
        self.writeFile()

    def removeElementById(self, id):
        ClientList.removeElementById(self, id)
        self.writeFile()

    def removeAll(self):
        ClientList.removeAll(self)
        self.writeFile()

    def update(self, elemIndex, name):
        ClientList.update(self, elemIndex, name)
        self.writeFile()

    def updateById(self, elemId, name):
        ClientList.updateById(self, elemId, name)
        self.writeFile()

    def swapElements(self, index1, index2):
        ClientList.swapElements(self, index1, index2)
        self.writeFile()

    def swapElementsById(self, id1, id2):
        ClientList.swapElementsById(self, id1, id2)
        self.writeFile()
