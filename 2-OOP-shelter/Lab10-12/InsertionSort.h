#ifndef _INSERTIONSORT_H
#define _INSERTIONSORT_H

#pragma once

#include <algorithm>
#include "DynamicVector.h"
#include "Comparator.h"

/**
 * Sorts a vector, implementing the ordering of a comparator
 * @tparam T
 * @param v: DynamicVector<T>
 * @param c: Comparator
 */
template <class T> void insertionSort(DynamicVector<T>* v, Comparator<T>& c)
{
    for(int i = 0; i < v->getSize(); i++)
    {
        for(int j = i+1; j < v->getSize(); j++)
        {
            if (c.compare(v->getItem(i), v->getItem(j)))
                std::swap(v->elements[i], v->elements[j]);
        }
    }
}

/**
* Sorts a vector, implementing the ordering of a comparator
* @tparam T
* @param v: Database<T>
* @param c: Comparator
*/
template <class T> void insertionSort(Database<T>* db, Comparator<T>& c)
{
	for (int i = 0; i < db->getSize; i++)
	{
		for (int j = i + 1; j < db->getSize(); j++)
		{
			if (c.compare(db->getItem(i), db->getItem(j)))
				std::swap(db->getVector[i], db->getVector[j])
		}
	}
}

#endif //_INSERTIONSORT_H
