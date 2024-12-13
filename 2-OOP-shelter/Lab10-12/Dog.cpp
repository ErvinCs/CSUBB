#include "Dog.h"

Dog::Dog() noexcept
{
    this->breed = "";
    this->name = "";
    this->age = -1;
    this->photoLink = "";
    this->id = idGen;
    idGen += 1;
}

Dog::Dog(const std::string& breed, const std::string& name, const int& age, const std::string& photoLink)
{
    this->breed = breed;
    this->name = name;
    this->age = age;
    this->photoLink = photoLink;
    this->id = idGen;
    idGen += 1;
}

bool Dog::operator==(Dog other)
{
    return (this->breed == other.breed && this->name == other.name &&
            this->age == other.age && this->photoLink == other.photoLink && this->id == other.id);
}

bool Dog::operator!=(Dog other)
{

    return !(this->breed == other.breed && this->name == other.name &&
            this->age == other.age && this->photoLink == other.photoLink && this->id == other.id);
}

const std::string Dog::toString()
{
    std::string ageStr = std::to_string(this->age);
    std::string idStr = std::to_string(this->id);

    std::string dogStr = "Dog{breed=" + breed + ", name=" + name
                         + ", age=" + ageStr + ", id=" + idStr +"}\n";
    return dogStr;
}

void Dog::open()
{
    ShellExecuteA(nullptr, nullptr, "firefox.exe", this->photoLink.c_str(), nullptr, SW_SHOWMAXIMIZED);
}

std::istream& operator>>(std::istream& in, Dog& dog)
{
    FileParser fp;
    std::string line;
    std::getline(in, line);

    std::vector<std::string> tokens = fp.tokenize(line, ',');

    if (tokens.size() != 5)
        return in;

    int age = std::stoi(tokens[2]);
    int id = std::stoi(tokens[4]);
    if (age < 0)
        throw DogException("Invalid age.\n");

    dog.setBreed(tokens[0]);
    dog.setName(tokens[1]);
    dog.setAge(age);
    dog.setPhotoLink(tokens[3]);
    dog.setId(id);

    return in;
}

std::ostream& operator<<(std::ostream& out, const Dog& dog)
{
    out << dog.getBreed() << "," << dog.getName() << "," << std::to_string(dog.getAge())
        << "," << dog.getPhotoLink() << "," << std::to_string(dog.getId()) << std::endl;
    return out;
}

