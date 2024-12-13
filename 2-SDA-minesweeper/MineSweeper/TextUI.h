#pragma once

#include <iostream>
#include <string>

#include "Controller.h"
#include "MarkedSpotException.h"


class TextUI
{
private:
	Controller con;
public:
	int readBoardSize()
	{
		int size;
		std::cout << "Enter board size(5-10): ";
		std::cin >> size;
		while (size < 5 || size > 10)
		{
			std::cout << "Invalid input!\n";
			std::cout << "Enter board size(5-10): ";
			std::cin >> size;
		}
		return size;
	}

	TextUI() : con(readBoardSize()) {}

	void printPlayerBoard();

	void printActualBoard();

	void printMenu();

	void run();




};