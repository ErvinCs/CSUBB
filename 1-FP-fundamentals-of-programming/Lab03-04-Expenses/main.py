'''
Jane is the administrator of an apartment building and she wants to manage the monthly expenses for
each apartment. Jane needs an application to store, for a given month, the expenses for each
apartment. Each expense is stored in the application using the following elements:
-apartment(number of apartment, positive integer),
-amount (positive integer),
-type (from one of the predefined categories: water, heating, electricity, gas, other).
Help Jane by creating an application that provides the following functionalities:
1.Add <apartment> <type> <amount>
2.Remove <apartment
  Remove <start apartment> to <end apartment>
  Remove <type>
  Replace <apartment> <type> with <amount>
3.List
  List <apartment>
  List [< | = | >] <amount>
4.Sum <type>
  Max <apartment>
  Sort apartment
  Sort type
5.Filter <type>
  Filter <value>
6.Undo
  The user will be able to undo all operations performed since program start.
'''

import domain.testExpense
import ui.consoleUI

if __name__ == '__main__':
    domain.testExpense.runTests()
    runList = ui.consoleUI.listControl.initList()
    ui.consoleUI.runConsole(runList)
    #commit@scs.ubbcluj.ro
    #zip cu extensia zip xD