#ifndef _DOGVALIDATOR_H
#define _DOGVALIDATOR_H

#pragma once

#include <string>
#include "Dog.h"
#include "DogException.h"

class DogValidator
{
public:
    bool validate(const std::string& breed, const std::string& name, const int& age, const std::string& photoLink);
};

#endif //LAB8_9_DOGVALIDATOR_H
