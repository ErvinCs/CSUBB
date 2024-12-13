class Board:
    def __init__(self, size, name):
        self.__size = size
        self.__board = [[0]*(size+1) for i in range(size+1)]
        self.__name = name
        #0 - None; X - Hit; M - Miss

    def getSize(self):
        return self.__size

    def setSize(self, size):
        self.__size = size

    def getBoard(self):
        return self.__board

    def setBoard(self, board):
        self.__board = board

    def getCoord(self, x, y):
        return self.__board[x][y]

    def setCoord(self, x, y, value):
        self.__board[x][y] = value

    def getName(self):
        return self.__name

    def setName(self, name):
        self.__name = name

    def printBoard(self):
        print(str(self) + "\n")
        for i in range(1,self.getSize()+1):
            print('-')

    def __str__(self):
        convert = {1: 'A', 2: 'B', 3: 'C', 4: 'D', 5: 'E', 6: 'F', 7: 'G', 8: 'H'}
        boardStr = self.getName() + "\n"
        i = 1
        boardStr += "  "
        for k in range(1, self.getSize()+1):
            boardStr += str(k) + " "
        boardStr += "\n"
        while i <= self.getSize():
            j = 1
            while j <= self.getSize():
                if j == 1:
                    boardStr += convert[i] + " "
                boardStr += str(self.getBoard()[i][j]) + " "
                j += 1
            boardStr += "\n"
            i += 1
        return boardStr

    def __len__(self):
        return self.getSize()



