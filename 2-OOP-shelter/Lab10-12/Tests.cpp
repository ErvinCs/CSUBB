#include <cassert>
#include <iostream>

#include "Tests.h"
#include "Dog.h"
#include "DynamicVector.h"
#include "Database.h"
#include "Controller.h"
#include "InsertionSort.h"
#include "AscAgeComparator.h"
#include "AscNameComparator.h"

void Tests::testDog()
{
    Dog dog = Dog("Breed", "Name", 5, "https://www.petfinder.com/barnyard/festus-41370249/ak/fairbanks/fairbanks-north-star-borough-animal-control-ak12/");
    assert(dog.getBreed() == "Breed");
    assert(dog.photoLink == "https://www.petfinder.com/barnyard/festus-41370249/ak/fairbanks/fairbanks-north-star-borough-animal-control-ak12/");
    assert(dog.age == 5);
    assert(dog.name == "Name");
    assert(dog.toString() == "Dog{breed=Breed, name=Name, age=5, id=1}\n");
}

void Tests::testDynamicVector()
{
    DynamicVector<Dog> arr = DynamicVector<Dog>();

    assert(arr.getCapacity() == 1);
    assert(arr.getSize() == 0);

    Dog d1 = Dog("Bulldog", "Piggy", 5, "https://www.petfinder.com/barnyard/festus-41370249/ak/fairbanks/fairbanks-north-star-borough-animal-control-ak12/");
    arr.add(d1);
    assert(arr.getItem(0).getBreed() == "Bulldog");

    Dog d2 = Dog("Food", "Peanut", 5, "https://www.petfinder.com/dog/peanut-37342395/ak/fairbanks/homeward-bound-pet-rescue-and-referral-ak29/");
    arr.add(d2);
    assert(arr.getSize() == 2);
    assert(arr.getCapacity() == 2);

    assert(arr.update(d2.getId(), d1));
    assert(arr.getItem(0) == arr.getItem(1));

    assert(arr.remove(d1.getId()));
    assert(arr.getSize() == 1);
    assert(arr.getCapacity() == 2);

    DynamicVector<Dog> arr2 = DynamicVector<Dog>();
    arr2 = arr2 + d1;
    arr2 = d2 + arr2;
    assert(arr2.getItem(0) == d1);
    assert(arr2.getItem(1) == d2);
    assert(arr2.getSize() == 2);

    DynamicVector<Dog> arr3 = DynamicVector<Dog>();
    Dog d3 = Dog("d3", "d3", 10, "link3");
    arr3.add(d3);
    arr3 = arr3 + arr2;
    assert(arr3.getSize() == 3);
    assert(arr3.getItem(1) == d1);
}

void Tests::testDatabase()
{
    Database<Dog> db = Database<Dog>("files/testdogs.txt");
    Dog d1 = Dog("a", "a", 1, "link1");
    Dog d2 = Dog("b", "b", 10, "link2");
    Dog d3 = Dog("a", "a", 6, "link3");
    Dog d4 = Dog("a", "a", 4, "link4");
    db.add(d1);
    db.add(d2);
    db.add(d3);
    db.add(d4);

    std::string name = "a";
    int age = 5;

    assert(db.getSize() == 4);
    assert(db.getItem(0) == d1);

    assert(db.update(d2.getId(), d1));
    assert(db.getItem(1).getAge() == d1.getAge());
    assert(db.getItem(1).getName() == d1.getName());
    assert(db.getItem(1).getBreed() == d1.getBreed());
    assert(db.getItem(1).getPhotoLink() == d1.getPhotoLink());
    assert(db.getItem(1).getId() != d1.getId());

    assert(db.remove(d1.getId()));
    assert(db.getSize() == 3);
    assert(!db.remove(10));
}

void Tests::testController()
{
    Database<Dog> db = Database<Dog>();
    Controller<Dog> con = Controller<Dog>(db);
    Dog a = Dog("aB", "aN", 1, "linka");
    Dog b = Dog("bB", "bN", 10, "linkb");
    con.add(a);
    con.add(b);
    con.update(a.getId(), b);
    assert(con.getItem(0).getBreed() == b.getBreed());
    assert(con.getItem(0).getName() == b.getName());
    assert(con.getItem(0).getAge() == b.getAge());
    assert(con.getItem(0).getPhotoLink() == b.getPhotoLink());
    assert(con.getItem(0).getId() != b.getId());
}

void Tests::testInsertionSort()
{
    DynamicVector<Dog> arr = DynamicVector<Dog>();
    Dog d1 = Dog("b1", "d1", 1, "link1");
    Dog d2 = Dog("b10", "ab10", 10, "link10");
    Dog d3 = Dog("b6", "b6", 6, "link6");
    Dog d4 = Dog("b4", "c4", 4, "link4");
    arr.add(d1);
    arr.add(d2);
    arr.add(d3);
    arr.add(d4);

    AscAgeComparator<Dog> ageCom;
    AscNameComparator<Dog> nameCom;

    DynamicVector<Dog> arr2 = DynamicVector<Dog>();
    arr2.add(d1);
    arr2.add(d4);
    arr2.add(d3);
    arr2.add(d2);

    insertionSort(&arr, ageCom);
    assert(arr == arr2);
    arr2.removeAll();

    arr2.add(d2);
    arr2.add(d3);
    arr2.add(d4);
    arr2.add(d1);

    insertionSort(&arr, nameCom);
    assert(arr == arr2);
}

void Tests::testFileParser()
{
    FileParser fp;
    std::string s = "This,is,a=csv,string";
    std::vector<std::string> r;
    r.push_back("This");
    r.push_back("is");
    r.push_back("a=csv");
    r.push_back("string");
    assert(fp.tokenize(s,',') == r);
}

void Tests::testAll()
{
    Tests::testDog();
    Tests::testDynamicVector();
    Tests::testDatabase();
    Tests::testController();
    Tests::testInsertionSort();
    Tests::testFileParser();
}
