
 Loan Calculator (JavaFX) — Amortization Table

A simple JavaFX application that calculates a loan’s monthly payment and generates a full amortization table (month-by-month breakdown of: payment, interest paid, principal/capital paid, and remaining balance).  
It also shows a Pie Chart that compares how much you pay in **interest vs capital**.

---

## Features

Inputs:
  - Loan amount (principal)
  - Annual interest rate (%)
  - Duration (years)

Outputs:
  - Monthly payment
  - Total interest paid
  - Total amount paid (principal + interest)
    
Amortization table:
  - Month number
  - Starting balance
  - Monthly payment
  - Interest paid
  - Capital (principal) paid
  - Remaining balance

Pie chart:
  - Interest vs Capital percentage

Input validation (basic error alert if parsing fails)

---

-How it works (Math)

The app uses the standard amortized loan payment formula:

Monthly rate: r = (annualInterestRate / 12) / 100
Number of months: n = years * 12



Monthly payment: P = (r * amount) / (1 - (1 + r)^(-n))



Then for each month:

- interestPaid = balance * r
- capitalPaid = monthlyPayment - interestPaid
- newBalance = balance - capitalPaid

---

-Project Structure

Main classes:

- LoanApplication
  - JavaFX entry point (start)  
  - Loads the UI from loan.fxml

- LoanController
  - Handles UI events (Calculate / Exit)
  - Reads user inputs
  - Displays:
    - monthly payment, total interest, total paid
    - table with payments
    - pie chart data

- Loan
  - Core loan logic (calculation + building amortization table)
  - Stores:
    - list of `Payment` objects
    - total interest and total paid

- Payment
  - Data model for one monthly row in the table:
    - month, oldBalance, payment, interest, capital, remaining

---

## Requirements

- Java JDK 17+ (recommended)
- JavaFX SDK (depending on your setup)
- IDE recommended: IntelliJ IDEA (works great with JavaFX)

> Note: The UI file `loan.fxml` must exist in:
`src/main/resources/com/example/kalkulator/loan.fxml`

---

## Running the App

### Option A — Run from IntelliJ (easiest)
1. Open the project in IntelliJ
2. Make sure JavaFX is configured (VM options / SDK)
3. Run:
   - `LoanApplication.java`

### Option B — Run with JavaFX VM options (manual)
If you run via command line, you’ll need JavaFX modules:

Example (adjust paths):
```bash
java --module-path /path/to/javafx/lib --add-modules javafx.controls,javafx.fxml -cp out com.example.kalkulator.LoanApplication
````

---

## Usage

1. Enter:

   * Amount (e.g. `10000`)
   * Interest rate (e.g. `5.5`)
   * Duration in years (e.g. `3`)
2. Click **Calculate**
3. You’ll see:

   * Monthly payment
   * Total interest
   * Total paid
   * Full amortization table
   * Interest vs Capital chart

---

## Notes / Limitations

* Inputs must be valid numbers (otherwise an alert shows an error).
* Interest rate should be a positive percentage.
* Duration should be a positive integer.

---

## Possible Improvements (Future Work)

* Better validation (empty fields, negative values, min/max checks)
* Export amortization table to **CSV/PDF**
* Support different currencies / localization
* Extra features:

  * extra monthly payments
  * early payoff simulation

---

## License

This project is for learning and demonstration purposes.
Feel free to use and modify it.
