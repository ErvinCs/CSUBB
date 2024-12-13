from src.repository.MyList import MyList
from src.repository.MyList import RepositoryException
from src.domain.Client import Client
from copy import deepcopy
import unittest

class ClientList(MyList):
    def __init__(self):
        MyList.__init__(self)
        #name mangling

    def __contains__(self, clientId):
        '''
        :param clientId: the Id of the client to check
        :return: True if the list contains a client having clienId; False otherwise
        '''
        if len(self._repo) == 0:
            raise RepositoryException("Empty Repository!")
        for i in range(0, len(self._repo)):
            if self._repo[i].getId() == clientId:
                return True
        return False

    def update(self, elemIndex, name):
        '''
        :param elemIndex: of the element to update
        :param newElem: value to be assigned at that index
        Raises a RepositoryException if the index is invalid.
        '''
        if elemIndex < 0 or elemIndex >= len(self._repo):
            raise RepositoryException("Invalid item position")
        self._repo[elemIndex].setName(name)

    def updateById(self, elemId, name):
        '''
        :param elemId: id of the element to update
        :param title, description, author: fields to be assigned at that index
        Raises a RepositoryException if the id is invalid.
        '''
        catch = True
        index = 0
        while index < len(self._repo):
            if self._repo[index].getId() == elemId:
                self._repo[index].setName(name)
                catch = False
            index += 1
        self._repo.resetIndex()
        if catch is True:
            raise RepositoryException("UpdateById: Item not found")

    def searchClient(self, id, name):
        '''
        :param id: integer
        :param name:
        :return: a list of clients matching the given parameters
        '''
        list = deepcopy(self)
        i = 0
        while i < len(list):
            removed = False
            if id is not None:
                x = list.getElement(i).getId()
                if x != id:
                    list.removeElement(i)
                    i -= 1
                    removed = True
            if name is not None and removed is False:
                x = list.getElement(i).getName()
                if not MyList.partialStrMatch(x, name):
                    list.removeElement(i)
                    i -= 1
                    removed = True
            i += 1
        return list


