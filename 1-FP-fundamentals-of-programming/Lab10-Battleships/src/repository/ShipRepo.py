from src.domain.Ship import Ship
class ShipRepo:
    def __init__(self):
        self.__repo = []
        self.initShips()

    def getRepo(self):
        return self.__repo

    def setRepo(self, repo):
        self.__repo = repo

    def addShip(self, ship):
        self.__repo.append(ship)

    def removeShip(self, shipName):
        i = 0
        while i < len(self.__repo):
            if self.__repo[i].getName() == shipName:
                return self.__repo.pop(i)
            i += 1
        raise Exception

    def removeShipBySize(self, shipSize):
        i = 0
        while i < len(self.__repo):
            if self.__repo[i].getSize() == shipSize:
                return self.__repo.pop(i)
            i += 1
        raise Exception

    def initShips(self):
        for ship in Ship.shipClass.keys():
            self.__repo.append(Ship(ship, 'V'))

    def __str__(self):
        string = ""
        for ship in self.__repo:
            string += str(ship)
        return string

    def __len__(self):
        return len(self.__repo)





