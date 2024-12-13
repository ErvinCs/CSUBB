from random import randint
from src.domain.Board import Board
from src.repository.ShipRepo import ShipRepo
from src.domain.Ship import Ship

class GameController:
    def __init__(self, boardSize, playerName):
        self.__board = Board(boardSize, "Enemy Board")
        self.__aiTarget = Board(boardSize, "Enemy Target Board")
        self.__targetBoard = Board(boardSize, "Target Board")
        self.__playerBoard = Board(boardSize, "Your Board")
        self.__playerRepo = ShipRepo()
        self.__aiRepo = ShipRepo()
        self.__aiHit = False
        self.__aiHitList = []
        self.__moves = self.genAiMoveList()

    #getAiBoard
    def getBoard(self):
        return self.__board

    def getPlayerRepo(self):
        return self.__playerRepo

    def getAiRepo(self):
        return self.__aiRepo

    #printAiBoard
    def printBoard(self):
        print(str(self.__board))

    def printTargetBoard(self):
        print(str(self.__targetBoard))

    def printPlayerBoard(self):
        print(str(self.__playerBoard))

    def genAiMoveList(self):
        size = self.__board.getSize()
        coords = []
        for i in range(1, size+1):
            if i%2 == 0:
                j = 2
                while j <= size:
                    coords.append((i, j))
                    j += 2
            else:
                j = 1
                while j <= size:
                    coords.append((i, j))
                    j += 2
        return coords

    def validateShip(self, ship, x, y, board):
        if ship.getAlign() == "V":
            if (x + ship.getSize()) > board.getSize():
                return False
            else:
                for i in range(ship.getSize()):
                    if board.getCoord(x + i, y) != 0:
                        return False
        elif ship.getAlign() == "H":
            if (y + ship.getSize()) > board.getSize():
                return False
            else:
                for i in range(ship.getSize()):
                    if board.getCoord(x, y + i) != 0:
                        return False
        else:
            return False
        return True

    def validateCoord(self, x, y, board):
        if x > board.getSize() or y > board.getSize() or x < 1 or y < 1:
            return False
        elif board.getCoord(x, y) != 0:
            return False
        else:
            return True

    def placeShip(self, ship, x, y, board):
        if ship.getAlign() == "V":
            for i in range(0, ship.getSize()):
                board.setCoord(x+i, y, ship.getSize())
        elif ship.getAlign() == "H":
            for i in range(0, ship.getSize()):
                board.setCoord(x, y+i, ship.getSize())


    def aiPlaceShips(self):
        for ship in self.__aiRepo.getRepo() :
            ok = False
            while (not ok):
                x = randint(1, self.__board.getSize())
                y = randint(1, self.__board.getSize())
                align = randint(0, 1)
                if align == 0:
                    ship.setAlign("H")
                else:
                    ship.setAlign("V")
                ok = self.validateShip(ship, x, y, self.__board)
                if ok:
                    self.placeShip(ship, x, y, self.__board)
        print("Computer ships placed!\n")

    def userPlaceShips(self):
        for ship in self.__playerRepo.getRepo():
            print("Place " + ship.getName() + "(" + str(ship.getSize()) + ")")
            ok = False
            while (not ok):
                x, y = self.readCoord()
                align = input("Enter alignment(H/V): ")
                while align != 'H' and align != 'V':
                    align = input("Enter alignment(H/V):\n")
                ship.setAlign(align)
                ok = self.validateShip(ship, x, y, self.__playerBoard)
                if ok:
                    self.placeShip(ship, x, y, self.__playerBoard)
                    self.printPlayerBoard()
                else:
                    print("Ship cannot be placed!\n")
        print("User ships placed!\n")

    def parseInt(self, x):
        try:
            x = int(x)
            return x
        except ValueError:
            return False

    def readCoord(self):
        print("Player move.")
        y = self.parseInt(input("Enter Column: "))
        while y is False or y < 1 or y > self.__playerBoard.getSize():
            y = self.parseInt(input("Enter Column: "))
        convert = {'A': 1, 'B': 2, 'C': 3, 'D': 4, 'E': 5, 'F': 6, 'G': 7, 'H': 8}
        x = input("Enter Row: ")
        while x not in convert.keys():
            x = input('Enter Row:')
        x = convert[x]
        return (x, y)

    def userMove(self):
        self.printPlayerBoard()
        self.printTargetBoard()
        x, y = self.readCoord()
        while self.validateCoord(x, y, self.__targetBoard) is False:
            print("Invalid Coordinates!")
            x, y = self.readCoord()
        if self.__board.getBoard()[x][y] == 0:
            print("\nYou missed!")
            self.__targetBoard.setCoord(x, y, 'M')
            self.__board.setCoord(x, y, 'M')
        else:
            print("Ship hit!")
            if self.checkShipDestroyed(x, y, self.__board, self.__aiRepo):
                print("Ship destroyed!\n")
            self.__targetBoard.setCoord(x, y, 'X')
            self.__board.setCoord(x, y, 'X')
            self.printTargetBoard()
            if self.checkWinCondition():
                return True
        return False

    def aiMove(self):
        if self.__aiHit is True:
            x, y = self.__aiHitList.pop()
            while self.validateCoord(x, y, self.__aiTarget) is False and self.__aiHit is True:
                if self.__aiHitList == []:
                    self.__aiHit = False
                else:
                    x, y = self.__aiHitList.pop()
            if self.__aiHit is True:
                return self.aiTryHit(x, y)
        else:
            rand = randint(0, len(self.__moves) - 1)
            x, y = self.__moves[rand]
            return self.aiTryHit(x, y)

    def aiTryHit(self, x, y):
        if (x, y) in self.__moves:
            self.__moves.remove((x, y))
        if self.__playerBoard.getBoard()[x][y] == 0:
            print("The Computer missed!\n")
            self.__playerBoard.setCoord(x, y, 'M')
            self.__aiTarget.setCoord(x, y, 'M')
            return False
        else:
            print("Ship hit!\n")
            if self.checkShipDestroyed(x, y, self.__playerBoard, self.__playerRepo):
                print("Ship destroyed!\n")
            self.__playerBoard.setCoord(x, y, 'X')
            self.__aiTarget.setCoord(x, y, 'X')
            if self.checkWinCondition():
                self.printBoard()
                self.printPlayerBoard()
                return True
            self.__aiHit = True
            self.__aiHitList.append((x+1, y))
            self.__aiHitList.append((x, y+1))
            self.__aiHitList.append((x-1, y))
            self.__aiHitList.append((x, y-1))
            return False


    def checkWinCondition(self):
        if len(self.__playerRepo) == 0:
            return True
        elif len(self.__aiRepo) == 0:
            return True
        else:
            return False

    def checkShipDestroyed(self, x, y, board, repo):
        shipSize = board.getBoard()[x][y]
        board.setCoord(x, y, 'X')    #just to be sure
        boardSize =  board.getSize()
        i = x+1
        while i <= boardSize:
            if board.getBoard()[i][y] == shipSize:
                return False
            i += 1
        i = x-1
        while i > 0:
            if board.getBoard()[i][y] == shipSize:
                return False
            i -= 1
        i = y+1
        while i <= boardSize:
            if board.getBoard()[x][i] == shipSize:
                return False
            i += 1
        i = y - 1
        while i > 0:
            if board.getBoard()[x][i] == shipSize:
                return False
            i -= 1
        repo.removeShipBySize(shipSize)
        return True




















