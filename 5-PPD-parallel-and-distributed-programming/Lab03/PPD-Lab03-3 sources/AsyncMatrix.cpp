#include "pch.h"
#include "AsyncMatrix.h"

AsyncMatrix AsyncMatrix::add(AsyncMatrix& other)
{
	if (this->columns != other.getColumns() || this->rows != other.getRows())
		throw new InvalidMatrixOperation("Cannot perform addition: invalid number of Rows & Columns!");
	AsyncMatrix result = AsyncMatrix(this->rows, this->columns);

	std::vector<std::future<int>> futures;

	auto const add = [](const int& a, const int& b, int* values, const int& index) {
		values[index] = a + b;
		return a + b;
	};
	int* values = (int*)malloc((this->getRows() * this->getColumns()) * sizeof(int));

	auto startTime = std::chrono::high_resolution_clock::now();

	for (int i = 0; i < this->rows; i++)
		for (int j = 0; j < this->columns; j++) {
			int index = i * this->getColumns() + j;
			futures.push_back(std::async(add, this->getData()[i][j], other.getData()[i][j], values, index));
		}

	for (int i = 0; i < this->getRows(); i++) {
		for (int j = 0; j < this->getColumns(); j++) {
			if (futures.at(i * this->getColumns() + j).wait_for(std::chrono::seconds(0)) == std::future_status::ready)
				result.data[i][j] = values[i * this->getColumns() + j];
			else
				j -= 1;
		}
	}

	auto finishTime = std::chrono::high_resolution_clock::now();
	auto executionTime = std::chrono::duration_cast<std::chrono::milliseconds>(finishTime - startTime);

	free(values);
	std::cout << "\nTime: " << std::to_string(executionTime.count()) << " ms";
	return result;
}

AsyncMatrix AsyncMatrix::multiply(AsyncMatrix& other)
{
	if (this->columns != other.getRows())
		throw new InvalidMatrixOperation("Cannot perform multiplication: invalid number of Rows & Columns!");
	AsyncMatrix result = AsyncMatrix(this->rows, other.getColumns());

	std::vector<std::future<int>> futures;

	auto mult = [](const int& row, const int& col, int* values, const int& index, AsyncMatrix* m1, AsyncMatrix* m2) {
		int result = 0;
		for (int i = 0; i < m1->getColumns(); i++)
			result += m1->getData()[col][i] * m2->getData()[i][row];
		values[index] = result;
		return result;
	};
	int* values = (int*)malloc((this->getRows() * this->getColumns()) * sizeof(int));

	auto startTime = std::chrono::high_resolution_clock::now();

	for (int i = 0; i < this->rows; i++)
		for (int j = 0; j < this->columns; j++) {
			int index = i * this->getColumns() + j;
			futures.push_back(std::async(mult, i, j, values, index, this, &other));
		}

	for (int i = 0; i < this->getRows(); i++) {
		for (int j = 0; j < this->getColumns(); j++) {
			if (futures.at(i * this->getColumns() + j).wait_for(std::chrono::seconds(0)) == std::future_status::ready)
				result.data[i][j] = values[i * this->getColumns() + j];
			else
				j -= 1;
		}
	}

	auto finishTime = std::chrono::high_resolution_clock::now();
	auto executionTime = std::chrono::duration_cast<std::chrono::milliseconds>(finishTime - startTime);

	free(values);
	std::cout << "\nTime: " << std::to_string(executionTime.count()) << " ms";
	return result;
}

AsyncMatrix AsyncMatrix::addNoThreads(AsyncMatrix& other)
{
	if (this->columns != other.getColumns() || this->rows != other.getRows())
		throw new InvalidMatrixOperation("Cannot perform addition: invalid number of Rows & Columns!");
	AsyncMatrix result = AsyncMatrix(this->rows, this->columns);

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

AsyncMatrix AsyncMatrix::multiplyNoThreads(AsyncMatrix& other)
{
	if (this->columns != other.getRows())
		throw new InvalidMatrixOperation("Cannot perform multiplication: invalid number of Rows & Columns!");
	AsyncMatrix result = AsyncMatrix(this->rows, other.getColumns());

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
