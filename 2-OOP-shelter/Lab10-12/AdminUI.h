#ifndef _ADMINUI_H
#define _ADMINUI_H

#pragma once

#include <cstring>
#include <iostream>

#include "UI.h"
#include "Controller.h"
#include "FileController.h"

class AdminUI: public UI<Dog>
{
public:
    FileController<Dog>& con;
public:
    explicit AdminUI(FileController<Dog>& c) : con(c) {}

    void add();

    void remove();

    void update();

    void printAll();

    void displayFile();

    void printMenu() override;

    void run() override;
};

#endif //_ADMINUI_H