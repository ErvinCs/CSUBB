#include "ControllerException.h"

ControllerException::ControllerException() noexcept
{
    this->message = "";
}

ControllerException::ControllerException(const std::string& msg)
{
    this->message = msg;
}

const char* ControllerException::what()
{
    return message.c_str();
}