#include "InvalidPositionException.h"

InvalidPositionException::InvalidPositionException() noexcept
{
	this->message = "";
}

InvalidPositionException::InvalidPositionException(const std::string& msg)
{
	this->message = msg;
}

const char* InvalidPositionException::what()
{
	return message.c_str();
}