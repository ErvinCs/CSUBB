#include "pch.h"
#include "PoolMatrix.h"

PoolMatrix PoolMatrix::add(PoolMatrix& other)
{
	if (this->columns != other.getColumns() || this->rows != other.getRows())
		throw new InvalidMatrixOperation("Cannot perform addition: invalid number of Rows & Columns!");
	PoolMatrix result = PoolMatrix(this->rows, this->columns);

	auto const add = [](const int& a, const int& b, int* values, const int& index) {
		values[index] = a + b;
		return a + b;
	};
	int* values = (int*)malloc((this->getRows() * this->getColumns()) * sizeof(int));

	auto startTime = std::chrono::high_resolution_clock::now();

	boost::thread_group threads;
	for (int i = 0; i < this->getRows(); i++) {
		for (int j = 0; j < this->getColumns(); j++) {
			auto thisData = this->getData()[i][j];
			auto otherData = other.getData()[i][j];
			int index = i * this->getColumns() + j;
			threads.add_thread(new boost::thread(add, thisData, otherData, values, index));
		}
	}
	threads.join_all();

	for (int i = 0; i < this->getRows(); i++) {
		for (int j = 0; j < this->getColumns(); j++) {
			result.data[i][j] = values[i * this->getColumns() + j];
		}
	}

	auto finishTime = std::chrono::high_resolution_clock::now();
	auto executionTime = std::chrono::duration_cast<std::chrono::milliseconds>(finishTime - startTime);

	free(values);
	std::cout << "\nTime: " << std::to_string(executionTime.count()) << " ms";
	return result;
}

PoolMatrix PoolMatrix::multiply(PoolMatrix& other)
{
	if (this->columns != other.getRows())
		throw new InvalidMatrixOperation("Cannot perform multiplication: invalid number of Rows & Columns!");
	PoolMatrix result = PoolMatrix(this->rows, other.getColumns());

	auto mult = [](const int& row, const int& col, int* values, const int& index, PoolMatrix* m1, PoolMatrix& m2) {
		int result = 0;
		for (int i = 0; i < m1->getColumns(); i++)
			result += m1->getData()[i][col] * m2.getData()[row][i];
		values[index] = result;
		return result;
	};
	int* values = (int*)malloc((this->getRows() * this->getColumns()) * sizeof(int));

	auto startTime = std::chrono::high_resolution_clock::now();

	boost::thread_group threads;
	for (int i = 0; i < this->getRows(); i++) {
		for (int j = 0; j < this->getColumns(); j++) {
			int index = i * this->getColumns() + j;
			threads.add_thread(new boost::thread(mult, i, j, values, index, this, other));
		}
	}
	threads.join_all();

	for (int i = 0; i < this->getRows(); i++) {
		for (int j = 0; j < this->getColumns(); j++) {
			result.data[i][j] = values[i * this->getColumns() + j];
		}
	}

	auto finishTime = std::chrono::high_resolution_clock::now();
	auto executionTime = std::chrono::duration_cast<std::chrono::milliseconds>(finishTime - startTime);

	free(values);
	std::cout << "\nTime: " << std::to_string(executionTime.count()) << " ms";
	return result;
}

PoolMatrix PoolMatrix::addNoThreads(PoolMatrix& other)
{
	if (this->columns != other.getColumns() || this->rows != other.getRows())
		throw new InvalidMatrixOperation("Cannot perform addition: invalid number of Rows & Columns!");
	PoolMatrix result = PoolMatrix(this->rows, this->columns);

	auto startTime = std::chrono::high_resolution_clock::now();

	for (int i = 0; i < this->rows; i++) {
		for (int j = 0; j < other.columns; j++) {
			result.data[i][j] = this->getData()[i][j] + other.getData()[i][j];
		}
	}

	auto finishTime = std::chrono::high_resolution_clock::now();
	auto executionTime = std::chrono::duration_cast<std::chrono::milliseconds>(finishTime - startTime);

	std::cout << "\nTime: " << std::to_string(executionTime.count()) << " ms";
	return result;
}

PoolMatrix PoolMatrix::multiplyNoThreads(PoolMatrix& other)
{
	if (this->columns != other.getRows())
		throw new InvalidMatrixOperation("Cannot perform multiplication: invalid number of Rows & Columns!");
	PoolMatrix result = PoolMatrix(this->rows, other.getColumns());

	auto startTime = std::chrono::high_resolution_clock::now();

	for (int i = 0; i < this->rows; i++) {
		for (int j = 0; j < other.columns; j++) {
			result.data[i][j] = 0;
			for (int k = 0; k < this->columns; k++) {
				result.data[i][j] += (this->getData()[i][k] * other.getData()[k][j]);
			}
		}
	}

	auto finishTime = std::chrono::high_resolution_clock::now();
	auto executionTime = std::chrono::duration_cast<std::chrono::milliseconds>(finishTime - startTime);

	std::cout << "\nTime: " << std::to_string(executionTime.count()) << " ms";
	return result;
}