#pragma once

template<typename T> class Iterator
{
public:
	virtual Iterator<T>* getIterator() = 0;

	virtual T getCurrent() = 0;

	virtual bool valid() = 0;

	virtual void next() = 0;
};
