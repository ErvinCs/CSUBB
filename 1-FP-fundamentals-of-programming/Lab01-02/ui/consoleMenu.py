from domain import integerList

def listConsoleMenu():
    '''
    List available commands.
    '''
    string = "\nCommands:\n"
    string += '\t 0 - Exit\n'
    string += '\t 1 - Read list\n'
    string += '\t 2 - Print list\n'
    string += '\t 3 - Print sequence for: contains only prime numbers\n'
    string += '\t 4 - Print sequence for: sum of elements is 10\n'
    string += '\t 5 - Print sequence for: contains a single number\n'
    string += '\t 6 - Print sequence for: consecutive number pairs have at least 2 common digits\n'
    print(string)

def executeConsoleMenu():
    '''
    Starts the menu loop.
    '''
    listConsoleMenu()
    list = []
    while True:
        try:
            command = int(input("\nEnter command: ").strip())
            if command == 0:
                print("Terminated.\n")
                break
            elif command == 1:
                list = integerList.readListKeyboard()
            elif command == 2:
                print(list)
            elif command == 3:
                print(integerList.primeNrSequence(list))
            elif command == 4:
                print(integerList.elemSum10Sequencev1(list))
            elif command == 5:
                print(integerList.aSingleNumSequence(list))
            elif command == 6:
                print(integerList.twoCommonDigitsSequence(list))
            else:
                raise Exception("Incorrect command!")
        except Exception as e:
            print("Error: " + str(e))




