#ifndef _HTMLFILEDATABASE_H
#define _HTMLFILEDATABASE_H

#pragma once

#include "Dog.h"
#include "Database.h"
#include <iostream>
#include <fstream>
#include <string>

template <typename T>
class HtmlFileDatabase : public Database<T>
{
public:
    HtmlFileDatabase() noexcept {};
    explicit HtmlFileDatabase(const Database<T>& other) : Database<T>(other) {}

    explicit HtmlFileDatabase(const std::string& filename) : Database<T>(filename) { this->readFile(); }

    void readFile() override
    {
        std::ifstream file;
        file.open(this->getFilename(), std::ios::in);

        std::string temp;
        for(int i = 0; i < 3; i++)
            std::getline(file, temp);

        //T item;
        while (file.good())
        {
            Dog item = Dog();

            std::getline(file, temp);
            item.setName(temp);

            for(int i = 0; i < 2; i++)
                std::getline(file, temp);
            std::getline(file, temp);
            item.setBreed(temp);

            for(int i = 0; i < 2; i++)
                std::getline(file, temp);
            std::getline(file, temp);
            item.setAge(std::stoi(temp));

            for(int i = 0; i < 3; i++)
                std::getline(file, temp);
            std::getline(file, temp);
            item.setPhotoLink(temp);

            for(int i = 0; i < 3; i++)
                std::getline(file, temp);
            std::getline(file, temp);
            item.setId(std::stoi(temp));
            idGen++;

            this->repo.push_back(item);

			for (int i = 0; i < 2; i++)
				std::getline(file, temp);

            std::getline(file, temp);
            if (temp.find("</table>") != std::string::npos)
                break;
            std::getline(file, temp);
        }
        file.close();
    }

    void writeFile() override
    {
        std::ofstream file;
        file.open(this->getFilename(), std::ios::out);

        file << "<!DOCTYPE html><html><head><title>Dog Shelter</title></head><body><table border = ""1""><tr><td>Breed</td><td>Name</td><td>Age</td><td>Link</td><td>Id</td></tr>\n";
        for (Dog dog : this->repo)
        {
            file << "<tr>\n";
            file << "<td>\n";
            file << dog.getBreed();
            file << "\n</td>\n";
            file << "<td>\n";
            file << dog.getName();
            file << "\n</td>\n";
            file << "<td>\n";
            file << dog.getAge();
            file << "\n</td>\n";
            file << "<td>\n<a href = """;
            file << dog.getPhotoLink();
            file << """>\n" << dog.getPhotoLink() << "\n</a>\n</td>\n";
            file << "<td>\n";
            file << dog.getId();
            file << "\n</td>\n";
            file << "</tr>\n";
        }
        file << "</table></body></td></tr></html>";

        file.close();
    }

    void display() override
    {
		std::string f = this->getFilename();
		std::string command = "firefox.exe " + f; 
		system(command.c_str());
	}
};

#endif //_HTMLFILEDATABASE_H
