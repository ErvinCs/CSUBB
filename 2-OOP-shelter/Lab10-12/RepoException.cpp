#include "RepoException.h"

RepoException::RepoException() noexcept
{
    this->message = "";
}

RepoException::RepoException(const std::string& msg)
{
    this->message = message;
}

const char* RepoException::what()
{
    return this->message.c_str();
}