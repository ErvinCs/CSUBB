#ifndef _TEXTFILEDATABASE_H
#define _TEXTFILEDATABASE_H

#pragma once

#include "Database.h"
#include "Dog.h"

template <typename T>
class TextFileDatabase : public Database<T>
{
public:
    TextFileDatabase() noexcept {};
    explicit TextFileDatabase(const Database<T>& other) : Database<T>(other)
    {}
    explicit TextFileDatabase(const std::string& filename) : Database<T>(filename)
    {
        this->readFile();
    }

    void readFile() override
    {
        std::ifstream file;
        file.open(this->getFilename(), std::ios::in);

        T item;
        while (file.good())
        {
            file >> item;
            this->repo.push_back(item);
            idGen++;
        }

        file.close();
    }


    void writeFile() override
    {
        std::ofstream file;
        file.open(this->filename, std::ios::out);

        for (T item : this->repo)
            file << item;

        file.close();
    }

    void display() override
    {
		std::string f = this->getFilename();
        std::string command = "notepad.exe " + f;  
        system(command.c_str());
    }
};

#endif //_TEXTFILEDATABASE_H
