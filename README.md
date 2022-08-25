Coverage: xx%
# Inventory Management System (IMS) for a Cocktail Bar

Using the given project template, the code has been altered and expanded so as to provide a fully functional command line interface that can be used by staff. The context is a bar service, which allows customers to make an account, and create an order composed of several drinks in the bars inventory such as cocktails, beer, cider, wine etc. This order connects to the staff interface of the application, which allows them to update their menu, view orders, take payment, etc. Therefore this application provides the medium for the bar to take online orders from customers, and also to keep stock of their current inventory and menu.

Jira was used to create a Scrum board, as shown below:

https://jadewfoster.atlassian.net/jira/software/projects/J3PROJ/boards/2/roadmap

<img src="/documentation/screenshots/jira-scrum-board" alt="Jira Scrum Board" style ="width:128px;height:128px;">

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites

To run the project you need to install the following:
* Java 18
* Maven (as build tool)
* IDE such as IntelliJ, VScode or Eclipse (which was used for this project)
* JUnit
* mySQL (to interact with the database and use the schema given in the resources section of the project)


### Installing

A step by step series of examples that tell you how to get a development env running:

* Download JDK (Java Development Kit) from the Oracle website (https://www.oracle.com/java/technologies/javase/jdk14-archive-downloads.html) 
* Setup env variables JAVA_HOME with PATH file appended with %JAVA_HOME%/bin
* Install Eclipse IDE for Java Developers (https://www.eclipse.org/downloads/packages/)
* Install Maven (https://maven.apache.org/download.cgi)
* Setup env variables MAVEN_HOME with PATH file appended with %MAVEN_HOME%/bin
* Install mySQL (https://dev.mysql.com/downloads/windows/installer/8.0.html), preferably with password 'pass'

When you have everything installed, fork the repo and open the project in Eclipse, updating db.properties with your connection info from your mySQL server. 

## Using the application

The application has 3 entities of customer, item and order. On the launch screen, these entities can be manipulated via user input "customer", "order" or "item"

Customer:
Create -> Asks for name and surname, then adds entry to the database
Read -> Displays all customers in database
Update -> Asks for customer id, new name and new surname, then updates entry in the database.
Delete -> Asks for customer id, then deletes customer from database.
Return -> Returns user back to main menu

Item
Create -> Asks for item name, price and stock, then adds entry to the database
Read -> Displays all items in database
Update -> Asks for item id, new item name, new price and new stock, then updates entry in the database.
Delete -> Asks for item id, then deletes item from database.
Return -> Returns user back to main menu

Order
Create -> Asks for customer id, item id and quantity, then adds entry to the database
Read -> Displays all orders in database
Update -> Asks for order id, new item id and quantity, then updates entry in the database.
Delete -> Asks for order id, then deletes order from database OR asks for order id and item id, then deletes item from order in database.
Return -> Returns user back to main menu

Stop -> Closes the application

## Running the tests

Explain how to run the automated tests for this system. 
Break down into which tests and what they do

### Unit Tests 

Explain what these tests test, why and how to run them


### Integration Tests 
Explain what these tests test, why and how to run them


### Coding style tests

Explain what these tests test and why


## Deployment

To deploy this on a live system, in the sql-schema file, remove drop schema 'ims', therefore data will be maintained across several people accessing the system, rather than being reset each time someone new accesses it.

## Built With

* [Maven](https://maven.apache.org/) - Dependency Management

## Versioning

We use [GitHub](https://github.com/) for versioning.

## Authors

* **Chris Perrins** - *Initial work* - [christophperrins](https://github.com/christophperrins)
* **Jade Willow Foster** - *Project work* - [jadewfoster](https://github.com/jadewfoster)

## License

This project is licensed under the MIT license - see the [LICENSE.md](LICENSE.md) file for details 

*For help in [Choosing a license](https://choosealicense.com/)*

## Acknowledgments
* QA Academy
* Trainers: Victoria Sacre, Jordan Benbelaid, Anoush Lowton, Christopher Yiangou.