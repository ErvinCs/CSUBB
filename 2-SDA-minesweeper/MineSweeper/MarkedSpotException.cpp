#include "MarkedSpotException.h"

MarkedSpotException::MarkedSpotException() noexcept
{
	this->message = "";
}

MarkedSpotException::MarkedSpotException(const std::string& msg)
{
	this->message = msg;
}

const char* MarkedSpotException::what()
{
	return message.c_str();
}