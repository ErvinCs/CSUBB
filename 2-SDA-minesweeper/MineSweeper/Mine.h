#pragma once

#include <string>

class Mine
{
private:
	int x;
	int y;
	bool marked;
	int proxy;
public:
	/**
	* Initialize all the fields
	* @param: xCoord - int
	* @param: yCoord - int
	* @param: marked - bool = false
	*/
	Mine(const int& xCoord, const int& yCoord) : x(xCoord), y(yCoord), marked(false), proxy(0) {}

	/**
	* Copy constructor
	*/
	Mine(const Mine& newMine);

	/**
	* Default constructor
	* x = -1
	* y = -1
	* marked = false
	*/
	Mine() : x(-1), y(-1), marked(false), proxy(0) {}

	const int getX() const;
	void setX(const int& x);

	const int getY() const;
	void setY(const int& y);

	const bool isMarked() const;
	void setMarked(const bool& marked);

	void setProxy(const int& proxy);
	const int getProxy();

	std::string toString();

	/**
	* Two mines are equal if their x, y, marked fields are equal
	*/
	bool operator==(Mine other);
	bool operator!=(Mine other);
};