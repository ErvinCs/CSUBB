#include <QMessageBox>

#include "DogGui.h"


DogGui::DogGui(GuiController<Dog>& c, QWidget* parent) : con{c}, QWidget{parent}
{
	this->initGui();
	this->currentDogsInRepo = this->con.getAllRepo();
	this->initRepo();
	this->initAdopted();
}

QString shortStringDog(const Dog& dog);
QString detailedStringDog(const Dog& dog);

void DogGui::initGui()
{
	this->adoptedIndex = 0;

	QHBoxLayout* layout = new QHBoxLayout{ this };

	QWidget* leftWidget = new QWidget{};
	QVBoxLayout* leftSide = new QVBoxLayout{ leftWidget };

	this->repoList = new QListWidget{};
	this->repoList->setSelectionMode(QAbstractItemView::SingleSelection);

	QWidget* dogDataWidget = new QWidget{};
	QFormLayout* formLayout = new QFormLayout{ dogDataWidget };

	this->breedEdit = new QLineEdit{};
	this->nameEdit = new QLineEdit{};
	this->ageEdit = new QLineEdit{};
	this->photoLinkEdit = new QLineEdit{};

	formLayout->addRow("&Breed:", breedEdit);
	formLayout->addRow("&Name:", nameEdit);
	formLayout->addRow("&Age:", ageEdit);
	formLayout->addRow("&Link:", photoLinkEdit);

	QWidget* buttonsWidget = new QWidget{};
	QGridLayout* gridLayout = new QGridLayout{ buttonsWidget };
	this->addButton = new QPushButton("Add");
	this->removeButton1 = new QPushButton("Remove");
	this->updateButton = new QPushButton("Update");
	this->filterButton = new QPushButton("Filter");
	this->displayButton = new QPushButton("Open");

	this->comboBoxRepo = new QComboBox(this);
	this->comboBoxRepo->addItem("Detalied");
	this->comboBoxRepo->addItem("Short");

	gridLayout->addWidget(removeButton1);
	gridLayout->addWidget(addButton);	
	gridLayout->addWidget(updateButton);	
	gridLayout->addWidget(filterButton);	
	gridLayout->addWidget(displayButton);

	leftSide->addWidget(new QLabel{ "Dogs" });
	leftSide->addWidget(comboBoxRepo);
	leftSide->addWidget(repoList);
	leftSide->addWidget(dogDataWidget);
	leftSide->addWidget(buttonsWidget);

	QWidget* middleWidget = new QWidget{};
	QVBoxLayout* vLayoutMidle = new QVBoxLayout{ middleWidget };
	this->moveOneButton = new QPushButton{ " > Adopt Dog < " };

	QWidget* upperPart = new QWidget{};
	QWidget* lowerPart = new QWidget{};
	QVBoxLayout* vLayoutUpperPart = new QVBoxLayout{ upperPart };

	vLayoutUpperPart->addWidget(this->moveOneButton);
	vLayoutMidle->addWidget(upperPart);
	vLayoutMidle->addWidget(lowerPart);

	QWidget* rightWidget = new QWidget{};
	QVBoxLayout* rightSide = new QVBoxLayout{ rightWidget };

	adoptionList = new QListWidget{};

	QWidget* dogsButtonWidget = new QWidget{};
	QHBoxLayout* dogsButtonLayout = new QHBoxLayout{ dogsButtonWidget };
	this->openButton = new QPushButton("Open");
	this->removeButton2 = new QPushButton("Remove");
	this->saveToTXTButton = new QPushButton("Save to TXT");
	this->saveToHTMLButton = new QPushButton("Save to HTML");
	this->nextButton = new QPushButton("Next");

	dogsButtonLayout->addWidget(nextButton);
	dogsButtonLayout->addWidget(openButton);
	//dogsButtonLayout->addWidget(removeButton2);
	dogsButtonLayout->addWidget(saveToTXTButton);
	dogsButtonLayout->addWidget(saveToHTMLButton);

	this->comboBoxAdoptions = new QComboBox(this);
	this->comboBoxAdoptions->addItem("Detalied");
	this->comboBoxAdoptions->addItem("Short");

	rightSide->addWidget(new QLabel{ "Adoption List" });
	rightSide->addWidget(comboBoxAdoptions);
	rightSide->addWidget(adoptionList);
	rightSide->addWidget(dogsButtonWidget);

	layout->addWidget(leftWidget);
	layout->addWidget(middleWidget);
	layout->addWidget(rightWidget);

	this->stringFormatRepo = &detailedStringDog;
	this->stringFormatAdopted = &detailedStringDog;

	this->connectSigSlot();
	this->initRepo();
	this->initAdopted();
}

