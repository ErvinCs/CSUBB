from src.repository.MyList import MyList

class MyController:
    def __init__(self, repo ):
        '''
        :param repo: reference to the current repository
        Initialize the undo field with an empty list
        '''
        self._repo = repo

    def setFlag(self, flag):
        self.fileFlag = flag

    def setRepo(self, newRepo):
        '''
        :param newRepo: value for repo
        Sets the repo field to newRepo
        '''
        self._repo.setRepo(newRepo)

    def setElement(self, index, object):
        '''
        :param index: element to set
        :param object: value to set to
        Set the element at index to object
        '''
        self._repo.setElement(index, object)

    def setElementById(self, id, object):
        '''
        :param id: element to set, having the given id
        :param object: value to set to
        Set the element at index to object
        '''
        self._repo.setElementById(id, object)

    def addElement(self, elem):
        '''
        :param elem: to add to repo
        Update the undo field
        '''
        self._repo.addElement(elem)

    def insertElement(self, element, position):
        '''
        :param element: to insert
        Inserts element on position in the repo
        '''
        self._repo.insertElement(position, element)

    def insertElementById(self, element, id):
        '''
        :param element: to insert
        :param id: id of an element in the list
        Inserts element after a given id
        '''
        self._repo.insertElementById(element, id)

    def removeElement(self, index):
        '''
        :param index: index of the element to remove
        Update the undo field
        '''
        x = self._repo.removeElement(index)
        return x

    def removeElementById(self, id):
        '''
        :param id: of the element to remove
        :return: the removed element
        Raises a RepositoryException if the id is invalid.
        '''
        x = self._repo.removeElementById(id)
        return x

    def removeAll(self):
        '''
        Remove all the elements from repo
        Update the undo field
        '''
        self._repo.removeAll()

    def getRepo(self):
        '''
        :return: repo
        '''
        return self._repo

    def getElement(self, index):
        '''
        :return: the element at index
        '''
        self._repo.getElement(index)

    def getElementById(self, id):
        '''
        :return: the element with id
        Raises a RepositoryException if the index is invalid.
        '''
        return self._repo.getElementById(id)

    def swapElements(self, index1, index2):
        '''
        Swap the values at index1 and index2
        '''
        self._repo.swapElements(index1, index2)

    def swapElementsById(self, id1, id2):
        '''
        Swap the values of the elements having id1 and id2
        '''
        self._repo.swapElementsById(id1, id2)

    def listRepo(self):
        '''
        Prints the elements of repo
        '''
        stringList = str(self._repo)
        if len(stringList) == 0:
            print("Empty list!")
        print(stringList)

class ControllerException(Exception):
    def __init__(self, message):
        self.__message = message

    def __str__(self):
        return self.__message