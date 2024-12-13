#pragma once

//Uncomment these - they exceeded git file size limit
//#include <boost/asio.hpp>
//#include <boost/thread.hpp>
//#include <boost/bind.hpp>

#include <vector>

//These are proly not needed
//#include <boost/asio/io_service.hpp>
//#include <boost/thread/thread.hpp>

#include "Matrix.h"


class PoolMatrix : public Matrix
{
public:
	PoolMatrix() : Matrix() { }
	PoolMatrix(int rows, int columns) : Matrix(rows, columns) { }
	PoolMatrix(int rows, int columns, int** data) : Matrix(rows, columns, data) { }
	~PoolMatrix() = default; 

	PoolMatrix add(PoolMatrix& other);
	PoolMatrix multiply(PoolMatrix& other);
	PoolMatrix addNoThreads(PoolMatrix& other);
	PoolMatrix multiplyNoThreads(PoolMatrix& other);
};

