import domain.testExpense
from control import listControl
from copy import deepcopy
def listCommands(list):
    '''
    List available command.
    '''
    string = "Commands:\n"
    string += "list\n"
    string += "list <apartment>\n"
    string += "list [<, =, >] <amount>\n"
    string += "add <apartment> <type> <amount>\n"
    string += "remove <aparmtent>\n"
    string += "remove <start apartment> to <end apartment>\n"
    string += "remove <type>\n"
    string += "replace <apartment> <type> with <amount>\n"
    string += "sum <type>\n"
    string += "max <apartment>\n"
    string += "sort apartment\n"
    string += "sort type\n"
    string += "filter <type>\n"
    string += "filter <value>\n"
    string += "undo\n"
    string += "help\n"
    print(string)

def readCommand():
    '''
    Reads a command given by the user.
    :return: a tuple: (command, command_arguments)
    '''
    command = input("Enter command: ")
    pos = command.find(" ")
    if(pos == -1):
        return (command, "")
    cmd = command[:pos]     #Command
    args = command[(pos+1):]    #Arguments
    args = args.replace(";", "")
    args = args.split()     #Split arguments
    args = [e.strip() for e in args]    #Get rid of whitespaces
    return (cmd, args)


ver = 0
def runConsole(list):
    runList = list
    listHistory = []
    listHistory.append(deepcopy(runList))
    commandList = {"add": listControl.add,"remove": listControl.remove, "list": listControl.list,
                   "sum": listControl.sum, "max":listControl.max ,"sort": listControl.sort,
                   "filter": listControl.filter, "help": listCommands}
    listCommands(runList)
    while True:
        (command, args) = readCommand()
        if command == "exit":
            input("Goodbye!")
            break
        try:
            global ver
            if command == "undo":
                if ver == 0:
                    raise Exception("Initial state reached!")
                else:
                    runList = listHistory.pop(ver);
                    ver -= 1
                    runList = deepcopy(listHistory[ver]);
            else:
                commandList[command](runList, *args)
                if runList != listHistory[ver]:
                    listHistory.append(deepcopy(runList))
                    ver += 1
        except KeyError as ke:
            print("Command does not exist!", ke)
        except Exception as ex:
            print("Error:", ex)

def initList():
    return listControl.initList()


