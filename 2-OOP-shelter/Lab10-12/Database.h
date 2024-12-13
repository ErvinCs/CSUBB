#ifndef _DATABASE_H
#define _DATABASE_H

#pragma once

#include <iostream>
#include <fstream>
#include <algorithm>
#include <vector>
#include <iterator>

#include "DynamicVector.h"
#include "RepoException.h"
#include "Dog.h"

template<typename T> class Database
{
protected:
    std::vector<T> repo;
    int current = 0;
    std::string filename;
public:

    /**
     * Default constructor
     */
    Database() noexcept {}

    /**
     * Copy constructor
     * @param other: Database<T>
     */
    Database(const Database<T>& other)
    {
        this->current = 0;
        this->filename = other.filename;
        this->repo = other.repo;
    }

    /**
     * Reads the contents of the specified file into the repo
     * @param filename: string
     */
    explicit Database(const std::string& filename)
    {
        this->current = 0;
        this->filename = filename;
        //this->readFile();
    }

    /**
     * Get the path fo the file
     * @return: string
     */
    const std::string& getFilename() const {
		return this->filename;
    }

    /**
     * Set the path of the file
     * @param filename: string
     */
    void setFilename(const std::string& filename) {
        this->filename = filename;
    }

    /**
     * @return: vector<T>
     * @throw: RepoException
     */
    std::vector<T> getVector()	//*
    {
        //if (repo.size() == 0)
        //    throw RepoException("Empty repository.\n");
        return this->repo;	//&
    }

    /**
     * @param index: the position of an item in the array
     * @return: T: the item at index
     * @throw: RepoException
     */
    T getItem(const int index) const
    {
        //if (repo.size() == 0)
        //    throw RepoException("Empty repository.\n");
        return repo[index];
    }

    /**
     * Adds an item to the repo
     * @tparam T
     * @param item: T
     * @throw: RepoException
     */
    void add(const T& item)
    {
        if (std::find(std::begin(repo), std::end(repo), item) == std::end(repo))
        {
            repo.push_back(item);
            this->writeFile();
        } 
		/*else
        {
            throw RepoException("Item already exists!");
        }*/
    }

    /**
     * Removes an item from the repo
     * @param id
     * @return: boolean
     *  True if an item was successfully removed; false otherwise
     * @throw: RepoException
     */
    bool remove(const int id)
    {
        //if (repo.size() == 0)
        //    throw RepoException("Empty repository.\n");

        int i = 0;
        for(T item : repo)
        {
            if (item.getId() == id)
            {
                repo.erase(std::begin(repo) + i);
                this->writeFile();
                return true;
            }
            i++;
        }
        return false;
    }

    /**
     * Updates the fields of an item in the repo
     * @tparam T
     * @param id: id of the item to update
     * @param elem: T
     * @return: boolean
     *  True if an item was successfully updated; false otherwise
     * @throw: RepoException
     */
    bool update(const int id, const T elem)
    {
        //if (repo.size() == 0)
        //    throw RepoException("Empty repository.\n");

        bool isPresent = (std::find(std::begin(repo), std::end(repo), elem) != std::end(repo));

        if (!isPresent)
            return false;

        for(int i = 0; i < repo.size(); i++)
        {
            if (repo[i].getId() == id)
            {
                repo[i].setAge(repo[i].getAge());
                repo[i].setName(repo[i].getName());
                repo[i].setPhotoLink(repo[i].getPhotoLink());
                repo[i].setBreed(repo[i].getBreed());
            }
        }

        this->writeFile();
        return true;
    }

	//
	void updateName(int pos, const int& id, const std::string& name)
	{
		Dog item = this->findById(id);
		Dog newDog = Dog(item.getBreed(), name, item.getAge(), item.getPhotoLink());
		newDog.setId(id);
		this->repo[pos] = newDog;
	}

	void updateBreed(int pos, const int& id, const std::string& breed)
	{
		Dog item = this->findById(id);
		Dog newDog = Dog(breed, item.getName(), item.getAge(), item.getPhotoLink());
		newDog.setId(id);
		this->repo[pos] = newDog;
	}

	void updateAge(int pos, const int& id, const int& age)
	{
		Dog item = this->findById(id);
		Dog newDog = Dog(item.getBreed(), item.getName(), age, item.getPhotoLink());
		newDog.setId(id);
		this->repo[pos] = newDog;
	}

	void updateLink(int pos, const int& id, const std::string& link)
	{
		Dog item = this->findById(id);
		Dog newDog = Dog(item.getBreed(), item.getName(), item.getAge(), link);
		newDog.setId(id);
		this->repo[pos] = newDog;
	}

	T findById(const int& id)
	{
		for (auto item : this->repo)
			if (item.getId() == id)
				return item;
		return T{};
	}

	int getPosById(const int id)
	{
		for (int i = 0; i < this->repo.size(); i++)
		{
			if (this->repo[i].getId() == id)
				return i;
		}
		return -1;
	}
	//

    /**
    * @return: int: the zie of the repository
    */
    int getSize() const
    {
        return (int)repo.size();
    }

    std::string toString()
    {
        std::string output;
        for(T item : repo)
        {
            output += item.toString();
        }
        return output;
    }

    /**
     * Iterator
     * @return: the current item in the repo
     */
    T getCurrent()
    {
        if (this->current == this->getSize())
            this->current = 0;
        if (this->getSize() > 0)
            return repo.at(current);

        return T {};
    }

    /**
     * Iterator
     * @return: the next item in the repo
     */
    bool next()
    {
        if (this->getSize() == 0)
            return false;
        this->current += 1;
        return true;
    }

    /**
     * Iterator
     * @return: the previous item in the repo
     */
    bool previous()
    {
        if (this->getSize() == 0)
            return false;
        this->current -= 1;
        return true;
    }

    virtual void display()
    {
		this->getCurrent().open();
    }


    /**
     * Read and load in the repo the contents of a file
     */
    virtual void readFile()
    {
        std::ifstream file;
        file.open(this->filename, std::ios::in);

        T item;
        while (file.good())
        {
            file >> item;
            idGen++;
            this->repo.push_back(item);
        }

        file.close();
    }

    /**
     * Write to a file the contents of the repo
     */
    virtual void writeFile()
    {
        std::ofstream file;
        file.open(this->filename, std::ios::out);

        for (T item : this->repo)
            file << item;

        file.close();
    }
};

#endif //_DATABASE_H