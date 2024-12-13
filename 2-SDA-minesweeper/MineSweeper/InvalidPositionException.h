#pragma once

#include <exception>
#include <string>

class InvalidPositionException : public std::exception
{
protected:
	std::string message;

public:
	InvalidPositionException() noexcept;
	explicit InvalidPositionException(const std::string& msg);
	virtual const char* what();
};