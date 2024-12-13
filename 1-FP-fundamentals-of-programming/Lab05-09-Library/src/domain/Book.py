class Book:
    bookIterator = 0  #bookId generator
    def __init__(self, title, description, author):
        '''
        Sets bookId using the bookIterator
        :param title, description, author: strings
        '''
        self.__bookId = Book.bookIterator
        Book.bookIterator += 1
        self.__title = title.strip()
        self.__description = description.strip()
        self.__author = author.strip()

    def getId(self):
        return self.__bookId

    def getTitle(self):
        return self.__title

    def getDescription(self):
        return self.__description

    def getAuthor(self):
        return self.__author

    def setTitle(self, title):
        self.__title = title.strip()

    def setDescription(self, description):
        self.__description = description.strip()

    def setAuthor(self, author):
        self.__author = author.strip()

    def __str__(self):
        '''
        :return: a String: 'Book[ID]: Title - Author - Descrpition'
        '''
        aString = 'Book ' + str(self.__bookId) + ': '
        aString += str(self.__title) + ' - '
        aString += str(self.__author) + ' - '
        aString += str(self.__description)
        return aString

    # Not used
    def strToStore(self):
        '''
        :return: a String: 'Title - Author - Description'
        '''
        #aString = 'Book ' + str(self.__bookId) + ': '
        aString = str(self.__title) + ' - '
        aString += str(self.__author) + ' - '
        aString += str(self.__description)
        return aString

    def __eq__(self, book):
        '''
        :param book: to compare to
        :return: True if the 2 books are identical (except for the bookId); False otherwise
        '''
        return self.__title == book.__title and self.__author == book.__author and self.__description == book.__description
