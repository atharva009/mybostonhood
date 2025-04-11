# 🏡 BostonHood

BostonHood is a Java Swing-based desktop application designed to help users explore and analyze neighborhoods in a city. The platform supports both general users and administrators, offering tailored features for each role.

---
## 🧑🏼‍💻 Users
  - Explore a list of neighborhoods through an intuitive UI
  - Sort and filter neighborhoods based on:
      - Average Rent
      - Crime Rate
      - School Ratings
      - Green Space Score
  - Compare neighborhoods side-by-side
  - Access an Analytics Dashboard with:
    - Graphs (bar, line) showing comparative data
    - Pie charts visualizing score distributions
      
## 👩🏻‍💼 Admin
  - Admins have access to a secure authentication page where they can:
      - Add new neighborhoods
      - Edit existing neighborhood details
      - Delete neighborhoods as needed
        
`This ensures that the data remains accurate, up-to-date, and tailored to the needs of the community.`

## ✨ Features

- **Admin Dashboard** to view and manage neighborhoods
- **Add Neighborhood** form with input validation
- **Edit Neighborhood** functionality with real-time updates
- **Custom Navigation Stack** to simulate back-navigation and control frame history
- **Validated Input** for all fields (e.g., rent, crime rate, green space score)

---

## 🧰 Tech Stack

- **Java**
- **Java Swing** (for GUI)
- **Custom Data Structures**
  - `NeighborhoodDirectory` (HashMap-based)
  - `NeighborhoodSorter` (Binary Search Tree)
  - `NavigationStack` (Stack-based Navigation)
  - `NeighborhoodCompareList` (List-based)
  - `WeightedScoringEngine` 

---
## 👩🏻‍💻 Contributors
- Atharva Hankare
- Keya Goswami
- Priyank Bagad

## 📁 Project Structure

### ➤ General
- **All fields are required** — no empty inputs allowed.

### ➤ Field-specific Rules:

| Field            | Type     | Constraints                            |
|------------------|----------|----------------------------------------|
| Name             | String   | Letters and spaces only                |
| Description      | String   | Alphanumeric and basic symbols allowed |
| Average Rent     | Double   | 0.0 to 10.0                            |
| Crime Rate       | Double   | 0.0 to 10.0                            |
| School Rating    | Double   | 0.0 to 10.0                            |
| Green Space Score| Integer  | 0 to 100                               |

## 🧭 Navigation Logic

The app uses a custom `NavigationStack` to simulate browser-like behavior:
- Each new page pushes itself onto the stack.
- Going "back" pops the current frame and shows the previous one.
- Prevents duplicate pages (e.g. multiple `AddNeighborhoodPage` instances).
- Ensures a clean stack during navigation and data operations.

---
## How to run BostonHood

***Prerequisites***

**Before running the project, ensure you have the following installed:**
- Java 21/22
- Git

`1. Clone the Repository`
- git clone git@github.com:atharva009/mybostonhood.git

`2. Compile the code`
- javac -d out -cp "lib/*" $(find src -name "*.java")

`3. Run the code`
-  java -cp out app.Main


**For any queries you can connect any of us 📧**
