#ifndef _DYNAMICVECTOR_H
#define _DYNAMICVECTOR_H

#pragma once

#include <iterator>
#include <iostream>

/**
 * Deprecated
 */
template<typename T> class DynamicVector
{
public:
    T* elements;
private:
    int capacity;
    int size;

public:

    /**
     * Default constructor
     */
    DynamicVector() noexcept
    {
        this->size = 0;
        this->capacity = 1;
        this->elements = new T[capacity];
    }

    /**
     * Copy constructor
     * @param other: DynamicVector<T>
     */
    DynamicVector(const DynamicVector<T>& other)
    {
        this->size = other.size;
        this->capacity = other.capacity;
        this->elements = new T[this->capacity];
        for(int i=0; i<this->size; i++)
            this->elements[i] = other.elements[i];
    }

    /**
     * Default destructor
     */
    ~DynamicVector()
    {
        delete[] this->elements;
    }

    /**
     * Returns the size of the array
     * @return: int
     */
    int getSize() const
    {
        return this->size;
    }

    /**
     * Returns the capacity of the array
     * @return: int
     */
    int getCapacity() const
    {
        return this->capacity;
    }

    /**
     * Returns a pointer to all the elements of the array
     * @return: T*
     */
    T* getAll() const
    {
        return this->elements;
    }

    /**
     * Returns the item at index
     * @param index: int
     * @return: T
     */
    T getItem(const int index) const
    {
        //throw exception if there's nothing at index
        return this->elements[index];
    }

    /**
     * Append an item in the array
     * @param item: T&
     */
    void add(const T& item)
    {
        if (this->size == this->capacity)
            this->resize();

        bool valid = true;
        for(int i = 0; i < this->size; i++)
        {
            if (this->getItem(i) == item)
                valid = false;
        }

        if (valid)
        {
            this->elements[this->size] = item;
            this->size += 1;
        }
    }


    /**
     * Removes the item with id from the array
     * @param id: int
     * @return: boolean
     *  True if an item was removed; false otherwise
     */
    bool remove(const int id)
    {
        for (int i = 0; i < this->size; i++)
        {
            if (this->elements[i].getId() == id)
            {
                this->size -= 1;
                for (int j = i; j < this->size; j++)
                {
                    this->elements[j] = this->elements[j + 1];
                }
                return true;
            }
        }
        return false;
    }

    /**
     * Clears the array
     */
    void removeAll()
    {
        this->size = 0;
        this->capacity = 1;
    }

    /**
     * Updates the fields of an item in the array
     * @tparam T
     * @param id: id of the item to update
     * @param elem: T
     * @return: boolean
     *  True if an item was successfully updated; false otherwise
     */
    bool update(const int id, const T elem)
    {
        for (int i = 0; i < this->size; i++)
        {
            if (this->elements[i].getId() == id)
            {
                this->elements[i] = elem;
                return true;
            }
        }
        return false;
    }

    /**
     * Appends the all the items of other in the array
     * @param other: DynamicVector<T>&
     * @return: this DynamicVector<T>&
     */
    DynamicVector<T>& operator+ (const DynamicVector<T>& other)
    {
        for(int i = 0; i < other.size; i++)
        {
            bool in = false;
            for(int j = 0; j < this->size; j++)
            {
                if (other.getItem(i) == this->getItem(j))
                {
                    in = true;
                    break;
                }
            }
            if (!in)
                this->add(other.elements[i]);
        }

        return *this;
    }

    /**
     * Appends an item in the array
     * @param other: T
     * @return: this DynamicVector<T>&
     */
    DynamicVector<T>& operator+ (const T& other)
    {
        for(int i = 0; i < this->size; i++)
        {
            if (this->getItem(i) == other)
                return *this;
        }
        this->add(other);
        return *this;
    }

    /**
     * Appends an item in other
     * @param item: T&
     * @param other: DynamicVector<T>&
     * @return: DynamicVector<T>&
     */
    friend DynamicVector<T>& operator+ (const T& item, DynamicVector<T>& other)
    {
        for(int i = 0; i < other.size; i++)
        {
            if (other.getItem(i) == item)
                return other;
        }
        other.add(item);
        return other;
    }

    /**
     * Assignment operator
     * @param v: DynamicVector<T>
     * @return: : DynamicVector<T>; this
     */
    DynamicVector<T>& operator= (const DynamicVector<T>& v)
    {
        if (this == &v)
            return *this;

        this->size = v.size;
        this->capacity = v.capacity;
        delete[] this->elements;
        this->elements = new T[this->capacity];
        for(int i = 0; i < this->size; i++)
            this->elements[i] = v.elements[i];

        return *this;
    }

    /**
     * Equality operator
     * @param v: DynamicVector<T>
     * @return: boolean
     *  True if the 2 items are identical; false otherwise
     */
    bool operator== (const DynamicVector<T>& v)
    {
        if(this->size != v.getSize())
            return false;
        for(int i = 0; i < v.size; i++)
        {
            if(v.getItem(i) != this->getItem(i))
                return false;
        }
        return true;
    }

private:
    /**
     * Doubles the array's capacity once it is full
     */
    void resize()
    {
        this->capacity *= 2;
        T* newElements = new T[capacity];
        for (int i = 0; i < this->size; i++)
            newElements[i] = this->elements[i];

        delete[] this->elements;
        this->elements = newElements;
    }
};

#endif //_DYNAMICVECTOR_H