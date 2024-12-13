#include "SparseMatrix.h"

SparseMatrix::SparseMatrix(const int& noLines, const int& noColumns)
{
	this->noLines = noLines;
	this->noColumns = noColumns;
}


void SparseMatrix::modify(const int& line, const int& column, Mine value)
{
	if (line < 0 || line > this->noLines || column < 0 || column > this->noColumns)
		throw InvalidPositionException("Invalid position!");
	
	if (map.getValue(line + column * noColumns) == Mine{} && value == Mine{})
		return;
	else if (map.getValue(line + column * noColumns) == Mine{} && value != Mine{})
	{
		this->map.insert(line + column * noColumns, value);
		return;
	}
	else if (map.getValue(line + column * noColumns) != Mine{} && value == Mine{})
	{
		this->map.removeKeyValue(line + column * noColumns, map.getValue(line + column * noColumns));
		return;
	}
	else
		this->map.update(line + column * noColumns, map.getValue(line + column * noColumns), value);
}

int SparseMatrix::lineNr()
{
	return this->noLines;
}

int SparseMatrix::columnNr()
{
	return this->noColumns;
}

Mine SparseMatrix::getElement(const int& line, const int& column)
{
	if (line > this->noLines || line < 0)
		throw InvalidPositionException("Invalid line!");
	if (column > this->noColumns || column < 0)
		throw InvalidPositionException("Invalid column!");

	Mine mine = map.getValue(line + column * noColumns);
	return mine;
}

int SparseMatrix::getPositionSum(const Mine& mine)
{
	return this->map.getKey(mine);
}
