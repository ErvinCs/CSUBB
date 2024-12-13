#pragma once

#include <vector>
#include <string>
#include <random>
#include <ctime>
#include <cstdlib>
#include <chrono>
#include <iostream>

#include "InvalidMatrixOperation.h"
 
class Matrix
{
protected:
	int rows;
	int columns;
	int** data;

public:
	Matrix()
	{
		this->rows = -1;
		this->columns = -1;
		this->data = nullptr;
	}

	Matrix(int rows, int columns)
	{
		this->rows = rows;
		this->columns = columns;
		this->data = new int*[columns];
		for (int i = 0; i < rows; i++)
			this->data[i] = new int[rows];

	}

	Matrix(int rows, int columns, int** data)
	{
		this->rows = rows;
		this->columns = columns;
		for (int i = 0; i < this->rows; i++)
			for (int j = 0; j < this->columns; j++)
				this->data[i][j] = data[i][j];
	}

	~Matrix() { }

	void populate()
	{
		std::srand(std::time(nullptr));
		for (int i = 0; i < this->rows; i++)
			for (int j = 0; j < this->columns; j++)
				this->data[i][j] = 1 + std::rand() % 10 + 1;
	}

	//virtual Matrix<T> add(Matrix<T>& other) = 0;

	//virtual Matrix<T> addNoThreads(Matrix<T>& other) = 0;

	//virtual Matrix<T> multiply(Matrix<T>& other) = 0;

	//virtual Matrix<T> multiplyNoThreads(Matrix<T>& other) = 0;

	const int& getRows()
	{
		return this->rows;
	}

	const int& getColumns()
	{
		return this->columns;
	}

	int** getData() const
	{
		return this->data;
	}

	std::string toString()
	{
		std::string output = "[" + std::to_string(this->rows) + "x" + std::to_string(this->columns) + "]\n";
		for (int i = 0; i < this->rows; i++)
		{
			output += "( ";
			for (int j = 0; j < this->columns; j++)
			{
				output += std::to_string(this->data[i][j]) + " ";
			}
			output += ")\n";
		}
		return output;
	}

};

