#include "DogValidator.h"
#include "DogException.h"

bool DogValidator::validate(const std::string& breed, const std::string& name, const int& age, const std::string& photoLink)
{
    if (breed.length() < 1)
        throw DogException("Invalid breed name.\n");
    if (name.length() < 1)
        throw DogException("Invalid name.\n");
    if (age < 0)
        throw DogException("Invalid age.\n");
	//if (photoLink.find("www") != 0 && photoLink.find("http") != 0)
	//	throw DogException("Invalid link.\n");
    return true;
}