void DogGui::connectSigSlot()
{
	QObject::connect(this->removeButton1, SIGNAL(clicked()), this, SLOT(removeDogDatabase()));
	QObject::connect(this->addButton, SIGNAL(clicked()), this, SLOT(addDog()));
	QObject::connect(this->updateButton, SIGNAL(clicked()), this, SLOT(updateDog()));
	QObject::connect(this->filterButton, SIGNAL(clicked()), this, SLOT(filter()));
	QObject::connect(this->displayButton, SIGNAL(clicked()), this, SLOT(displayDogRepo()));

	QObject::connect(this->moveOneButton, SIGNAL(clicked()), this, SLOT(moveOne()));

	QObject::connect(this->openButton, SIGNAL(clicked()), this, SLOT(displayDog()));
	QObject::connect(this->removeButton2, SIGNAL(clicked()), this, SLOT(removeDogClient()));
	QObject::connect(this->nextButton, SIGNAL(clicked()), this, SLOT(next()));

	QObject::connect(this->saveToTXTButton, SIGNAL(clicked()), this, SLOT(saveToFile()));
	QObject::connect(this->saveToHTMLButton, SIGNAL(clicked()), this, SLOT(saveToHtml()));

	QObject::connect(this->comboBoxRepo, SIGNAL(currentIndexChanged(int)), this, SLOT(comboBoxRepoView(int)));
	QObject::connect(this->comboBoxAdoptions, SIGNAL(currentIndexChanged(int)), this, SLOT(comboBoxAdoptionsView(int)));
}

void DogGui::comboBoxRepoView(const int mode)
{
	if (this->stringFormatRepo == &detailedStringDog)
		this->stringFormatRepo = &shortStringDog;
	else 
		this->stringFormatRepo = &detailedStringDog;

	this->initRepo();
}

void DogGui::comboBoxAdoptionsView(const int mode)
{
	if (this->stringFormatAdopted == &detailedStringDog)
		this->stringFormatAdopted = &shortStringDog;
	else
		this->stringFormatAdopted = &detailedStringDog;

	this->initAdopted();
}

QString detailedStringDog(const Dog& dog)
{
	QString itemInList = QString::fromStdString(
		"Breed: " + dog.getBreed() +
		", Name: " + dog.getName() +
		", Age: " + std::to_string(dog.getAge()) +
		", Id: " + std::to_string(dog.getId())
	);
	return itemInList;
}

QString shortStringDog(const Dog& dog)
{
	QString itemInList = QString::fromStdString(
		"Name: " + dog.getName()
	);
	return itemInList;
}

void DogGui::initRepo()
{
	if (this->repoList->count() > 0)
		this->repoList->clear();

	for (auto dog : this->currentDogsInRepo)
	{
		QString itemInList = this->stringFormatRepo(dog);
		QListWidgetItem* item = new QListWidgetItem(itemInList);
		this->repoList->addItem(item);
	}

	if (this->currentDogsInRepo.size() > 0)
		this->repoList->setCurrentRow(0);
}

void DogGui::initAdopted()
{
	if (this->adoptionList->count() > 0)
		this->adoptionList->clear();

	QString itemInList = this->stringFormatAdopted(this->con.getCurrent());
	QListWidgetItem* doggo = new QListWidgetItem(itemInList);
	this->adoptionList->addItem(doggo);

	if (this->currentDogsAdopted.size() > 0)
		this->adoptionList->setCurrentRow(0);

	//for (auto dog : this->con.getAllAdopted())
	//{
	//	QString itemInList = this->stringFormatAdopted(dog);
	//	QListWidgetItem* item = new QListWidgetItem(itemInList);
	//	this->adoptionList->addItem(item);
	//}

	//if (this->currentDogsAdopted.size() > 0)
	//	this->adoptionList->setCurrentRow(0);
}

//byIndex
int DogGui::getItemRepo()	
{
	if (this->repoList->count() == 0)
		return -1;

	QModelIndexList index = this->repoList->selectionModel()->selectedIndexes();
	if (index.size() == 0)
	{
		this->nameEdit->clear();
		this->breedEdit->clear();
		this->ageEdit->clear();
		this->photoLinkEdit->clear();
		return -1;
	}
	int itemIndex = index.at(0).row();
	return itemIndex;
}

//byIndex
int DogGui::getItemAdopted()
{
	if (this->adoptionList->count() == 0)
		return -1;

	QModelIndexList index = this->adoptionList->selectionModel()->selectedIndexes();
	
	int itemIndex = index.at(0).row();
	return itemIndex;
}

