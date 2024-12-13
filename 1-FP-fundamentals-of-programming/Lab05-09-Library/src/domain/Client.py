class Client:
    clientIterator = 0  #clientId generator
    def __init__(self, name):
        '''
        Sets clientId using the clientIterator
        :param name: string
        '''
        self.__clientId = Client.clientIterator
        Client.clientIterator += 1
        self.__name = name.strip()

    def getId(self):
        return self.__clientId

    def getName(self):
        return self.__name

    def setName(self, name):
        self.__name = name.strip()

    def __str__(self):
        '''
        :return: a String: 'Client[ID]: Name'
        '''
        aString = 'Client ' + str(self.__clientId) + ': '
        aString += str(self.__name)
        return aString

    def __eq__(self, client):
        '''
        :param client: to compare to
        :return: True if the 2 clients are identical (except for the clientId); False otherwise
        '''
        return self.__name == client.__name
