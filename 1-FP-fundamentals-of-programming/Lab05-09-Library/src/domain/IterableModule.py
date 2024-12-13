from copy import deepcopy
class IterableEx:
    def __init__(self, start, end):
        self.start = start - 1
        self.end = end - 1

    def __iter__(self):
        #Called at the start of loops
        return self

    def __next__(self):
        #Called at each loop increment
        if self.start > self.end:
            raise StopIteration
        else:
            self.start += 1
            return self.start

class Iterable:
    def __init__(self, repo):
        self.__repo = repo
        self.__index = -1
        self.__length = len(repo)

    def resetIndex(self):
        self.__index = -1

    def __str__(self):
        return str(self.__repo)

    def __contains__(self, item):
        for elem in self.__repo:
            if item == elem:
                return True
        return False

    def __len__(self):
        return len(self.__repo)

    def __getitem__(self, index):
        return self.__repo[index]

    def __setitem__(self, index, value):
        self.__repo[index] = value

    def __delitem__(self, index):
        del self.__repo[index]

    def __iter__(self):
        return self

    def __next__(self):
        if self.__length - 1 == self.__index:
            self.__index = -1
            raise StopIteration
        else:
            self.__index += 1
            return self.__repo[self.__index]

    def append(self, elem):
        '''
        Append elem to the repo
        '''
        self.__repo.append(elem)
        self.__length += 1
        self.__index = -1

    def insert(self, pos, elem):
        '''
        Insert elem in the repo at the position pos
        '''
        self.__repo.insert(pos, elem)
        self.__length += 1
        self.__index = -1

    def pop(self, index):
        '''
        :param index: of the element to pop from the repo
        :return: the element at index
        '''
        x = self.__repo.pop(index)
        self.__length -= 1
        self.__index = -1
        return x

    def remove(self, elem):
        '''
        :param elem: value of the element to remove
        '''
        self.__repo.remove(elem)
        self.__length -= 1
        self.__index = -1

    def clear(self):
        '''
        Empties the repo
        '''
        self.__repo.clear()
        self.__length = -1
        self.__index = -1

    def updateById(self, *args):
        self.__repo.updateById(*args)
        print("it")
        self.__index = -1


def compare(e1, e2, sign):
    '''
    :param e1, e2: elements to compare
    :param sign: operator to use
    :return: boolean; e1 sign e2
    '''
    operator = {"<": (lambda x, y: x < y), ">": (lambda x, y: x > y), "=": (lambda x, y: x == y),
                "<=": (lambda x, y: x <= y), ">=": (lambda x, y: x >= y)}
    return operator[sign](e1, e2)

def gnomeSort(list, sign):
    '''
    :param list: to sort
    :param sign: for ordering the list
    :return: the sorted list
    '''
    pos = 0
    while pos < len(list):
        if pos == 0 or compare(list[pos], list[pos - 1], sign):
            pos += 1
        else:
            list[pos], list[pos - 1] = list[pos - 1], list[pos]
            pos -= 1
    return list

def gnomeSortByList(initList, sortBy):
    '''
    :param initList: to sort
    :param sortBy: list to sort by
    :return: a new sorted list
    '''
    list = deepcopy(initList)
    pos = 0
    while pos < len(sortBy):
        if pos == 0 or compare(sortBy[pos], sortBy[pos - 1], "<="):
            pos += 1
        else:
            list[pos], list[pos - 1] = list[pos - 1], list[pos]
            sortBy[pos], sortBy[pos - 1] = sortBy[pos - 1], sortBy[pos]
            pos -= 1
    return list

def filter(list, accept):
    '''
    :param list: to filter
    :param accept: decides whether a value passes the filter
    :return: filtered list
    '''
    i = 0
    while i < len(list):
        if not accept(list[i]): #don't keep element if it doesen't apass the acceptance
            list.pop(i)
        i += 1
    return list

if __name__ == '__main__':
    for c in IterableEx(0, 5):
        print(c)
    print("=============\n")
    i2 = Iterable([2,4,6,8,10])
    for v in i2:
        print(v)
