# Retail Store Management System (DBMS Project)

A fully functional **command-line based online grocery store** built as part of the CSE202: Fundamentals of Database Management Systems course at IIIT-Delhi.

This system allows customers to browse, order, and pay for grocery items while enabling admins to manage inventory, track customer activity, and oversee delivery logistics using an SQL-backed relational database.

🔗 [Project Motivation](https://docs.google.com/presentation/d/1703LuXUJ3Hc4cLSZ0xvnJ_McD7BVaa4g/edit?usp=sharing&ouid=108049351732374820521&rtpof=true&sd=true)
🔗 [More info about Project](https://drive.google.com/file/d/1azQZSekmHlj3ydol4UMGgbQ21XVo2M_L/view?usp=sharing)


---

## Tech Stack
- **Language**: Java (CLI)
- **Database**: MySQL (with complex schema and relationships)
- **Tools**: IntelliJ / VS Code, MySQL Workbench
- **Concepts**: Triggers, Transactions, Indexes, SQL Joins, ER Diagrams, Embedded SQL

---

## Functional Modules

### Customer
- Register/login with secure password and input validation
- View available items and current deals
- Add/remove items from cart
- Place orders using multiple payment methods
- Track delivery status
- Provide feedback after order

### Admin
- View all customers and order stats
- Analyze top customers (by spend, quantity, average order)
- Manage inventory (add/update/remove items)
- View product-deal and product-supplier mappings

### Inventory & Orders
- Real-time quantity update on order placement
- Apply discounts from deals table
- Assign delivery partners to orders
- Trigger-based stock update and login security

---

## Database Features

- **15+ Tables** with foreign key relationships
- **Normalization & Indexing**
- **Triggers**:
  - Automatically update item stock post order
  - Block account after 3 failed login attempts
- **Transactions**: Demonstrated both conflict-serializable and non-conflicting transactions
- **ER Diagram & Relational Schema** included

---

## File Structure
RetailStoreDB
- 📄 finalfinaldbms.sql ← Complete schema and sample data
- 📄 Project Overview.pdf ← The overview of the Functional & technical section
- 📄 Relational diagram and ER diagram.pdf
- 📄 Conflicting and Non-Conflicting Database Transactions.pdf
- 📄 README.md ← You're reading it!
