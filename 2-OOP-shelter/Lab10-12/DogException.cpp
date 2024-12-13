#include "DogException.h"

DogException::DogException() noexcept
{
    this->message = "";
}

DogException::DogException(const std::string& msg)
{
    this->message = msg;
}

const char* DogException::what()
{
    return message.c_str();
}