from src.controller.MyController import MyController
from src.domain.Book import Book
from random import randint


class BookController(MyController):
    def __init__(self, repo):
        '''
        Creates a repo and an empty undo list.
        self._undo = []
        self._repo = bookList
        '''
        MyController.__init__(self, repo)

    def readBook(self):
        title = input("Title: ").strip()
        description = input("Descrpition: ").strip()
        author = input("Author: ").strip()
        book = Book(title, description, author)
        self._repo.addElement(book)
        return book

    def updateBook(self, elemIndex):
        title = input("Title: ").strip()
        description = input("Descrpition: ").strip()
        author = input("Author: ").strip()
        self._repo.update(elemIndex, title, description, author)

    def updateById(self, elemId):
        title = input("Title: ").strip()
        description = input("Descrpition: ").strip()
        author = input("Author: ").strip()
        self._repo.updateById(elemId, title, description, author)

    def searchBook(self, id=None, title=None, description=None, author=None):
        id = input("Id: ").strip().lower()
        if id == "":
            id = None
        else:
            id = int(id)
        title = input("Title: ").strip().lower()
        if title == "":
            title = None
        description = input("Description: ").strip().lower()
        if description == "":
            description = None
        author = input("Author: ").strip().lower()
        if author == "":
            author = None
        print(str(self._repo.searchBook(id, title, description, author)))

    def setUpBooks(self):
        self._repo.addElement(Book("book0", "desc0", "author0"))
        self._repo.addElement(Book("book1", "desc1", "author1"))
        self._repo.addElement(Book("book2", "desc1", "author2"))
        self._repo.addElement(Book("book3", "desc3", "author3"))
        self._repo.addElement(Book("book31", "desc4", "author4"))
        self._repo.addElement(Book("book3", "desc5", "author4"))
        self._repo.addElement(Book("book6", "desc6", "author4"))
        self._repo.addElement(Book("book1", "desc1", "author4"))
        self._repo.addElement(Book("book8", "desc8", "author8"))
        self._repo.addElement(Book("book9", "desc9", "author9"))
        for i in range(10,40):
            self._repo.addElement(Book("book" + str(i), "desc" + str(i), "author" + str(i)))