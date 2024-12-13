#ifndef _UI_H
#define _UI_H

#pragma once

#include "Controller.h"

template<typename T> class UI {
public:
    virtual void printMenu() = 0;
    virtual void run() = 0;
};

#endif //_UI_H