from src.repository.RentalList import RentalList
from src.domain.Rental import Rental
from datetime import date
class RentalListFile(RentalList):
    def __init__(self, fileName="RentalList.txt"):
        '''
        :param fileName: file to read/write
        Stores data in string format
        '''
        RentalList.__init__(self)
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
        Reads rentals from a text file
        '''
        f = open(self.__fileName, "r")
        lines = f.readlines()
        for line in lines:
            pos = line.find(":")
            line = line[pos + 1:].strip()
            fields = line.split(",")
            #Get Client numeral ID's only
            clienttxt = str(fields[0])
            cpos = clienttxt.find(" ")
            clienttxt = clienttxt[cpos+1].strip()
            #Get Book numeral ID's only
            booktxt = str(fields[1])
            bpos = booktxt.find(" ")
            booktxt = booktxt[bpos-1].strip()
            #Get RentDate and DueDate
            due, to, ret = fields[2].split()
            due = due.strip()
            duey, duem, dued = due.split("-")
            ret = ret.strip()
            rety, retm, retd = ret.split("-")
            book = Rental(int(clienttxt), int(booktxt), date(int(duey), int(duem), int(dued)), date(int(rety), int(retm), int(retd)), fields[3])
            RentalList.addElement(self, book)
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


