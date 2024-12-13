#ifndef _ASCNAMECOMPARATOR_H
#define _ASCNAMECOMPARATOR_H

#pragma once

#include "Comparator.h"

template<typename T> class AscNameComparator: public Comparator<T>
{
public:
    /**
     * Compares the the name field of 2 items
     * @param item1: T
     * @param item2: T
     * @return boolean
     *  True if item1 > item2, false otherwise
     */
    bool compare(const T item1, const T item2)
    {
        return (item1.getName() > item2.getName());
    }
};

#endif //_ASCNAMECOMPARATOR_H
