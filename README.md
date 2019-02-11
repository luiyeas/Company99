# Company99
A Clean MVP project (boilerplate example) written in Kotlin

# Architecture
In this project the architecture used has been MVP. What are the advantages of SOLID-MVP?:

1) Totally decoupled
2) Each layer can easily testable separately
3) Modularize code
4) Usecase can be well maintained
5) Easily scalable

# Presenter
The view-interface, usually implemented by an Activity (or Fragment) will contain a reference to the presenter. It will be responsible for creating the presenter object. The only thing that the view will do is calling a method from the presenter every time there is an interface action (a button click for example).

# Model
Model would only be the gateway to the domain layer or business logic. The model would probably be an interactor that implements a use case.

# Entities
Enterprise level classes

# Usecases (Interactors)
Handle the flow to and from entities

As you can see in the image, the approach than I'm using only has one entry point in the presenter. The Presenter receive Actions and send State to the view. With this approach I reduce possible bugs and it's easier to debug and test.

![Architecture](https://github.com/luiyeas/Company99/blob/master/CleanDiagram.jpg)

There are 4 main modules:

UI and PRESENTER: In this part we can find Views (Activities, Fragments, Screens..), Presenters and Mapper(map between Domain and Repository). This part is responsable to render the data received by other layers.

DOMAIN: In this part we can find the UseCases and Domain models. In this part we can create the business rules that define the project. The UseCases or Interactors call the repositories to get the data and does some logic defined by the business rules.

DATA: In this part we can find DataSources (Api, DataBase, SharedPreferences,...) and Mappers(map between Data and Domain). In this part we can have a lot of DataSource, each one will be responsable to get the data from one source and return a dataModel
Using this tree layers, we can isolate each functionality, make a testable app and avoid most of the bugs that occurs in the normal apps.
