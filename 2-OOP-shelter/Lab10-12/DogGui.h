#pragma once

#include <QtWidgets/QMainWindow>
#include <QListWidget>
#include <QFormLayout>
#include <QLineEdit>
#include <QPushButton>
#include <QLabel>
#include <QRadioButton>
#include <QtWidgets/qcombobox.h>

#include <vector>
#include <algorithm>

#include "RepoException.h"
#include "DogException.h"
#include "Dog.h"
#include "GuiController.h"
#include "TextFileDatabase.h"
#include "HtmlFileDatabase.h"

class DogGui : public QWidget
{
	Q_OBJECT
private:
	GuiController<Dog>& con;

	std::vector<Dog> currentDogsInRepo;
	std::vector<Dog> currentDogsAdopted;
	int adoptedIndex;

	QListWidget* repoList;
	QListWidget* adoptionList;

	QLineEdit* breedEdit;
	QLineEdit* nameEdit;
	QLineEdit* ageEdit;
	QLineEdit* photoLinkEdit;
	QLineEdit* idEdit;

	QPushButton* addButton;
	QPushButton* removeButton1;
	QPushButton* updateButton;
	QPushButton* filterButton;
	QPushButton* displayButton;

	QPushButton* moveOneButton;
	QPushButton* moveAllButton;

	QPushButton* openButton;
	QPushButton* removeButton2;
	QPushButton* saveToHTMLButton;
	QPushButton* saveToTXTButton;
	QPushButton* nextButton;

	//Activity - ComboBox
	QComboBox* comboBoxRepo;	
	QComboBox* comboBoxAdoptions;
	QString (*stringFormatRepo)(const Dog& dog);
	QString (*stringFormatAdopted)(const Dog& dog);

	QLineEdit* testEdit;


public:
	DogGui(GuiController<Dog>& c, QWidget* parent = 0);
	~DogGui() = default;

private:
	void initGui();
	void initRepo();
	void initAdopted();
	int getItemRepo();
	int getItemAdopted();
	void connectSigSlot();


private slots:
	void addDog();
	void removeDogDatabase();
	void updateDog();

	void filter();
	void moveAll();
	void moveOne();

	void removeDogClient();
	void displayDog();
	void displayDogRepo();
	void saveToFile();
	void saveToHtml();

	void comboBoxRepoView(const int mode);
	void comboBoxAdoptionsView(const int mode);
	void next();
};