#ifndef _FILEPARSER_H
#define _FILEPARSER_H

#pragma once

#include <sstream>
#include <string>
#include <vector>
#include <fstream>


class FileParser
{
public:
    FileParser() = default;

    std::string strip(const std::string& str);

    std::vector<std::string> tokenize(const std::string& str, char separator);

	std::vector<std::string> tokenizeHTML(std::ifstream& file);
};


#endif //_FILEPARSER_H
