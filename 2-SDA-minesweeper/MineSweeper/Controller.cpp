#include "Controller.h"
#include <iostream>
void Controller::initMines()
{
	this->numberOfMines = (this->board.columnNr() * this->board.lineNr()) / 5;
	srand(time(NULL));
	//std::cout << numberOfMines;
	for (int i = 0; i < numberOfMines; i++)
	{
		int x = rand() % this->board.lineNr();
		int y = rand() % this->board.columnNr();
		while (this->board.getElement(x, y) != Mine{})
		{
			x = rand() % this->board.lineNr();
			y = rand() % this->board.columnNr();
		}
		this->board.modify(x, y, Mine(x, y));
	}
}

void Controller::flag(const int& x, const int& y)
{
	Mine mine = this->view.getElement(x, y);
	if (mine.isMarked())
	{
		mine.setMarked(false);
		this->view.modify(x, y, mine);
		this->board.modify(x, y, mine);
	}
	else
	{
		mine.setMarked(true);
		this->view.modify(x, y, mine);
		this->board.modify(x, y, mine);
	}
}

bool Controller::expand(const int& x, const int& y)
{
	if (this->board.getElement(x, y).isMarked())
		throw MarkedSpotException("Spot is marked!");

	if (this->board.getElement(x, y) == Mine(x, y))
		return false;
	else
		this->fillBoard(x, y);
	return true;
}

void Controller::fillBoard(const int& x, const int& y)
{
	int counter = 0;
	if (y + 1 < this->board.columnNr() && this->board.getElement(x, y + 1).getX() != -1)
		counter++;
	if (x + 1 < this->board.lineNr() && this->board.getElement(x + 1, y).getX() != -1)
		counter++;
	if (y + 1 < this->board.columnNr() && x + 1 < this->board.lineNr() && this->board.getElement(x + 1, y + 1).getX() != -1)
		counter++;
	if (y - 1 > this->board.columnNr() && this->board.getElement(x, y - 1).getX() != -1)
		counter++;
	if (x - 1 > this->board.lineNr() && this->board.getElement(x - 1, y).getX() != -1)
		counter++;
	if (y - 1 > this->board.columnNr() && x - 1 > this->board.lineNr() && this->board.getElement(x - 1, y - 1).getX() != -1)
		counter++;
	if (y + 1 < this->board.columnNr() && x - 1 > this->board.lineNr() && this->board.getElement(x - 1, y + 1).getX() != -1)
		counter++;
	if (y - 1 > this->board.columnNr() && x + 1 < this->board.lineNr() && this->board.getElement(x + 1, y - 1).getX() != -1)
		counter++;

	Mine mine = Mine(x, y);
	mine.setProxy(counter);
	this->view.modify(x, y, mine);
}


SparseMatrix Controller::getBoard()
{
	return this->board;
}

std::string Controller::toStringBoard()
{
	std::string output = "Final Board: \n  ";
	for (int i = 0; i < this->board.lineNr(); i++)
		output += std::to_string(i) + " ";
	output += "\n";

	for (int i = 0; i < this->board.lineNr(); i++)
	{
		for (int j = 0; j < this->board.columnNr(); j++)
		{
			if (j == 0)
				output += std::to_string(i) + " ";
			Mine curr = this->board.getElement(i, j);
			if (curr == Mine{})
				output += "- ";
			else
				output += "X ";
		}
		output += "\n";
	}
	output += "\n";

	return output;
}

std::string Controller::toStringView()
{
	std::string output = "Board: \n  ";
	for (int i = 0; i < this->board.lineNr(); i++)
		output += std::to_string(i) + " ";
	output += "\n";

	for (int i = 0; i < this->view.lineNr(); i++)
	{
		for (int j = 0; j < this->view.columnNr(); j++)
		{
			if (j == 0)
				output += std::to_string(i) + " ";
			Mine currView = this->view.getElement(i, j);
			Mine flagged = Mine{};
			flagged.setMarked(true);

			if (currView == flagged)
				output += "F ";
			else if (currView.getProxy() > 0)
				output += std::to_string(currView.getProxy()) + " ";
			else
				output += "- ";
		}
		output += "\n";
	}
	output += "\n";

	return output;
}

int Controller::getBoardHeight()
{
	return this->board.lineNr();
}

int Controller::getBoardWidth()
{
	return this->board.columnNr();
}

int Controller::getNumberOfMines()
{
	return this->numberOfMines;
}