#ifndef _DOG_H
#define _DOG_H

#pragma once

#include <cstdio>
#include <cstdlib>
#include <cstring>
#include <string>
#include <windows.h>
#include <vector>

#include "FileParser.h"
#include "DogException.h"

static int idGen = 1;

class Dog
{
public:
    int age;
    std::string name;
    std::string photoLink;
private:
    std::string breed;
    int id;

public:

    /**
     * Default constructor
     */
    Dog() noexcept;

    /**
     * @param breed: string
     * @param name: string
     * @param age: int
     * @param photoLink: string
     *  A link to a photograph
     */
    Dog(const std::string& breed, const std::string& name,
        const int& age, const std::string& photoLink);


    int getAge() const { return this->age; }

    void setAge(const int age) { this->age = age; }

    std::string getName() const { return this->name; }

    void setName(const std::string& name) { this->name = name; }

    std::string getPhotoLink() const { return this->photoLink;}

    void setPhotoLink(const std::string& photoLink) { this->photoLink = photoLink; }

    int getId() const { return this->id; }

    void setId(const int id) { this->id = id; }

    void setBreed(const std::string &breed) { this->breed = breed; }

    std::string getBreed() const { return this->breed; }

    /**
     * True if the 2 Dogs are the same object; false otherwise
     * @param other
     * @return: boolean
     */
    bool operator==(Dog other);

    /**
     * True if the 2 Dogs are not the same object; false otherwise
     * @param other
     * @return: boolean
     */
    bool operator!=(Dog other);

    /**
    * @return: string representation: Dog{breed="", name="", age="", id=""}
    */
    std::string const toString();

    /**
     * Opens the link stored at the photolink field
     */
    void open();

    friend std::istream& operator>>(std::istream& in, Dog& dog);

    friend std::ostream& operator<<(std::ostream& out, const Dog& dog);

};

#endif //_DOG_H