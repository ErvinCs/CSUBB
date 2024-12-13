#ifndef _ASCAGECOMPARATOR_H
#define _ASCAGECOMPARATOR_H

#pragma once

#include "Comparator.h"

template<typename T> class AscAgeComparator: public Comparator<T>
{
public:
    /**
     * Compares the the age field of 2 items
     * @param item1: T
     * @param item2: T
     * @return boolean
     *  True if item1 > item2, false otherwise
     */
    bool compare(const T item1, const T item2)
    {
        return (item1.getAge() > item2.getAge());
    }
};

#endif //_ASCAGECOMPARATOR_H
