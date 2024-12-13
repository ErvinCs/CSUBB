ExpenseTypeList = ["water", "heating", "electricity", "gas", "other"]

def newExpense(apartment, type, amount):
    '''
    :param apartment: integer > 0
    :param type: a type from the ExpenseTypeList = ["water", "heating", "electricity", "gas", "other"]
    :param amount: integer > 0
    :return: a dictionary = {"apartment": apartment, "type": type, "amount": amount}
    '''
    return {"apartment": apartment, "type": type, "amount": amount}

def checkExpense(expense):
    '''
    :param expense: a dictionary = {"apartment": apartment, "type": type, "amount": amount}
    :return: True if expense matches its format; False otherwise
    '''
    if type(expense["apartment"]) != int or expense["apartment"] < 0:
        return False
    if expense["type"] not in ExpenseTypeList:
        return False
    if type(expense["amount"]) != int or expense["amount"] < 0:
        return False
    return True

def addExpense(list, expense):
    '''
    :param list: the list to append
    :param expense: a dictionary = {"apartment": apartment, "type": type, "amount": amount}
    :return: appends list with expense if its format is correct;
    raises an exception otherwise
    '''
    if checkExpense(expense) == False:
        raise Exception("Invalid input!")
    list.append(expense)

def removeExpenseAp(list, apartment):
    '''
    :param list: the list of all expenses
    :param apartment: apartment to remove; positive integer
    Removes all expenses matching the given apartment;
    raises an exception if the list is empty or if nothing was removed.
    '''
    if len(list) == False:
        raise Exception("Empty List!")
    removed = False
    i = 0
    while i < len(list):
        if list[i]["apartment"] == apartment:
            list.remove(list[i])
            removed = True
            i -= 1
        i += 1
    if removed == False:
        raise Exception("No matching apartment found!")



def removeExpenseInterval(list, apStart, apEnd):
    '''
    :param list: the list of all expenses
    :param apStart: interval lower bound; positive integer
    :param apEnd: interval upper bound; positive integer
    Removes all expenses for apartments between apStart and apEnd;
    raises an exception if the list is empty or if nothing was removed.
    '''
    if len(list) == 0:
        raise Exception("Empty List!")
    removed = False
    i = 0
    while i < len(list):
        if list[i]["apartment"] >= apStart and list[i]["apartment"] <= apEnd:
            list.remove(list[i])
            i -= 1
            removed = True
        i += 1
    if removed == False:
        raise Exception("No matching apartment found!")

def removeExpenseType(list, type):
    '''
    :param list: the list of all expenses
    :param type: value from ExpenseTypeList
    Removes all expenses for the given type.
    raises an exception if the list is empty or if nothing was removed.
    '''
    if len(list) == 0:
        raise Exception
    removed = False
    i = 0
    while i < len(list):
        if list[i]["type"] == type:
            list.remove(list[i])
            removed = True
            i -= 1
        i += 1
    if removed == False:
        raise Exception("No matching apartment found!")


def replace(list, apartment, type, newAmount):
    '''
    :param list: the list of all expenses
    :param newAmount: replaces amount for the given entries
    Replaces the amount for all expenses matching the apartment and type parameters.
    raises an exception if the list is empty or if nothing was replaced.
    '''
    if len(list) == 0:
        raise Exception("Empty List!")
    replaced = False
    for t in list:
        if t["apartment"] == apartment and t["type"] == type:
            t["amount"] = newAmount
            replaced = True
    if replaced == False:
        raise Exception("No matching apartment found!")

def printExpense(expense):
    '''
    Prints an expense
    '''
    print(str(expense["apartment"]) + ": " + expense["type"] + ", " + str(expense["amount"]))

def printAll(list):
    '''
    :param list: list of all expenses
    Prints the list of all expenses
    '''
    if len(list) == 0:
        raise Exception("Empty List!")
    for expense in list:
        printExpense(expense)

