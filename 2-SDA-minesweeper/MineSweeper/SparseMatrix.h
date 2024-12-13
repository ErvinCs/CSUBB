#pragma once

#include "HashTable.h"
#include "Mine.h"
#include "InvalidPositionException.h"
#include <string>

class SparseMatrix
{
private:
	HashTable<int, Mine> map;
	int noColumns;
	int noLines;
	//int noElems;
public:
	/**
	* Creates a new SparseMatrix with noLines lines and noColumns columns
	* @param: noLines - integer > 0
	* @param: noColumns - integer > 0
	*/
	SparseMatrix(const int& noLines, const int& noColumns);

	/**
	* Copy constructor
	*/
	//SparseMatrix(const SparseMatrix& other);

	/**
	* Sets the value at the given position to the given value
	* If the 2 values are 0 no change is made
	* If the old value is 0 and the new one is different than 0 it adds a new entry into the table
	* If the old value is different than 0 and the new one is 0 it removes an entry from the table
	* If both values are different than 0 it sets the old value to the new one
	* @param: line - integer > 0 and < noColumns
	* @param: column - integer > 0 and < noLines
	* @param: value - Mine
	*/
	void modify(const int& line, const int& column, Mine value);

	/**
	* Returns the number of lines
	*/
	int lineNr();

	/**
	* Returns the number of columns
	*/
	int columnNr();

	/**
	* Returns the Mine object at position (line, column)
	* @throws: InvalidPositionException
	*	if line < 0 or column < 0 or line > noLines or column > noLines
	*/
	Mine getElement(const int& line, const int& column);

	int getPositionSum(const Mine& mine);
};