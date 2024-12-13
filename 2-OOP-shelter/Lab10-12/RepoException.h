#ifndef _REPOEXCEPTION_H
#define _REPOEXCEPTION_H

#pragma once

#include <exception>
#include <string>


class RepoException : public std::exception
{
protected:
    std::string message;

public:
    RepoException() noexcept;
    explicit RepoException(const std::string& msg);
    virtual const char* what();
};

#endif //_REPOEXCEPTION_H
