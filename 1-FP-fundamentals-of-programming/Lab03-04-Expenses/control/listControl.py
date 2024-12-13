import domain.expense

def add(list, apartment, type, amount, *args):
    expense = domain.expense.newExpense(int(apartment), type, int(amount))
    domain.expense.addExpense(list, expense)
    if args:
        i = 0
        while i < len(args):
            ap = int(args[i])
            ty = args[i+1]
            am = int(args[i+2])
            i += 3
            expense = domain.expense.newExpense(ap, ty, am)
            domain.expense.addExpense(list, expense)

def remove(list, apStart=None, to=None, apEnd=None, *args):
    if apStart not in domain.expense.ExpenseTypeList:
        if int(apStart) and apEnd is None and to is None:
            domain.expense.removeExpenseAp(list, int(apStart))
        elif int(apStart) and int(apEnd) and to == "to":
            domain.expense.removeExpenseInterval(list, int(apStart), int(apEnd))
            if args:
                i = 0
                while i < len(args):
                    apS = int(args[i])
                    apE = int(args[i+2])
                    i += 3
                    domain.expense.removeExpenseInterval(list, apS, apE)
        else:
            raise Exception("Invalid input!")
    elif to is None and apEnd is None:
        domain.expense.removeExpenseType(list, apStart)
    else:
        raise Exception("Invalid input!")

def list(list, target=None, amount = None):
    if target is None and amount is None:
        domain.expense.printAll(list)
    elif target in ['<', '>', '<=', '>=', '='] and int(amount):
        domain.expense.printCompare(list, target, int(amount))
    elif int(target) and amount is None:
        domain.expense.printApartment(list, int(target))
    else:
        raise Exception("Invalid input!")

def sum(list, type):
    if type not in ["water", "heating", "electricity", "gas", "other"]:
        raise Exception("Invalid input!")
    sum = domain.expense.sumType(list, type)
    if sum == 0:
        raise Exception("No matching apartment found!")
    print("Sum is: " + str(sum))

def max(list, apartment):
    max = domain.expense.maxPerType(list, int(apartment))
    if max == {"water": 0, "heating": 0, "electricity": 0, "gas": 0, "other": 0}:
        raise Exception("No matching apartment found!")
    print("Maximum amount per exepense: \n" + str(max))

def sort(list, arg=None):
    if arg == "apartment":
        sorted = domain.expense.sortApExpenses(list)
        for ap, am in sorted:
            apartment, amount = ap, am
            print(apartment, ', ', amount)
    elif arg == "type":
        sorted = domain.expense.sortApType(list)
        for t, p in sorted:
            type, price = t, p
            print(type + ', ' + str(price))
    else:
        raise Exception("Invalid input!")

def filter(list, typeValue):
    if typeValue in ["water", "heating", "electricity", "gas", "other"]:
        return domain.expense.filterType(list, typeValue)
    elif int(typeValue):
        return domain.expense.filterValueSmaller(list, int(typeValue))
    else:
        raise Exception("Invalid input!")

def initList():
    list = []
    domain.expense.addExpense(list, domain.expense.newExpense(11, "gas", 100))
    domain.expense.addExpense(list, domain.expense.newExpense(1, "water", 200))
    domain.expense.addExpense(list, domain.expense.newExpense(5, "other", 10))
    domain.expense.addExpense(list, domain.expense.newExpense(5, "heating", 12))
    domain.expense.addExpense(list, domain.expense.newExpense(7, "electricity", 9000))
    domain.expense.addExpense(list, domain.expense.newExpense(11, "other", 55))
    domain.expense.addExpense(list, domain.expense.newExpense(11, "other", 1055))
    domain.expense.addExpense(list, domain.expense.newExpense(12, "gas", 99))
    domain.expense.addExpense(list, domain.expense.newExpense(13, "heating", 55))
    domain.expense.addExpense(list, domain.expense.newExpense(12, "other", 77))
    domain.expense.addExpense(list, domain.expense.newExpense(1, "electricity", 66))
    domain.expense.addExpense(list, domain.expense.newExpense(7, "gas", 250))
    return list
