from src.repository.ClientList import ClientList
from src.domain.Client import Client
import pickle
class ClientListPickle(ClientList):
    def __init__(self, fileName="ClientList.pickle"):
        '''
        :param fileName: file to read/write
        Stores data in binary format
        '''
        ClientList.__init__(self)
        self.__fileName = fileName
        self.readFile()

    def readFile(self):
        '''
        Reads data in binary format and loads it into the repository
        '''
        f = open(self.__fileName, "rb")     #read binary
        try:
            self._repo = pickle.load(f)
        except EOFError:  # Empty file
            self._repo = []
        except Exception as ex:  # File can't be accessed
            raise ex
        finally:
            f.close()

    def writeFile(self):
        '''
        Writes data in binary format in a file
        '''
        f = open(self.__fileName, "wb")
        pickle.dump(self._repo, f)
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