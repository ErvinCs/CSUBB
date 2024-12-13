#include "TextUI.h"

void TextUI::printMenu()
{
	std::string options = "1.Check spot \n";
	options += "2.Place\\Remove flag \n";
	options += "0.Exit \n";
	std::cout << options;
}

void TextUI::printPlayerBoard()
{
	std::cout << this->con.toStringView();
}

void TextUI::printActualBoard()
{
	std::cout << this->con.toStringBoard();
}

int readValue(std::string what)
{
	int value;
	std::cout << "Enter " + what + ": ";
	std::cin >> value;

	return value;
}

void TextUI::run()
{
	bool running = true;
	int x, y;
	int flagNo = this->con.getNumberOfMines();
	while (running)
	{
		if (flagNo <= 0)
		{
			running = false;

			this->printPlayerBoard();
			this->printActualBoard();

			char enter;
			std::cin >> enter;
		}

		this->printPlayerBoard();
		this->printMenu();

		int action;
		std::cout << "Action: ";
		std::cin >> action;
		if (!(action == 0 || action == 1 || action != 2))
			action = 3;

		switch (action)
		{
		case 0:
			std::cout << "Goodbye.\n";
			running = false;
			break;
		case 1:
			x = readValue("X");
			y = readValue("Y");
			if (x < 0 || y < 0 || x > this->con.getBoardWidth() || y > this->con.getBoardHeight())
			{
				std::cout << "Invalid input!\n";
				break;
			}
			
			try {
				bool gg = this->con.expand(x, y);
				if (gg == false)
				{
					running = false;
					std::cout << "Mine hit. Good game.\n";
					this->printActualBoard();
					char enter;
					std::cin >> enter;
				}
			}
			catch (MarkedSpotException ex)
			{
				std::cout << "Spot is marked!\n";
			}
			break;
		case 2:
			x = readValue("X");
			y = readValue("Y");
			if (x < 0 || y < 0 || x > this->con.getBoardWidth() || y > this->con.getBoardHeight())
			{
				std::cout << "Invalid input!\n";
				break;
			}
			this->con.flag(x, y);
			flagNo--;
		default:
			std::cout << "Invalid input!\n";
			break;
		}
	}
}