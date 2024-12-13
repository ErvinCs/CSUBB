#pragma once

#include "Database.h"
#include "DogException.h"
#include "ControllerException.h"

template<typename T> class GuiController
{
private:
	Database<T>* repo;
	Database<T>* adopted;
public:

	explicit GuiController(Database<T>* r, Database<T>* a) : repo(r), adopted(a) {}
	~GuiController()
	{
		delete repo;
		delete adopted;
	}

	Database<T>* getRepo()
	{
		return repo;
	}

	Database<T>* getAdopted()
	{
		return adopted;
	}

	int getPosByIdRepo(const int id)
	{
		return this->repo->getPosById(id);
	}

	int getPosByIdAdopted(const int id)
	{
		return this->adopted->getPosById(id);
	}

	T getItemRepoById(const int id)
	{
		return this->repo->findById(id);
	}

	T getItemAdoptedById(const int id)
	{
		return this->adopted->findById(id);
	}
	T getItemRepo(const int index) const
	{
		return repo->getItem(index);
	}

	T getItemAdopted(const int index) const
	{
		return adopted->getItem(index);
	}

	int getSizeRepo()
	{
		return repo->getSize();
	}

	int getSizeAdopted()
	{
		return adopted->getSize();
	}

	std::vector<T> getAllRepo()
	{
		return this->repo->getVector();
	}

	std::vector<T> getAllAdopted()
	{
		return this->adopted->getVector();
	}

	void add(const T& dog)
	{
		try {
			this->repo->add(dog);
		}
		catch (DogException ex)
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

	void addAdopted(const T& dog)
	{
		try {
			this->adopted->add(dog);
		}
		catch (DogException ex)
		{
			throw ControllerException(ex.what());
		}
	}

	T findByIdRepo(const int& id)
	{
		return this->repo->findById(id);
	}

	T findByIdAdopted(const int& id)
	{
		return this->adopted->findById(id);
	}

	bool removeAdopted(const int id)
	{
		return this->adopted->remove(id);
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

	T getCurrentAdopted()
	{
		return this->adopted->getCurrent();
	}

	bool nextAdopted()
	{
		return this->adopted->next();
	}

	bool previousAdopted()
	{
		return this->adopted->previous();
	}

	void display()
	{
		this->repo->display();
	}

	void saveAdoptedTo(const std::string& filename)
	{
		this->adopted->setFilename(filename);
		this->adopted->writeFile();
	}

	std::vector<T> filterName(const std::string& name)
	{
		std::vector<T> items = this->repo->getVector();
		int n = count_if(items.begin(), items.end(), [name](const Dog& dog)
		{
			if (dog.getName().find(name) != std::string::npos)
				return true;
		});

		std::vector<Dog> returnItems(n);
		copy_if(items.begin(), items.end(), returnItems.begin(), [name](const Dog& dog)
		{
			if (dog.getName().find(name) != std::string::npos)
				return true;
		});

		return returnItems;
	}

	void updateName(int pos, const int& id, const std::string& name)
	{
		this->repo->updateName(pos, id, name);
	}

	void updateBreed(int pos, const int& id, const std::string& breed)
	{
		this->repo->updateBreed(pos, id, breed);
	}

	void updateAge(int pos, const int& id, const int& age)
	{
		this->repo->updateAge(pos, id, age);
	}

	void updateLink(int pos, const int& id, const std::string& link)
	{
		this->repo->updateLink(pos, id, link);
	}
};
