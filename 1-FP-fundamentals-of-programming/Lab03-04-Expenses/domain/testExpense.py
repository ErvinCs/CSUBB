from domain import expense

def testCheckExpense():
    assert expense.checkExpense(expense.newExpense("definitely not an int", "gas", 2)) == False
    assert expense.checkExpense(expense.newExpense(3.1415, "gas", 2)) == False
    assert expense.checkExpense(expense.newExpense(3, "gas", -20)) == False
    assert expense.checkExpense(expense.newExpense(12, "jews", 13)) == False
    assert expense.checkExpense(expense.newExpense(101, "other", "sarak")) == False
    assert expense.checkExpense(expense.newExpense(1, "water", 2)) == True

def setUp():
    testList = []
    expense.addExpense(testList, expense.newExpense(11, "gas", 100))
    expense.addExpense(testList, expense.newExpense(1, "water", 200))
    expense.addExpense(testList, expense.newExpense(5, "other", 10))
    expense.addExpense(testList, expense.newExpense(5, "heating", 12))
    expense.addExpense(testList, expense.newExpense(7, "electricity", 9000))
    assert len(testList) == 5
    expense.addExpense(testList, expense.newExpense(11, "other", 55))
    expense.addExpense(testList, expense.newExpense(11, "other", 1055))
    assert len(testList) == 7
    return testList

def testRemoveExpenseAp():
    testList = setUp()
    expense.removeExpenseAp(testList, 1)
    assert len(testList) == 6
    expense.removeExpenseAp(testList, 5)
    assert len(testList) == 4

def testRemoveExpenseInterval():
    testList = setUp()
    expense.removeExpenseInterval(testList, 2, 7)
    assert len(testList) == 4

def testRemoveExpenseType():
    testList = setUp()
    expense.removeExpenseType(testList, "other")
    assert len(testList) == 4

def testReplace():
    testList = setUp()
    expense.replace(testList, 11, "other", 999)
    assert testList[5]["amount"] == 999
    assert testList[6]["amount"] == 999

def testSumType():
    testList = setUp()
    assert expense.sumType(testList, "water") == 200
    assert expense.sumType(testList, "other") == 1120

def testMaxPerType():
    testList = setUp()
    assert expense.maxPerType(testList, 11) == {"water": 0, "heating": 0, "electricity": 0, "gas": 100, "other": 1055}

def testSortApartment():
    testList = setUp()
    assert expense.sortApExpenses(testList) == [(5, 22), (1, 200), (11, 1120), (7, 9000)]

def testSortType():
    testList = setUp()
    assert expense.sortApType(testList) == [('heating', 12), ('gas', 100), ('water', 200), ('other', 1120), ('electricity', 9000)]

def testFilterType():
    testList = setUp()
    assert expense.filterType(testList, "other") == [{5, "other", 10}, {11, "other", 55}, {11, "other", 1055}]

def filterValueSmaller():
    testList = setUp()
    assert expense.filterValueSmaller(testList, 20) == [{5, "other", 10}, {5, "heating", 12}]

def runTests():
    #testCheckExpense()
    #testRemoveExpenseAp()
    #testRemoveExpenseInterval()
    #testRemoveExpenseType()
    #testReplace()
    #testSumType()
    #testMaxPerType()
    #testSortApartment()
    #testSortType()
    #testReplace()
    pass