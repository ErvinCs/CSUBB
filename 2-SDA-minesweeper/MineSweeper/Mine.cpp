#include "Mine.h"

Mine::Mine(const Mine& newMine)
{
	this->x = newMine.getX();
	this->y = newMine.getY();
	this->marked = newMine.isMarked();
	this->proxy = newMine.proxy;
}

const int Mine::getX() const
{
	return this->x;
}

void Mine::setX(const int& x)
{
	this->x = x;
}

const int Mine::getY() const
{
	return this->y;
}

void Mine::setY(const int& y)
{
	this->y = y;
}

const bool Mine::isMarked() const
{
	return this->marked;
}

void Mine::setMarked(const bool& marked)
{
	this->marked = marked;
}

std::string Mine::toString()
{
	return "Mine={x=" + std::to_string(x) +
			", y=" + std::to_string(y) +
			", marked=" + std::to_string(marked) +
			"}";
}

bool Mine::operator==(Mine other)
{
	return ( this->x == other.getX() &&
			 this->y == other.getY() && 
			 this->marked == other.isMarked() );
}
bool Mine::operator!=(Mine other)
{
	return !( this->x == other.getX() &&
			  this->y == other.getY() &&
			  this->marked == other.isMarked() );
}

void Mine::setProxy(const int& proxy)
{
	this->proxy = proxy;
}

const int Mine::getProxy()
{
	return this->proxy;
}