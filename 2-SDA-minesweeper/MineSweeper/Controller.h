#pragma once

#include <string>
#include <time.h> 

#include "Mine.h"
#include "SparseMatrix.h"
#include "MarkedSpotException.h"


class Controller
{
private:
	SparseMatrix board;
	SparseMatrix view;
	int numberOfMines;
public:
	Controller(const int& boardSize) : board(SparseMatrix(boardSize, boardSize)), view(SparseMatrix(boardSize, boardSize)), numberOfMines(0) 
	{ this->initMines(); }

	void initMines();

	void flag(const int& x, const int& y);

	bool expand(const int& x, const int& y);

	void fillBoard(const int& x, const int& y);

	std::string toStringBoard();

	std::string toStringView();

	int getBoardHeight();

	int getBoardWidth();

	int getNumberOfMines();

	SparseMatrix getBoard();

};