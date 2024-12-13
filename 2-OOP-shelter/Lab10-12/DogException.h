#ifndef _DOGEXCEPTION_H
#define _DOGEXCEPTION_H

#pragma once

#include <exception>
#include <string>

class DogException: public std::exception
{
protected:
    std::string message;

public:
    DogException() noexcept;
    explicit DogException(const std::string& msg);
    virtual const char* what();
};

#endif //_DOGEXCEPTION_H
