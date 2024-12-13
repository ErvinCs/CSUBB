from src.ui.TextUI import TextUI
from src.controller.GameController import GameController

if __name__ == '__main__':
    boardSize = 8
    playerName = "Player"
    play = True
    while play is True:
        gCon = GameController(boardSize, playerName)
        ui = TextUI(gCon)
        win = ui.run()
        if win is True:
            print("You win!\n")
        else:
            print("You lose!\n")
        play = input("Play again?(Y/_)")
        if play == 'Y':
            play = True
        else:
            play = False
    input("Bye.")