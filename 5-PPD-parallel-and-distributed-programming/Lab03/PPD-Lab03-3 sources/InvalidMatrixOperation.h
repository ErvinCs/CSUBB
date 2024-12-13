#pragma once

#include <exception>
#include <string>

class InvalidMatrixOperation : public std::exception
{
protected:
	std::string message;
public:
	InvalidMatrixOperation();
	explicit InvalidMatrixOperation(const std::string& msg);
	virtual const char* what();
	~InvalidMatrixOperation() = default;
};

