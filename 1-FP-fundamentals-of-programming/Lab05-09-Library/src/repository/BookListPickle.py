from src.repository.BookList import BookList
from src.domain.Book import Book
import pickle
class BookListPickle(BookList):
    def __init__(self, fileName="BookList.pickle"):
        '''
        :param fileName: file to read/write
        Stores data in binary format
        '''
        BookList.__init__(self)
        self.__fileName = fileName
        self.readFile()

    def readFile(self):
        '''
        Reads data in binary format and loads it into the repository
        '''
        f = open(self.__fileName, "rb")     # read binary
        try:
            self._repo = pickle.load(f)
        except EOFError:    # Empty file
            self._repo = []
        except Exception as ex: # File can't be accessed
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
        BookList.setElement(self, index, object)
        self.writeFile()

    def setElementById(self, id, object):
        BookList.setElementById(self, id, object)
        self.writeFile()

    def setRepo(self, newRepo):
        BookList.setRepo(self, newRepo)
        self.writeFile()

    def addElement(self, element):
        BookList.addElement(self, element)
        self.writeFile()

    def insertElement(self, element, position):
        BookList.insertElement(self, element, position)
        self.writeFile()

    def insertElementById(self, element, id):
        BookList.setElementById(self, element, id)
        self.writeFile()

    def removeElement(self, index):
        BookList.removeElement(self, index)
        self.writeFile()

    def removeElementById(self, id):
        BookList.removeElementById(self, id)
        self.writeFile()

    def removeAll(self):
        BookList.removeAll(self)
        self.writeFile()

    def update(self, elemIndex, title, description, author):
        BookList.update(self, elemIndex, title, description, author)
        self.writeFile()

    def updateById(self, elemId, title, description, author):
        BookList.updateById(self, elemId, title, description, author)
        self.writeFile()

    def swapElements(self, index1, index2):
        BookList.swapElements(self, index1, index2)
        self.writeFile()

    def swapElementsById(self, id1, id2):
        BookList.swapElementsById(self, id1, id2)
        self.writeFile()