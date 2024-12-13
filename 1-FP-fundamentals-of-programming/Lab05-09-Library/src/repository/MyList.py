from copy import deepcopy
from src.domain.IterableModule import Iterable

class MyList:
    def __init__(self):
        '''
        Initialize the data field with an empty list
        '''
        self._repo = Iterable([])

    def getAll(self):
        '''
        :return: the list of elements
        '''
        if len(self._repo) == 0:
            raise RepositoryException("GetAll: Empty Repository!")
        return self._repo

    def getElement(self, index):
        '''
        :return: the element at index
        Raises a RepositoryException if the index is invalid.
        '''
        if index < 0 or index >= len(self._repo):
            raise RepositoryException("GetElement: Invalid item position")
        return self._repo[index]

    def getElementById(self, id):
        '''
        :return: the element with id
        Raises a RepositoryException if the index is invalid.
        '''
        for e in self._repo:
            if e.getId() == id:
                return e
        raise RepositoryException("GetElementById: Item not found")

    def getTop(self):
        '''
        :return: the element on top of the list
        Raises a RepositoryException if the list is empty.
        '''
        if len(self._repo) == 0:
            raise RepositoryException("GetTop: Empty Repository!")
        return self._repo[len(self._repo)-1]

    def setElement(self, index, object):
        '''
        :param index: element to set
        :param object: value to set to
        Set the element at index to object
        '''
        if index < 0 or index >= len(self._repo):
            raise RepositoryException("SetElement: Invalid item position")
        self._repo[index] = object

    def setElementById(self, id, object):
        '''
        :param id: element to set, having the given id
        :param object: value to set to
        Set the element at index to object
        '''
        catch = True
        index = 0
        while index < len(self._repo):
            if self._repo[index].getId() == id:
                self._repo[index] = object
                catch = False
            index += 1
        if catch is True:
            raise RepositoryException("SetElementById: Item not found")


    def setRepo(self, newRepo):
        '''
        :param newRepo: value for repo
        Sets the repo field to newRepo
        '''
        self._repo = newRepo

    def addElement(self, element):
        '''
        :param element: to append
        Appends an element to repo
        '''
        self._repo.append(element)

    def insertElement(self, element, position):
        '''
        :param element: to insert
        Inserts element on position in the repo
        '''
        if position < 0:
            raise RepositoryException("InsertElement: Invalid item position")
        if position > len(self._repo):
            self._repo.append(element)
        else:
            self._repo.insert(position, element)

    def insertElementById(self, element, id):
        '''
        :param element: to insert
        :param id: id of an element in the list
        Inserts element after a given id
        '''
        if id < 0:
            raise RepositoryException("InsertElementById: Invalid item position")
        if id > self._repo[len(self._repo)-1].getId():
            self._repo.append(element)
        else:
            index = 0
            while index < len(self._repo):
                if self._repo[index].getId() > id:
                    self._repo.insert(index, element)
                    break
                index += 1

    def removeElement(self, index):
        '''
        :param index: of the element to remove
        :return: the removed element
        Raises a RepositoryException if the index is invalid.
        '''
        if index < 0 or index >= len(self._repo):
            raise RepositoryException("RemoveElement: Invalid item position")
        return self._repo.pop(index)

    def removeElementById(self, id):
        '''
        :param id: of the element to remove
        :return: the removed element
        Raises a RepositoryException if the id is invalid.
        '''
        i = 0
        while i < len(self._repo):
            if self._repo[i].getId() == id:
                ret = self._repo[i]
                self._repo.pop(i)
                return ret
            i += 1
        raise RepositoryException("RemoveElementById: Item not found")

    def removeAll(self):
        '''
        :return: clears the repo field
        '''
        self._repo.clear()


    def swapElements(self, index1, index2):
        '''
        Swap the values at index1 and index2
        '''
        if index1 < 0 or index1 >= len(self._repo) or index2 < 0 or index2 >= len(self._repo):
            raise RepositoryException("SwapElements: Invalid item position")
        self._repo[index1], self._repo[index2] = self._repo[index2], self._repo[index1]

    def swapElementsById(self, id1, id2):
        '''
        Swap the values of the elements having id1 and id2
        '''
        '''i = 0
        while i < len(self._repo):
            if self._repo[i].getId() == id1:
                j = 0
                while j < len(self._repo):
                    if self._repo[j].getId() == id2:
                        self.setElementById(id1, self.getElementById(id2))
                        aux = deepcopy(self.getElementById(id1))
                        self.setElementById(id2, aux)'''
        for e1 in self._repo:
            if e1.getId() == id1:
                for e2 in self._repo:
                    if e2.getId() == id2:
                        self.setElementById(id1, self.getElementById(id2))
                        aux = deepcopy(self.getElementById(id1))
                        self.setElementById(id2, aux)
        raise RepositoryException("SwapElementsById: Item(s) not found")

    def newSortedByList(self, list):
        '''
        :param list: a list of values that represent a given property for each element of the repo
        :return: a copy of repo sorted in descending order by list
        '''
        newList = deepcopy(self)
        i = 0
        while i < len(newList._repo):
            j = i + 1
            while j < len(newList._repo):
                if list[i] < list[j]:
                    list[i], list[j] = list[j], list[i]
                    newList._repo[i], newList._repo[j] = newList._repo[j], newList._repo[i]
                j += 1
            i += 1
        return newList

    @staticmethod
    def partialStrMatch(str1, str2):
        '''
        :param str1: String
        :param str2: String
        :return: True if str1 and str2 partially match(one of them contains the other);
        False otherwise
        '''
        if str1 == str2:
            return True
        i = 0
        while i < len(str1) and i < len(str2):
            if str1[i] in str2:
                i += 1
            else:
                return False
        return True

    def __len__(self):
        '''
        :return: the length of the data list
        '''
        return len(self._repo)

    def __str__(self):
        '''
        :return: __str__ function of each element
        '''
        aString = ''
        for element in self._repo:
            aString += str(element) + '\n'
        return aString

    def __getitem__(self, index):
        '''
        :param index: integer
        :return: the element on postion index from the repo
        '''
        return self._repo[index]

    def __setitem__(self, index, value):
        '''
        :param index: integer
        :param value: to set to
        Sets the element on index to value.
        '''
        self._repo[index] = value

class RepositoryException(Exception):
    def __init__(self, message):
        self.__message = message

    def __str__(self):
        return self.__message

