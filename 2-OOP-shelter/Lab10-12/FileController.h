#ifndef _FILECONTROLLER_H
#define _FILECONTROLLER_H

#include "Database.h"
#include "DogException.h"
#include "ControllerException.h"

template<typename T> class FileController
{
private:
    Database<T>* repo;
public:

    explicit FileController(Database<T>* r) : repo(r) {}
    ~FileController()
    {
        delete repo;
    }

    T getItem(const int index) const
    {
        return repo->getItem(index);
    }

    int getSize()
    {
        return repo->getSize();
    }

	std::vector<T> getAll()
	{
		return this->repo->getVector();
	}

    void add(const T& dog)
    {
        try {
            this->repo->add(dog);
        } catch (DogException ex)
        {
            throw ControllerException(ex.what());
        }
    }

    bool remove(const int id)
    {
        return this->repo->remove(id);
    }

    bool update(const int id, const T elem)
    {
        return this->repo->update(id, elem);
    }

    T getCurrent()
    {
        return this->repo->getCurrent();
    }

    bool next()
    {
        return this->repo->next();
    }

    bool previous()
    {
        return this->repo->previous();
    }

    void display()
    {
        this->repo->display();
    }

};

#endif //LAB8_9_FILECONTROLLER_H
