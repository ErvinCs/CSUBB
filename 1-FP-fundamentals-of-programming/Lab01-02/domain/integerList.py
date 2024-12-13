#4.contains only prime numbers
#11.sum of elements is 10
#5.contains a single number
#12.all consecutive number pairs have at least 2 common digits

def readListKeyboard():
    '''
    Reads a list of integers from the keyboad.
    :return: read list
    '''
    list = []
    listSize = int(input("Gib list size: "))
    if listSize < 0:
        raise Exception("Size cannot be negative!");

    for i in range(0, listSize):
        list.append(int(input("Gib integer: ")))
    return list

def aSingleNumSequence(list):
    '''
    :return: The longest sequence of the same value in list.
    '''
    result = []
    i = 0
    while i < len(list)-1:
        tempResult = []
        while  i < len(list)-1 and list[i] == list[i+1]:
            if(tempResult == []):
                tempResult.append(list[i])
            tempResult.append(list[i+1])
            i += 1
        i += 1
        if len(tempResult) > len(result):
            result = tempResult
    return result



def digitOccurence(number):
    '''
    :return: A list such that list[digit] == 1 if digit occurs in number.
    '''
    digits = [0]*10
    while number > 0:
        digits[number%10] = 1
        number = number // 10
    return digits

def twoCommonDigits(num1, num2):
    '''
    :return: True if num1 and num2 have at least 2 common digits; False otherwise.
    '''
    num1 = digitOccurence(num1)
    num2 = digitOccurence(num2)
    counter = 0
    for i in range(0, 10):
        if num1[i] == num2[i] and num1[i] == 1:
            counter += 1
    if counter >= 2:
        return True
    else:
        return False


def twoCommonDigitsSequence(list):
    '''
    :return: The longest sequence in list such that consecutive elements have at least 2 common digits.
    '''
    result = []
    i = 0
    while i < len(list)-1:
        tempResult = []
        while i < len(list)-1 and twoCommonDigits(list[i], list[i+1]):
            if tempResult == []:
                tempResult.append(list[i])
            tempResult.append(list[i+1])
            i += 1
        i += 1
        if len(tempResult) > len(result):
             result = tempResult
    return result


def elemSum10Sequencev1(list):
    '''
    :return: The longest sequence of numbers in list such that their sum is 10; [] if no such sequence exists.
    '''
    result = []
    tempResult = []
    i = 0
    while i < len(list):
        sum = list[i]
        if sum == 10:
            tempResult.append(list[i])
        for j in range(i + 1, len(list)):
            sum += list[j]
            if sum == 10:
                tempResult.extend(list[i:j + 1])
        if len(tempResult) > len(result) and len(tempResult) > 1:
            result = tempResult
        tempResult = []
        i += 1
    return result

def primeNumber(number):
    '''
    Checks if a number is prime.
    :return: True if number is prime; False otherwise.
    '''
    if number == 0 or number == 1:
        return False
    if number == 2:
        return True
    for i in range(2, (number//2 + 1)):
        if number % i == 0:
            return False
    return True

def primeNrSequence(list):
    '''
    :return: The longest sequence of prime numbers in list; [] if no such sequence exists.
    '''
    result = []
    tempResult = []
    i = 0
    while i < len(list):
        while i < len(list) and primeNumber(list[i]):
            tempResult.append(list[i])
            i += 1
        i += 1
        if len(tempResult) > len(result) and len(tempResult) > 1:
            result = tempResult
        tempResult = []
    return result

