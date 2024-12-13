#include "UserUI.h"
#include "Database.h"

void UserUI::run()
{
    std::cout << "User Mode.\n";
    bool running = true;
    while (running)
    {
        printMenu();

        int option;
        std::cout << "Enter option: ";
        std::cin >> option;

        switch (option)
        {
            case 0:
                running = false;
                break;
            case 1:
                next();
                adopt();
                break;
            case 2:
                std::cout << "Enter breed: ";
                std::cin >> breed;

                std::cout << "Enter age: ";
                std::cin >> age;

                filterBreedAge(breed, age);
                break;
            case 3:
                this->filterOn = false;
                this->breed = "";
                this->age = -1;
                break;
            case 4:
                printAdoptionList();
                break;
        }
    }
}

void UserUI::printMenu()
{
    std::cout << "\t0. Exit \n";
    std::cout << "\t1. See the dogs in the database \n";
    std::cout << "\t2. Filter the dogs of 'breed', younger than 'age' \n";
    std::cout << "\t3. Turn off the filter \n";
    std::cout << "\t4. See the adoption list \n";
}

void UserUI::next()
{
    if (this->filterOn)
    {
        this->con.next();
        int stop = this->con.getCurrent().getId();
        while (this->con.getCurrent().getBreed() != this->breed
               || this->con.getCurrent().getAge() >= this->age)
        {
            this->con.next();
            if (this->con.getCurrent().getId() == stop)
            {
                this->filterOn = false;
                this->breed = "";
                this->age = -1;

                this->con.next();
                break;
            }
        }
        std::cout << this->con.getCurrent().toString();
        this->con.getCurrent().open();
    } else
    {
        this->con.next();
        std::cout << this->con.getCurrent().toString();
        this->con.getCurrent().open();
    }

}

void UserUI::adopt()
{
    std::string prompt = "null";
    std::cout << "Do you want to adopt '" << con.getCurrent().getName() << "'?(y/n)\n";
    std::cin >> prompt;

    if (prompt == "y")
    {
        this->dogDb.add(con.getCurrent());
        std::cout << "Congratulations! You are now the owner of this incredible dog: " << con.getCurrent().toString();
        this->con.remove(con.getCurrent().getId());
        this->con.previous();
    }
	else if (prompt == "n")
	{
		std::cout << "You heartless monster.\n";
	}
    else
        std::cout << "Invalid input.\n";

    while (prompt != "y" && prompt != "n")
    {
        std::cout << "Do you want to adopt '" << con.getCurrent().getName() << "'?(y/n)\n";
        std::cin >> prompt;
        if (prompt == "y")
        {
            this->dogDb.add(con.getCurrent());
            std::cout << "Congratulations! You are now the owner of this incredible " << con.getCurrent().toString();
            this->con.remove(con.getCurrent().getId());
            this->con.previous();
        }
        else if (prompt == "n")
            std::cout << "You heartless monster.\n";
        else
            std::cout << "Invalid input.\n";
    }
}

void UserUI::printAdoptionList()
{
    if (this->dogDb.getSize() == 0)
        std::cout << "Empty adoption list.\n";
    std::cout << this->dogDb.toString();
}


void UserUI::filterBreedAge(std::string& breed, int& age)
{
    this->breed = breed;
    this->age = age;
    this->filterOn = true;
}

