#pragma once

#include <exception>
#include <string>

class MarkedSpotException : public std::exception
{
protected:
	std::string message;

public:
	MarkedSpotException() noexcept;
	explicit MarkedSpotException(const std::string& msg);
	virtual const char* what();
};