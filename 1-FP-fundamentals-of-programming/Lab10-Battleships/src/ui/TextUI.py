from src.controller.GameController import GameController

class TextUI:
    def __init__(self, gCon):
        self.__gCon = gCon

    def setUp(self):
        print("Computer placing ships.\n")
        print("...\n")
        self.__gCon.aiPlaceShips()
        print("Player placing ships.\n")
        self.__gCon.printPlayerBoard()
        self.__gCon.userPlaceShips()
        print("Start game.\n")

    def run(self):
        self.setUp()
        game = True
        while game:
            #Player move
            win = self.__gCon.userMove()
            #Check vicotry
            if win is True:
                print("You win!\n")
                return True
            #Computer move
            win = self.__gCon.aiMove()
            #Check victory - AUTOMATED
            if win is True:
                print("You lose!\n")
                return False


