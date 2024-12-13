#pragma once

#include <iostream>
#include <memory>
#include <future>
#include <thread>
#include <utility>
#include <algorithm>
#include <vector>

#include "Matrix.h"


class AsyncMatrix : public Matrix
{
public:

	AsyncMatrix() : Matrix() { }
	AsyncMatrix(int rows, int columns) : Matrix(rows, columns) { }
	AsyncMatrix(int rows, int columns, int** data) :	Matrix(rows, columns, data) { }
	~AsyncMatrix() = default;

	AsyncMatrix add(AsyncMatrix& other);
	AsyncMatrix multiply(AsyncMatrix& other);
	AsyncMatrix addNoThreads(AsyncMatrix& other);
	AsyncMatrix multiplyNoThreads(AsyncMatrix& other);
};

