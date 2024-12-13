#include "Lab1012.h"

#include <QtWidgets/QApplication>
#include <QtWidgets/QTextEdit>
#include <QtWidgets/QLabel>
#include <QtWidgets/QHBoxLayout>
#include <QtWidgets/QMessageBox>

#include "Tests.h"
#include "UI.h"
#include "UserUI.h"
#include "AdminUI.h"
#include "Database.h"
#include "TextFileDatabase.h"
#include "HtmlFileDatabase.h"
#include "Controller.h"
#include "FileController.h"
#include "GuiController.h"
#include "DogGui.h"

Database<Dog>* choosePersistence(const std::string& txtPath, const std::string& htmlPath)
{
	QMessageBox choice;
	choice.setText("Choose persistence type: ");
	QAbstractButton* html = choice.addButton("HTML File", QMessageBox::YesRole);
	QAbstractButton* txt = choice.addButton("CSV File", QMessageBox::NoRole);

	choice.exec();

	while (true)
	{
		if (choice.clickedButton() == html)
			return new HtmlFileDatabase<Dog>(htmlPath);
		else if (choice.clickedButton() == txt)
			return new TextFileDatabase<Dog>(txtPath);
	}
}

int main(int argc, char *argv[])
{
	QApplication app(argc, argv);

	std::string txtPath = "C:\\Users\\asus\\source\\repos\\Lab10-12\\dogs.txt";
	std::string htmlPath = "C:\\Users\\asus\\source\\repos\\Lab10-12\\dogs.html";
	
	Database<Dog>* dogDb = choosePersistence(txtPath, htmlPath);
	Database<Dog>* adoptedDb = new Database<Dog>;

	GuiController<Dog> con = GuiController<Dog>(dogDb, adoptedDb);
	DogGui gui{ con };
	gui.show();
	
	return app.exec();
}
