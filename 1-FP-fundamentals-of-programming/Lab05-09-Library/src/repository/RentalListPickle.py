from src.repository.RentalList import RentalList
from src.domain.Rental import Rental
import pickle
class RentalListPickle(RentalList):
    def __init__(self, fileName="RentalList.pickle"):
        '''
        :param fileName: file to read/write
        Stores data in binary format
        '''
        RentalList.__init__(self)
        self.__fileName = fileName
        self.readFile()

    def readFile(self):
        '''
        Reads data in binary format and loads it into the repository
        '''
        f = open(self.__fileName, "rb")     # read binary
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
        RentalList.setElement(self, index, object)
        self.writeFile()

    def setElementById(self, id, object):
        RentalList.setElementById(self, id, object)
        self.writeFile()

    def setRepo(self, newRepo):
        RentalList.setRepo(self, newRepo)
        self.writeFile()

    def addElement(self, element):
        RentalList.addElement(self, element)
        self.writeFile()

    def insertElement(self, element, position):
        RentalList.insertElement(self, element, position)
        self.writeFile()

    def insertElementById(self, element, id):
        RentalList.setElementById(self, element, id)
        self.writeFile()

    def removeElement(self, index):
        RentalList.removeElement(self, index)
        self.writeFile()

    def removeElementById(self, id):
        RentalList.removeElementById(self, id)
        self.writeFile()

    def removeAll(self):
        RentalList.removeAll(self)
        self.writeFile()

    def update(self, elemIndex, returnDate):
        RentalList.update(self, elemIndex, returnDate)
        self.writeFile()

    def updateById(self, elemId, returnDate):
        RentalList.updateById(self, elemId, returnDate)
        self.writeFile()

    def swapElements(self, index1, index2):
        RentalList.swapElements(self, index1, index2)
        self.writeFile()

    def swapElementsById(self, id1, id2):
        RentalList.swapElementsById(self, id1, id2)
        self.writeFile()