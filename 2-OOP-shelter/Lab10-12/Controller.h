#ifndef _CONTROLLER_H
#define _CONTROLLER_H

#pragma once

#include "Database.h"
#include "DogException.h"
#include "ControllerException.h"

template<typename T> class Controller
{
private:
    Database<T> repo;
public:

    Controller(const Database<T>& r) : repo(r) {}



    T getItem(const int index) const
    {
        return repo.getItem(index);
    }

    int getSize()
    {
        return repo.getSize();
    }

	std::vector<T> getAll()
	{
		return this->repo.getVector();
	}

    void add(const T& dog)
    {
        try {
            this->repo.add(dog);
        } catch (DogException ex)
        {
            throw ControllerException(ex.what());
        }
    }

    bool remove(const int id)
    {
        return this->repo.remove(id);
    }

    bool update(const int id, const T elem)
    {
        return this->repo.update(id, elem);
    }

    T getCurrent()
    {
        return this->repo.getCurrent();
    }

    bool next()
    {
        return this->repo.next();
    }

    bool previous()
    {
        return this->repo.previous();
    }

};

#endif //_CONTROLLER_H