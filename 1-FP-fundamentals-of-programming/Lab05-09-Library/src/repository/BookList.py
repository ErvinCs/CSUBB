from src.repository.MyList import MyList
from src.repository.MyList import RepositoryException
from src.domain.Book import Book
from copy import deepcopy

class BookList(MyList):
    def __init__(self):
        '''
        Initialize the list with an empty list
        '''
        MyList.__init__(self)
        #name mangling

    def __contains__(self, bookId):
        '''
        :param bookId: the Id of the book to check
        :return: True if the list contains a book having bookId; False otherwise
        '''
        if len(self._repo) == 0:
            raise RepositoryException("In: Empty Repository!")
        for i in range(0, len(self._repo)):
            if self._repo[i].getId() == bookId:
                return True
        return False

    def getBookById(self, bookId):
        '''
        :param bookId: an integer
        :return: the first book in the list with the given bookId
        '''
        if len(self._repo) == 0:
            raise RepositoryException("GetBookById: Empty Repository!")
        i = 0
        while i < len(self._repo):
            if bookId == self._repo[i].getId():
                return self._repo[i]
            i += 1
        raise RepositoryException("GetBookById: Invalid Id!")

    def getAuthorList(self):
        '''
        :return: the list of authors
        '''
        if len(self._repo) == 0:
            raise RepositoryException("GetAuthorList: Empty Repository!")
        authorList = MyList()
        for b in self._repo:
            if b.getAuthor() not in authorList:
                authorList.addElement(b.getAuthor())
        return authorList

    def update(self, elemIndex, title, description, author):
        '''
        :param elemIndex: of the element to update
        :param title, description, author: fields to be assigned at that index
        Raises a RepositoryException if the index is invalid.
        '''
        if elemIndex < 0 or elemIndex >= len(self._repo):
            raise RepositoryException("Update: Invalid item position")
        self._repo[elemIndex].setTitle(title)
        self._repo[elemIndex].setDescription(description)
        self._repo[elemIndex].setAuthor(author)

    def updateById(self, elemId, title, description, author):
        '''
        :param elemId: id of the element to update
        :param title, description, author: fields to be assigned at that index
        Raises a RepositoryException if the id is invalid.
        '''
        print("in")
        catch = True
        index = 0
        while index < len(self._repo):
            if self._repo[index].getId() == elemId:
                self._repo[index].setTitle(title)
                self._repo[index].setDescription(description)
                self._repo[index].setAuthor(author)
                catch = False
            index += 1
        self._repo.resetIndex()
        if catch is True:
            raise RepositoryException("UpdateById: Item not found")

    def searchBook(self, id, title, description, author):
        '''
        :param id: integer
        :param title, description, author:
        :return: a list of books matching the given parameters
        '''
        blist = deepcopy(self)
        i = 0
        while i < len(blist):
            removed = False
            if id is not None:
                x = blist.getElement(i).getId()
                if x != id:
                    blist.removeElement(i)
                    i -= 1
                    removed = True
            if title is not None and removed is False:
                x = blist.getElement(i).getTitle()
                if not MyList.partialStrMatch(x, title):
                    blist.removeElement(i)
                    i -= 1
                    removed = True
            if description is not None and removed is False:
                x = blist.getElement(i).getDescription()
                if not MyList.partialStrMatch(x, description):
                    blist.removeElement(i)
                    i -= 1
                    removed = True
            if author is not None and removed is False:
                x = blist.getElement(i).getAuthor()
                if not MyList.partialStrMatch(x, author):
                    blist.removeElement(i)
                    i -= 1
                    removed = True
            i += 1
        return blist



