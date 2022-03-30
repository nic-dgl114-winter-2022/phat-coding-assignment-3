# Harvesting Fruits Game

Author: Phat Tran

Course: DGL-114

Assignment: Coding Assignment 3

Submitted: March 28th, 2022

Time Spent: 2-3 days

# Introduction
This is an upgraded version of the coding assignment 2. I keep the idea of making a game in which the player harvests the fruits and sell them to the store. 

Every 5 seconds, the amount of the fruits within the land increases by 6. The player tap the land to collect the fruits and can sell them in the **Market**.

The game contains three screens:

- **Home screen**
  
  This is the landing screen when the application opens. Player can access to other screens from here. It displays 9 lands in the middle of the screen. The user can harvest the fruits by tapping the land. After being harvested, the land will switch to "soil" land and player has to wait until the next harvesting.
  
  <img src="https://user-images.githubusercontent.com/45039354/160941005-ded640b1-6309-45ca-854a-1a9b1c0cf3d6.png" width="200" height="350">
  
- **Inventory screen**

  This is a place where the player see the available fruits in the inventory

  <img src="https://user-images.githubusercontent.com/45039354/160941370-180e52b6-36c3-40ab-84af-5e52892d8e80.png" width="200" height="350">
  
- **Market screen**

  This is where the player sells the fruits and get money. Depending on the input amount and price of the fruit, the player can sell them a earn a equilivent value. It also contains a "Sell All" button to sell all the fruits just by one tap. Otherwise, the player can buy each fruit separately.

  <img src="https://user-images.githubusercontent.com/45039354/160941415-3aa7c8c4-c0b0-4566-bc91-8fcdd91ecd53.png" width="200" height="350">

# Code Description
### 1. Fragment

There are 3 fragments representing three different screens:
- **HomeFragment** (default home screen)
- **InventoryFragment**
- **MarketFragment**

### 2. Room (database)
It requires 3 entities to play:
- **Fruit**
  
  This entity contains the information about the fruit such as name, price, quantity in stock. 
  
- Land

  This entity includes information about the land such as the planted fruit, status, the available amount to harvest.

- User
  
  This entity is just created to store the amount of money because this is a single player game.
  
### 3. Recycler view
The project contains two lists implementing the recycler view:
 - In **Inventory screen**, the list is rendered by the recycler view.
 - In **Market screen**, the list of fruits is displayed with the recycler view.

### 4. LiveData
The two lists in **Inventory screen** and **Market screen** are attached by the LiveData. Any changes from the database will affect concurrent to the content.

### 5. View Models
Due to having three entities, there are 3 view models for each screen:
- **FruitViewModel.kt**
- **LandViewModel.kt**
- **UserViewModel.kt**

# Development
This game is only a single player game so there is no api calls in this project. Thus, the project can upgrade by becoming a multiple player gamne. On the other hand, the money in the project has not been used to buy anything yet, a new feature that requiring money to spent is also a good idea.

Fortunately in this project, I learn a way to fix a bug that the **Coding assignment 2** that I did not find a way to fix it. If you inflate an item of the **Recycler View** by **Binding**, remember to attach it to the **parent**. Otherwise, the item of the list will not expand as wide as the parent if the width was set to **"match_parent"**

# References (Free Game assets)
* https://bakudas.itch.io/generic-oldwest-pack
* https://helm3t.itch.io/farmlandia-fruit
* https://aztrakatze.itch.io/topdown-farm-crop
