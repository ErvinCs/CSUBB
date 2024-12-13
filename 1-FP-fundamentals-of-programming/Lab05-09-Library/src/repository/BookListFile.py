from src.repository.BookList import BookList
from src.domain.Book import Book
class BookListFile(BookList):
    def __init__(self, fileName="BookList.txt"):
        '''
        :param fileName: file to read/write
        Stores data in string format
        '''
        BookList.__init__(self)
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
        Reads books from a text file
        '''
        f = open(self.__fileName, "r")
        lines = f.readlines()
        for line in lines:
            pos = line.find(":")
            line = line[pos+1:].strip()
            fields = line.split(" - ")
            book = Book(fields[0].strip(), fields[1].strip(), fields[2].strip())
            BookList.addElement(self, book)
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