def printApartment(list, apartment):
    '''
    :param list: list of all expenses
    Prints all expenses for apartment
    raises an exception if the list is empty
    '''
    if len(list) == 0:
        raise Exception("Empty List!")
    printed = False
    for t in list:
        if t["apartment"] == apartment:
            printExpense(t)
            printed = True
    if printed == False:
        raise Exception("No matching apartment found!")

def printCompare(list, sign, amount):
    '''
    :param list: list of all expenses
    :param sign: evaluates the expenses in relation to amount
    Prints all expenses that satisfy the condition
    '''
    if len(list) == 0:
        raise Exception("Empty List!")
    operator = {"<": (lambda x,y: x<y), ">": (lambda x,y: x>y), "=": (lambda x,y: x==y),
                 "<=": (lambda x,y: x<=y), ">=": (lambda x,y: x>=y)}
    printed = False
    for t in list:
        if operator[sign] (t["amount"], amount):
            printExpense(t)
            printed = True
    if printed == False:
        raise Exception("No matching apartment found!")

def sumType(list, type):
    '''
    :param list: list of all expenses
    :return: the sum of all expenses having the given type.
    '''
    if len(list) == 0:
        raise Exception("Empty List!")
    sum = 0
    for t in list:
        if t["type"] == type:
            sum += t["amount"]
    return sum

def maxPerType(list, apartment):
    '''
    :param list: the list of all expenses
    :return: a dictionary: "expense_type": maximum_amount, for apartment
    '''
    if len(list) == 0:
        raise Exception("Empty List!")
    max = {"water": 0, "heating": 0, "electricity": 0, "gas": 0, "other": 0}
    for t in list:
        if t["apartment"] == apartment:
            if t["amount"] > max[t["type"]]:
                max[t["type"]] = t["amount"]
    return max


def sortApExpenses(list):
    '''
    :param list: the list of all apartments
    :return: the list of apartments sorted in ascending order by total amount of expenses
    '''
    if len(list) == 0:
        raise Exception("Empty List!")
    ap = []
    am = []
    #Sum up all amount entries for each apartment
    for t in list:
        if t["apartment"] not in ap:
            ap.append(t["apartment"])
            am.append(t["amount"])
        else:
            i = ap.index(t["apartment"])
            am[i] += t["amount"]
    #Sort by amount
    i = 0
    while i < len(ap)-1:
        j = i + 1
        while j < len(ap):
            if am[j] < am[i]:
                am[j], am[i] = am[i], am[j]
                ap[j], ap[i] = ap[i], ap[j]
            j += 1
        i += 1
    #Build a list of tuples: (apartment, total_expenses_per_apartment)
    apam = []
    i = 0
    while i < len(ap):
        apam.append((ap[i], am[i]))
        i += 1
    return apam

import operator
def sortApType(list):
    '''
    :param list: the list of all expenses
    :return: the total amount of expenses for each type
    '''
    if len(list) == 0:
        raise Exception("Empty List!")
    totalType = {"water": 0, "heating": 0, "electricity": 0, "gas": 0, "other": 0}
    for t in list:
        totalType[t["type"]] += t["amount"]
    returnList = sorted(totalType.items(), key=operator.itemgetter(1))
    return returnList

def filterType(list, type):
    '''
    :param list: the list of all expenses
    :param type: expense type to keep
    :return: the list containing only expenses of the given type
    '''
    if len(list) == 0:
        raise Exception("Empty List!")
    i = 0
    while i < len(list):
        if list[i]["type"] != type:
            list.remove(list[i])
            i -= 1
        i += 1
    return list


def filterValueSmaller(list, compAmount):
    '''
    :param list: the list of all expenses
    :param amount: keep expenses having amount smaller than compAmount
    :return: the list containing the expenses having an amount of money smaller than compAmount
    '''
    if len(list) == 0:
        raise Exception("Empty List!")
    i = 0
    while i < len(list):
        if list[i]["amount"] >= compAmount:
            list.remove(list[i])
            i -= 1
        i += 1
    return list
