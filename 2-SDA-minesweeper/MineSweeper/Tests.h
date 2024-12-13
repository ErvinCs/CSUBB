#pragma once

#include <cassert>

#include "Mine.h"
#include "HashTable.h"
#include "SparseMatrix.h"
#include "InvalidPositionException.h"

class Tests
{
public:
	static void testMine();
	static void testHashTable();
	static void testSparseMatrix();

	static void testAll();
};