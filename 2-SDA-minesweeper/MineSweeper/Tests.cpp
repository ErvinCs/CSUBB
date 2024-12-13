#include "Tests.h"

#include <iostream>

void Tests::testMine()
{
	Mine mine{ 1, 2 };

	assert(mine.toString() == "Mine={x=1, y=2, marked=0}");

	assert(mine.getX() == 1);
	assert(mine.getY() == 2);
	assert(mine.isMarked() == false);

	mine.setMarked(true);
	mine.setX(10);
	mine.setY(20);

	assert(mine.getX() == 10);
	assert(mine.getY() == 20);
	assert(mine.isMarked() == true);

}

void Tests::testHashTable()
{
	HashTable<int, Mine> table = HashTable<int, Mine>();
	Mine mine1{ 1, 2 };
	Mine mine2{ 3, 4 };
	Mine mine3{ 5, 6 };
	Mine mine4{ 9, 10 };

	

	table.insert(1, mine1);
	table.insert(24, mine2);
	table.insert(2, mine3);
	assert(table.getSize() == 3);

	table.insert(47, mine4);
	assert(table.getSize() == 4);

	assert(table.getKey(mine3), 2);

	assert(table.getValue(47) == mine4);
	assert(table.getValue(1) == mine1);

	assert(table.removeKeyValue(24, mine2) == true);
	assert(table.removeKeyValue(24, mine2) == false);
	assert(table.removeKeyValue(7, mine2) == false);
	assert(table.removeKeyValue(2, mine3) == true);
	assert(table.getSize() == 2);

	assert(table.update(1, mine1, mine2) == true);
	assert(table.update(5, mine1, mine2) == false);
	assert(table.update(1, mine4, mine2) == true);

	
}

void Tests::testSparseMatrix()
{
	SparseMatrix matrix = SparseMatrix(5, 5);

	assert(matrix.columnNr() == 5);
	assert(matrix.lineNr() == 5);

	Mine mine;
	bool getElemTest = false;
	try {
		mine = matrix.getElement(6, 6);
	} catch (InvalidPositionException ex)
	{
		getElemTest = true;
	}
	assert(getElemTest);
	getElemTest = false;
	try {
		mine = matrix.getElement(-1, -1);
	}
	catch (InvalidPositionException ex)
	{
		getElemTest = true;
	}
	assert(getElemTest);
	getElemTest = false;
	mine = matrix.getElement(3, 2);
	assert(mine.getX() == -1 && mine.getY() == -1 && mine.isMarked() == false);

	mine.setMarked(true);
	mine.setX(1);
	mine.setY(1);

	Mine emptyMine = Mine{};
	matrix.modify(1, 1, mine);
	assert(matrix.getElement(1, 1) == mine);
	Mine newMine{ 2, 4 };
	matrix.modify(1, 1, newMine);
	assert(matrix.getElement(1, 1) == newMine);
	matrix.modify(1, 1, emptyMine);
	assert(matrix.getElement(1, 1) == emptyMine);
	
	try {
		matrix.modify(-1, -1, emptyMine);
	}
	catch (InvalidPositionException ex)
	{
		getElemTest = true;
	}
	assert(getElemTest);

	Mine aMine = Mine{ 2, 3 };
	matrix.modify(2, 3, aMine);

	std::cout << matrix.getPositionSum(aMine);

	int something;
	std::cin >> something;

}


void Tests::testAll()
{
	testMine();
	testHashTable();
	testSparseMatrix();
}