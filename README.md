# 🍔 Build-a-Burger (BaB)

## 📋 Description / Project Overview
**Build‑a‑Burger (BaB)** is a text‑based, week‑long burger‑making simulation game where the player takes on the role of a burger shop apprentice. Over the course of seven in‑game days, the player prepares ingredients through interactive minigames, assembles customer orders, and earns ratings based on accuracy and quality. The project demonstrates core OOP principles such as abstraction, inheritance, polymorphism, and encapsulation.

The project demonstrates core **Object-Oriented Programming (OOP)** principles such as abstraction, inheritance, polymorphism, and encapsulation.

---

## 🧩 OOP Concepts Used

| OOP Principle | How It Is Used in the Program |
| :--- | :--- |
| **Abstraction** | The `Ingredient` abstract class defines shared attributes (name, quality, and minigame metrics) and declares the abstract method `performMinigame()`. Each ingredient subtype must implement this method, hiding the internal complexity of minigame logic while exposing only essential interfaces. |
| **Inheritance** | `Patty`, `Topping`, and `Sauce` inherit from `Ingredient`. They automatically gain shared fields and behaviors, allowing the program to reuse code efficiently. Each subclass also adds its own minigame mechanics while maintaining the structure defined by the parent class. |
| **Polymorphism** | The program treats all ingredient types as `Ingredient` objects when assembling a `Burger`. When `performMinigame()` is called, Java automatically executes the correct version based on the ingredient type (Patty/Topping/Sauce), enabling flexible interaction with mixed ingredient lists. |
| **Encapsulation** | Classes like `Player` and `RatingSystem` store their data privately (performance history, upgrade states, scoring values). Access is controlled through public methods such as `addRating()`, `setDailyAverage()`, and `calculateRating()`, ensuring data integrity and preventing direct manipulation. |
| **Composition** | The `Burger` class is composed of multiple ingredient objects (patty, toppings, sauces). A burger cannot exist without these components, and their lifecycle is tied to the `Burger` itself. This structure models real-world assembly while organizing ingredient management cleanly. |
| **Modularity** | The project divides responsibilities into separate classes: `BaBGame` manages game flow, `RatingSystem` evaluates quality, `CustomerTicket` generates orders, and ingredient classes handle minigames. This makes the program easier to maintain, debug, and extend. |

---

## 📂 Program Structure

```
Build-a-Burger/
├── src/
│   ├── BaBGame.java          // Main entry point; handles game loop and UI
│   ├── Player.java           // Manages player stats, upgrades, and scores
│   ├── RatingSystem.java     // Logic for calculating stars based on accuracy/time
│   ├── CustomerTicket.java   // Generates random orders and names
│   ├── Burger.java           // Represents the final assembled product
│   ├── Ingredient.java       // Abstract parent class for all food items
│   ├── Patty.java        // Subclass: Handles cooking timing minigame
│   ├── Topping.java      // Subclass: Handles reaction time minigame
│   └── Sauce.java        // Subclass: Handles typing speed minigame
├── .gitignore
└── README.md
```
