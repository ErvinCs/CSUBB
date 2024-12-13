class Ship:
    shipClass = {2: "destroyer", 3: "cruiser", 4: "battleship"}
    def __init__(self, size, align):
        self.__size = size
        self.__align = align    #H(Horizontal) or V(Vertical)
        self.__name = Ship.shipClass[size]

    def getName(self):
        return self.__name

    def getSize(self):
        return self.__size

    def setSize(self, size):
        self.__size = size

    def getAlign(self):
        return self.__align

    def setAlign(self, align):
        self.__align = align

    def decrSize(self):
        self.__size -= 1
        return self.__size

    def __str__(self):
        string = self.getName()
        string += " " + str(self.getSize())
        string += " " + self.getAlign() + "; "
        return string