void DogGui::addDog()
{
	try{
		std::string breed = this->breedEdit->text().toStdString();
		std::string name = this->nameEdit->text().toStdString();
		std::string age = this->ageEdit->text().toStdString();
		std::string link = this->photoLinkEdit->text().toStdString();

		Dog dog = Dog(breed, name, std::stoi(age), link);

		this->con.add(dog);
		this->currentDogsInRepo = this->con.getAllRepo();
		this->initRepo();
	}
	catch (DogException ex)
	{
		QMessageBox error;
		error.critical(0, "Error", QString::fromStdString(ex.what()));
	}
	catch (RepoException ex)
	{
		QMessageBox error;
		error.critical(0, "Error", QString::fromStdString(ex.what()));
	}
	catch (std::invalid_argument ex)
	{
		QMessageBox error;
		error.critical(0, "Error", QString::fromStdString("Error: Uncompleted fields!"));
	}

}

void DogGui::removeDogDatabase()
{
	int index = this->getItemRepo();
	if (index == -1)
		return;
	std::vector<Dog> dogs = this->con.getAllRepo();

	if (index >= dogs.size())
		return;

	this->con.remove(dogs[index].getId());
	this->currentDogsInRepo = this->con.getAllRepo();
	this->initRepo();
}

void DogGui::updateDog()
{
	int index = this->getItemRepo();
	if (index == -1)
		return;
	std::vector<Dog> dogs = this->con.getAllRepo();
	int id = dogs[index].getId();

	if (index >= dogs.size())
		return;

	std::string breed = this->breedEdit->text().toStdString();
	std::string name = this->nameEdit->text().toStdString();
	std::string age = this->ageEdit->text().toStdString();
	std::string link = this->photoLinkEdit->text().toStdString();
	
	int pos = this->con.getPosByIdRepo(id);

	if (breed != "")
	{
		this->con.updateBreed(pos, id, breed);
	}
	if (age != "")
	{
		this->con.updateAge(pos, id, std::stoi(age));
	}
	if (name != "")
	{
		this->con.updateName(pos, id, name);
	}
	if (link != "")
	{
		this->con.updateLink(pos, id, link);
	}
	
	this->currentDogsInRepo = this->con.getAllRepo();
	this->initRepo();
}

void DogGui::filter()
{
	std::string name = this->nameEdit->text().toStdString();
	if (name == "")
	{
		this->currentDogsInRepo = this->con.getAllRepo();
		this->initRepo();
		return;
	}
	else
	{
		this->currentDogsInRepo = this->con.filterName(name);
		this->initRepo();
	}
}

void DogGui::moveAll()
{
	for (auto dog : this->currentDogsInRepo)
	{
		this->con.addAdopted(dog);
		this->con.remove(dog.getId());
	}

	this->currentDogsInRepo = this->con.getAllRepo();
	this->currentDogsAdopted = this->con.getAllAdopted();
	this->initRepo();
	this->initAdopted();
}

void DogGui::moveOne()
{
	int index = this->getItemRepo();
	if (index == -1 || index >= this->currentDogsInRepo.size())
		return;

	this->con.addAdopted(this->currentDogsInRepo[index]);
	this->con.remove(this->currentDogsInRepo[index].getId());
	this->currentDogsInRepo = this->con.getAllRepo();
	this->currentDogsAdopted = this->con.getAllAdopted();
	this->initRepo();
	this->initAdopted();
}

void DogGui::removeDogClient()
{
	int index = this->getItemAdopted();
	if (index == -1)
		return;
	std::vector<Dog> dogs = this->con.getAllAdopted();

	if (index >= dogs.size())
		return;

	con.add(dogs[index]);
	this->con.removeAdopted(dogs[index].getId());
	this->currentDogsInRepo = this->con.getAllRepo();
	this->currentDogsAdopted = this->con.getAllAdopted();
	this->initAdopted();
	this->initRepo();
}

void DogGui::displayDogRepo()
{
	int index = this->getItemRepo();
	if (index == -1 || index >= this->currentDogsInRepo.size())
		return;
	this->currentDogsInRepo[index].open();
}

void DogGui::displayDog()
{
	this->con.getCurrent().open();
	//int index = this->getItemAdopted();
	//if (index == -1 || index >= this->currentDogsAdopted.size())
	//	return;
	//this->currentDogsAdopted[index].open();
}

void DogGui::saveToFile()
{
	Database<Dog>* db = new TextFileDatabase<Dog>("C:\\Users\\asus\\source\\repos\\Lab10-12\\adopted.txt");
	for (auto dog : this->con.getAdopted()->getVector())
	{
		db->add(dog);
	}
	db->writeFile();
	delete db;
}

void DogGui::saveToHtml()
{
	Database<Dog>* db = new HtmlFileDatabase<Dog>("C:\\Users\\asus\\source\\repos\\Lab10-12\\adopted.html");
	for (auto dog : this->con.getAdopted()->getVector())
	{
		db->add(dog);
	}
	db->writeFile();
	delete db;
}

void DogGui::next()
{
	this->con.next();
	this->initAdopted();
}