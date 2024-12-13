#include "FileParser.h"

std::string FileParser::strip(const std::string& str)
{
    std::string result = str;

    unsigned long pos = (int)result.find_first_not_of(' ');
    if (pos != -1)
        result.erase(0, pos);

    pos = (int)result.find_last_not_of(' ');
    if (pos != std::string::npos)
        result.erase(pos+1);

    return result;
}

std::vector<std::string> FileParser::tokenize(const std::string& str, char separator)
{
    std::vector<std::string> result;
    std::stringstream stringStream(str);
    std::string token;

    while (getline(stringStream, token, separator))
    {
        token = this->strip(token);
        result.push_back(token);
    }

    return result;
}

std::vector<std::string> FileParser::tokenizeHTML(std::ifstream& file)
{
	std::vector<std::string> result;
	return result;
}