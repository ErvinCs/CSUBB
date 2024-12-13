#ifndef _CONTROLLEREXCEPTION_H
#define _CONTROLLEREXCEPTION_H

#include <exception>
#include <string>

class ControllerException: public std::exception
{
protected:
    std::string message;

public:
    ControllerException() noexcept;
    explicit ControllerException(const std::string& msg);
    virtual const char* what();
};

#endif //_CONTROLLEREXCEPTION_H
