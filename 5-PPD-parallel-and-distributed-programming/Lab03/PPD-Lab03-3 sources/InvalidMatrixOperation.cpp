#include "pch.h"
#include "InvalidMatrixOperation.h"


InvalidMatrixOperation::InvalidMatrixOperation()
{
	this->message = "";
}


InvalidMatrixOperation::InvalidMatrixOperation(const std::string& msg)
{
	this->message = msg;
}

const char* InvalidMatrixOperation::what()
{
	return this->message.c_str();
}