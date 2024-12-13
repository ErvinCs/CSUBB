from domain import integerList

def testPrimeNumber():
    assert integerList.primeNumber(1) == False
    assert integerList.primeNumber(2) == True
    assert integerList.primeNumber(13) == True
    assert integerList.primeNumber(999) == False

def testPrimeNrSequence():
    assert integerList.primeNrSequence([0, 4, 6, 14, 256]) == []
    assert integerList.primeNrSequence([2, 4, 6, 14, 256]) == []
    assert integerList.primeNrSequence([2, 3, 5, 17]) == [2, 3, 5, 17]
    assert integerList.primeNrSequence([2]) == []
    assert integerList.primeNrSequence([3, 3, 4, 6, 3, 5, 17]) == [3, 5, 17]
    assert integerList.primeNrSequence([4, 3, 5, 16, 17]) == [3, 5]

def testElemSum10v1():
    assert integerList.elemSum10Sequencev1([1, 2, -10, 3, 4, 100, -90]) == [1, 2, -10, 3, 4, 100, -90]
    assert integerList.elemSum10Sequencev1([2, 4, 7, 4]) == []
    assert integerList.elemSum10Sequencev1([-1, 9, 2, 13, -11, 100]) == [-1, 9, 2]
    assert integerList.elemSum10Sequencev1([100, -11, -1, 2, 9]) == [-1, 2, 9]
    assert integerList.elemSum10Sequencev1([10, -11, -1]) == []

def testASingleNumSequence():
    assert integerList.aSingleNumSequence([2, 2, 2, 2]) == [2, 2, 2, 2]
    assert integerList.aSingleNumSequence([1, 3, 4, 5]) == []
    assert integerList.aSingleNumSequence([3, 3, 3, 2, 2, 4, 2]) == [3, 3, 3]
    assert integerList.aSingleNumSequence([3, 3, 2, 2, 2, 4, 2]) == [2, 2, 2]
    assert integerList.aSingleNumSequence([9, 8, 7, 6, 2, 2]) == [2, 2]
    assert integerList.aSingleNumSequence([1, 3, 5, 9, 9, 9, 7, 10]) == [9, 9, 9]

def testDigitOccurence():
    assert integerList.digitOccurence(12332145) == [0, 1, 1, 1, 1, 1, 0, 0, 0, 0]

def testTwoCommonDigits():
    assert integerList.twoCommonDigits(1234, 3456) == True
    assert integerList.twoCommonDigits(1234, 567) == False

def testTwoCommonDigitsSequence():
    assert integerList.twoCommonDigitsSequence([12, 341, 79]) == []
    assert integerList.twoCommonDigitsSequence([12, 2213, 31, 314, 451]) == [12, 2213, 31, 314, 451]
    assert integerList.twoCommonDigitsSequence([12, 13, 14, 51, 66, 12]) == []
    assert integerList.twoCommonDigitsSequence([12, 2213, 23, 3, 0, 314, 451, 441]) == [12, 2213, 23]
    assert integerList.twoCommonDigitsSequence([12, 2213, 79, 987, 878]) == [79, 987, 878]
    assert integerList.twoCommonDigitsSequence([1, 12, 2213, 31, 314, 451, 55]) == [12, 2213, 31, 314, 451]



def runTests():
    testPrimeNumber()
    testPrimeNrSequence()
    testElemSum10v1()
    testDigitOccurence()
    testTwoCommonDigits()
    testASingleNumSequence()
    testTwoCommonDigitsSequence()
